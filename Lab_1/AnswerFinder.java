import exceptions.IncorrectInputException;

public class AnswerFinder {
    IOHandler ioHandler;
    MatrixHandler matrixHandler;
    public AnswerFinder(IOHandler ioHandler, MatrixHandler matrixHandler){
        this.ioHandler = ioHandler;
        this.matrixHandler = matrixHandler;
    }
    public void calculateResult(double[][] matrix, double[] equationsRoots, double epsilon) throws IncorrectInputException {
        double[][] matrixCopy = matrixHandler.cloneMatrix(matrix);
        System.out.println("Введенная матрица:");
        ioHandler.printLines();
        ioHandler.printMatrix(matrix);
        ioHandler.printLines();
        System.out.println("Приведем к треугольному виду и найдем детерминант:");
        ioHandler.printLines();
        System.out.println(matrixHandler.findDeterminant(matrixCopy));
        ioHandler.printLines();
        ioHandler.printMatrix(matrixCopy);
        ioHandler.printLines();
        System.out.println("Проведем диагональное выравнивание");
        matrixHandler.diagonalAlignment(matrix, equationsRoots);
        ioHandler.printLines();
        ioHandler.printMatrix(matrix);
        ioHandler.printLines();
        System.out.println("Проведем нормализацию матрицы");
        matrixHandler.matrixAndRootsNormalization(matrix, equationsRoots);
        ioHandler.printLines();
        ioHandler.printMatrix(matrix);
        ioHandler.printLines();
        System.out.println("Проверим условие сходимости");
        if (matrixHandler.convergenceCheck(matrix)) {
            ioHandler.printLines();
            System.out.println("Условие сходимости выполненяется");
        }
        ioHandler.printLines();
        for (int i = 1; i <= equationsRoots.length; i++) {
            System.out.print(" (x" + i + ")^k |");
        }
        System.out.println(" max|(Xi)^k - (Xi)^(k-1)|");
        ioHandler.printLines();
        double[] previousIteration = matrixHandler.cloneFreeCoefficients(equationsRoots);
        int iterationNumber = 1;
        while (matrixHandler.calculateAnswer(matrix, previousIteration, equationsRoots, epsilon, iterationNumber)) {
            iterationNumber += 1;
        }
    }

    public void calculateResultWithoutOutputs(double[][] matrix, double[] equationsRoots, double epsilon) throws IncorrectInputException {
        double[][] matrixCopy = matrixHandler.cloneMatrix(matrix);
        matrixHandler.findDeterminant(matrixCopy);
        matrixHandler.diagonalAlignment(matrix, equationsRoots);
        matrixHandler.matrixAndRootsNormalization(matrix, equationsRoots);
        matrixHandler.convergenceCheck(matrix);
        ioHandler.printLines();
        for (int i = 1; i <= equationsRoots.length; i++) {
            System.out.print(" (x" + i + ")^k |");
        }
        System.out.println(" max|(Xi)^k - (Xi)^(k-1)|");
        ioHandler.printLines();
        double[] previousIteration = matrixHandler.cloneFreeCoefficients(equationsRoots);
        int iterationNumber = 1;
        while (matrixHandler.calculateAnswer(matrix, previousIteration, equationsRoots, epsilon, iterationNumber)) {
            iterationNumber += 1;
        }
    }
}
