import exceptions.IncorrectInputException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IOHandler {

    Scanner scanner;

    public IOHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public void printLines() {
        System.out.println("--------------------------------");
    }

    public void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double number : row) {
                System.out.format(" %f", number);
            }
            System.out.println();
        }
    }

    public double[][] readMatrix(int matrixSize) throws IncorrectInputException, NumberFormatException {
        double[][] result = new double[matrixSize][matrixSize];
        String[] rowCoefficients;
        String string = null;
        for (int i = 0; i < matrixSize; i++) {
            string = scanner.nextLine();
            rowCoefficients = string.trim().replaceAll("\\s+", " ").split(" ");
            if (rowCoefficients.length < matrixSize) {
                throw new IncorrectInputException("Количество коэффициентов в строке не соответсвует введенному размеру матрицы");
            }
            for (int j = 0; j < matrixSize; j++) {
                result[i][j] = Double.parseDouble(rowCoefficients[j]);
            }
        }
        return result;
    }

    public double[] readEquationsRoots(int matrixSize) throws IncorrectInputException, NumberFormatException {
        double[] result = new double[matrixSize];
        String[] rowData = scanner.nextLine().trim().replaceAll("\\s+", " ").split(" ");
        if (rowData.length < matrixSize) {
            throw new IncorrectInputException("Количество корней в строке не соответсвует введенному размеру матрицы");
        }
        for (int i = 0; i < matrixSize; i++) {
            result[i] = Double.parseDouble(rowData[i]);
        }
        return result;
    }

    public void printFormatAnswer(double[] answers, double epsilon) {
        for (double answer : answers) {
            System.out.format(" %f", answer);
        }
        System.out.format(" %f", epsilon);
        System.out.println();
        printLines();
    }

    public void printAnswer(double[] result, double epsilon, int iterationNumber) {
        System.out.println("Ответ:");
        for (int i = 1; i <= result.length; i++) {
            System.out.println("x" + i + " = " + result[i - 1]);
        }
        System.out.println("Конечная точность: " + epsilon);
        System.out.println("Номер итерации: " + iterationNumber);
    }

    public void readInputFromFile(Data data) throws IncorrectInputException {
        String fileName;
        File file;
        System.out.println("Введите абсолютный путь к файлу:");
        while (true) {
            if (scanner.hasNextLine()) {
                fileName = scanner.nextLine();
                try {
                    file = new File(fileName);
                    if (!file.canRead()) {
                        System.out.println("Не получается прочитать файл!");
                    } else break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException exception) {
            throw new IncorrectInputException("Файл не найден. Проверьте входные данные.");
        }
        scanner = new Scanner(inputStream);
        try {
            data.setEpsilon(Double.parseDouble(scanner.nextLine()));
            data.setMatrixSize(Integer.parseInt(scanner.nextLine()));
        } catch (NullPointerException | NumberFormatException exception){
            throw new IncorrectInputException("Точность и размер матрицы неверны.");
        }
        if (data.getMatrixSize() > 20 || data.getMatrixSize() < 3)
            throw new IncorrectInputException("Введен неверный размер матрицы");
        data.setMatrix(new double[data.getMatrixSize()][data.getMatrixSize()]);
        data.setEquationsRoots(new double[data.getMatrixSize()]);
        data.setMatrix(readMatrix(data.getMatrixSize()));
        data.setEquationsRoots(readEquationsRoots(data.getMatrixSize()));
    }
}
