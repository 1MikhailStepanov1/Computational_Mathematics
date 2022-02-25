import exceptions.IncorrectInputException;

public class MatrixHandler {

    IOHandler ioHandler;

    public MatrixHandler(IOHandler ioHandler) {
        this.ioHandler = ioHandler;
    }

    public double[][] cloneMatrix(double[][] matrix){
        double[][] result = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++){
            System.arraycopy(matrix[i], 0, result[i], 0, matrix.length);
        }
        return result;
    }

    public double[] cloneFreeCoefficients(double[] freeCoefficients){
        double[] result = new double[freeCoefficients.length];
        System.arraycopy(freeCoefficients, 0, result, 0, freeCoefficients.length);
        return result;
    }

    public String findDeterminant(double[][] matrix) throws IncorrectInputException {
        double result = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i == j) {
                    elementValidationAndModification(matrix, i, j);
                    rowSubtraction(matrix, i);
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            result *= matrix[i][i];
        }
        if (result == 0) {
            throw new IncorrectInputException("Детерминант матрицы равен 0, введите другие значения");
        }
        String resultString = "Детерминант матрицы: " + String.valueOf(result);
        return resultString;
    }

    private void elementValidationAndModification(double[][] matrix, int rowIndex, int columnIndex) throws IncorrectInputException {
        if (matrix[rowIndex][columnIndex] == 0) {
            double columnSum = 0;
            for (int i = 1; i < matrix.length; i++) {
                columnSum += matrix[i - 1][columnIndex];
            }
            if (columnSum == 0) {
                throw new IncorrectInputException("В матрице имеется нулевой столбец. Измените введенные даныне.");
            } else {
                for (int i = rowIndex; i < matrix.length - 1; i++) {
                    if (matrix[i][columnIndex] != 0) {
                        double[] temp = matrix[rowIndex];
                        matrix[rowIndex] = matrix[i];
                        matrix[i] = temp;
                        break;
                    }
                }
            }
        }
    }

    private void rowSubtraction(double[][] matrix, int elementIndex) {
        for (int i = elementIndex + 1; i < matrix.length; i++) {
            double tempCoefficient = matrix[i][elementIndex] / matrix[elementIndex][elementIndex];
            for (int j = elementIndex; j < matrix.length; j++) {
                matrix[i][j] = matrix[i][j] - matrix[elementIndex][j] * tempCoefficient;
                if (Double.isNaN(matrix[i][j])) {
                    matrix[i][j] = 0d;
                }
            }
        }
    }

    public void diagonalAlignment(double[][] matrix, double[] freeCoefficients) throws IncorrectInputException {
        for (int i = 0; i < matrix.length; i++) {
            double maxInColumn = Double.MIN_VALUE;
            int maxIndex = -1;
            double columnSum = 0;
            double[] temp = null;
            double tempNumber;
            for (int j = 0; j < matrix.length; j++) {
                columnSum += Math.abs(matrix[j][i]);
                if (Math.abs(matrix[j][i]) > maxInColumn) {
                    maxInColumn = Math.abs(matrix[j][i]);
                    maxIndex = j;
                }
            }
            if (maxInColumn >= (columnSum - maxInColumn)) {
                temp = matrix[i];
                matrix[i] = matrix[maxIndex];
                matrix[maxIndex] = temp;
                tempNumber = freeCoefficients[i];
                freeCoefficients[i] = freeCoefficients[maxIndex];
                freeCoefficients[maxIndex] = tempNumber;
            } else
                throw new IncorrectInputException("Невозможно привести матрицу к виду с преобладанием диагональных элементов. Условие максимального элемента");
        }
        for (int i = 0; i < matrix.length; i++) {
            double maxInColumn = Double.MIN_VALUE;
            int maxIndex = -1;
            for (int j = 0; j < matrix.length; j++) {
                if (Math.abs(matrix[j][i]) > maxInColumn) {
                    maxInColumn = Math.abs(matrix[j][i]);
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                throw new IncorrectInputException("Невозможно привести матрицу к виду с преобладанием диагональных элементов. Не удается перставить строки нужным образом");
            }
        }
    }

    public void matrixAndRootsNormalization(double[][] matrix, double[] roots) {
        for (int i = 0; i < matrix.length; i++) {
            double tempCoefficient = matrix[i][i];
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = -1 * matrix[i][j] /tempCoefficient;
            }
            roots[i] = roots[i]/tempCoefficient;
            matrix[i][i] = 0;
        }
    }

    public boolean convergenceCheck(double[][] matrix) throws IncorrectInputException{
        double[] coefficients = new double[matrix.length];
        double maxCoefficient = Double.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++){
            double rowSum = 0;
            for (int j = 0; j < matrix.length; j++){
                rowSum += Math.abs(matrix[i][j]);
            }
            coefficients[i] = rowSum;
        }
        for (double coefficient : coefficients) {
            if (coefficient > maxCoefficient) {
                maxCoefficient = coefficient;
            }
        }
        if (maxCoefficient >= 1){
            throw new IncorrectInputException("Не выполняется условие сходимости");
        }
        return true;
    }

    public boolean calculateAnswer(double[][] coefficientMatrix, double[] previousIteration, double[] freeCoefficient, double epsilon, int iterationNumber){
        double resultEpsilon = Double.MIN_VALUE;
        double[] result = new double[previousIteration.length];
        for (int i = 0; i < coefficientMatrix.length; i++){
            result[i] = 0;
            for (int j = 0; j < coefficientMatrix.length; j++){
                result[i] += coefficientMatrix[i][j]*previousIteration[j];
            }
            result[i] += freeCoefficient[i];
        }
        for (int i =0; i < result.length; i++){
            if (Math.abs(result[i] - previousIteration[i]) > resultEpsilon){
                resultEpsilon = Math.abs(result[i] - previousIteration[i]);
            }
            previousIteration[i] = result[i];
        }
        ioHandler.printFormatAnswer(result, resultEpsilon);
        if (resultEpsilon > epsilon) {
            return true;
        } else {
            ioHandler.printAnswer(result, resultEpsilon, iterationNumber);
            return false;
        }
    }
}
