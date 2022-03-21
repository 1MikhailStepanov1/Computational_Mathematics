package equationSystem;

import data.Data;
import exceptions.IncorrectDataException;
import main.IOHandler;

import java.util.Arrays;

public class EquationSystemSolver {
    private final IOHandler ioHandler;
    public EquationSystemSolver(IOHandler ioHandler){
        this.ioHandler = ioHandler;
    }

    public double[] solveWithNewtonMethod(double[] solutions) throws IncorrectDataException{
        boolean isEnd = false;
        double[] result = new double[solutions.length];
        System.arraycopy(solutions, 0, result, 0, result.length);
        int iteration = 0;
        while (true) {
            if (isEnd){
                break;
            }
            double[] function = Data.getSystem(solutions, 0);
            double[][] Jacobi = new double[solutions.length][solutions.length];
            for (int i = 0; i < solutions.length; i++) {
                Jacobi[i] = Data.getSystem(solutions, i + 1);
            }
            double[][] JacobiCopy = new double[Jacobi.length][Jacobi.length];
            for (int i = 0; i < Jacobi.length; i++) {
                System.arraycopy(Jacobi[i], 0, JacobiCopy[i], 0, Jacobi.length);
            }
            double determinant = findDeterminant(JacobiCopy);
            double[][] reverseJacobi = reverseMatrix(Jacobi);
            double[] rightPart = matrixMultiplication(reverseJacobi, function);
            for (int i = 0; i < result.length; i++){
                result[i] = result[i] - (1/determinant)*rightPart[i];
                if (Math.abs(result[i] - solutions[i]) <= Data.getEpsilonSystem()){
                    isEnd = true;
                }
            }
            iteration++;
            ioHandler.printSystemIteration(iteration, solutions, result);
            System.arraycopy(result, 0, solutions, 0, solutions.length);
        }
        return result;
    }

    private double[] matrixMultiplication(double[][] matrix1, double[] matrix2){
        double[] result = new double[matrix2.length];
        Arrays.fill(result, 0);
        for (int i = 0; i < matrix1.length; i++){
            for (int j = 0; j < matrix1.length; j++){
                result[i] = result[i] + matrix1[i][j]*matrix2[j];
            }
        }
        return result;
    }

    private double[][] reverseMatrix(double[][] matrix) throws IncorrectDataException {
        double[][] result = new double[matrix.length][matrix.length];
        if (matrix[0].length >= 3) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    result[i][j] = Math.pow(-1, i + j) * findDeterminant(findMinor(matrix, i + 1, j + 1));
                }
            }
        }else{
            result[0][0] = matrix[1][1];
            result[0][1] = -1*matrix[0][1];
            result[1][0] = -1*matrix[1][0];
            result[1][1] = matrix[0][0];
        }
        return result;
    }
    private double[][] findMinor(double[][] matrix, int row, int column){
        double[][] result = new double[matrix.length-1][matrix.length-1];
        double[][] temp = new double[matrix.length-1][matrix.length];
        boolean isRow = false;
        for (int i = 0; i < matrix.length; i++){
            if (i == row-1 && !isRow){
                isRow = true;
                continue;
            }
            if (isRow) {
                System.arraycopy(matrix[i], 0, temp[i-1], 0, matrix.length);
            } else System.arraycopy(matrix[i], 0, temp[i], 0, matrix.length);
        }
        isRow = false;
        int c = 0;
        for (int i = 0; i < temp.length; i++){
            if (i == row-1 && !isRow){
                isRow = true;
                continue;
            }
            if (isRow) {
                for (int j = 0; j < temp.length; j++) {
                    if (j != column - 1) {
                        result[i-1][c] = matrix[i][j];
                        c++;
                    }
                }
            }else {
                for (int j = 0; j < temp.length; j++) {
                    if (j != column - 1) {
                        result[i][c] = matrix[i][j];
                        c++;
                    }
                }
            }
            c = 0;
        }
        return result;
    }

    private double findDeterminant(double[][] matrix) throws IncorrectDataException{
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
            throw new IncorrectDataException("Детерминант матрицы равен 0, введите другие значения");
        }
        return result;
    }

    private void elementValidationAndModification(double[][] matrix, int rowIndex, int columnIndex) throws IncorrectDataException {
        if (matrix[rowIndex][columnIndex] == 0) {
            double columnSum = 0;
            for (int i = 1; i < matrix.length; i++) {
                columnSum += matrix[i - 1][columnIndex];
            }
            if (columnSum == 0) {
                throw new IncorrectDataException("В матрице имеется нулевой столбец. Измените введенные даныне.");
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

    public IOHandler getIoHandler() {
        return ioHandler;
    }


}
