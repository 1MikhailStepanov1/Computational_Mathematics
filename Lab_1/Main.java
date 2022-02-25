import exceptions.IncorrectInputException;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static IOHandler ioHandler = new IOHandler(scanner);
    static MatrixHandler matrixHandler = new MatrixHandler(ioHandler);
    static AnswerFinder answerFinder = new AnswerFinder(ioHandler, matrixHandler);
    static int inputChoice;

    public Main() {
    }

    public static void main(String[] args) {
        Data data = new Data();
        System.out.println("Выберете метод ввода данных: 1 - ручной ввод, 2 - ввод из файла, 3 - генерация случайной матрицы А и столбца В");
        while (true) {
            try {
                inputChoice = Integer.parseInt(scanner.nextLine().trim());
                if (inputChoice == 1 || inputChoice == 2 || inputChoice == 3) {
                    break;
                } else {
                    System.out.println("Введите число от 1 до 3!");
                }
            } catch (NumberFormatException exception) {
                System.out.println("Введите число!");
            }
        }
        switch (inputChoice) {
            case 1:
                boolean isStopRequested = false;
                do {
                    System.out.println("Введите точность:");
                    try {
                        data.setEpsilon(Double.parseDouble(scanner.nextLine()));
                    } catch (NullPointerException | NumberFormatException exception) {
                        System.out.println("Введенные данные должны быть числом.");
                        continue;
                    }
                    System.out.println("Введите размеры матрицы из промежутка [3;20]: ");
                    try {
                        data.setMatrixSize(Integer.parseInt(scanner.nextLine()));
                    } catch (NullPointerException | NumberFormatException exception) {
                        System.out.println("Введенные данные должны быть числом.");
                        continue;
                    }
                    if (data.getMatrixSize() > 20 || data.getMatrixSize() < 3) {
                        System.out.println("Данные не соответсвуют промежутку. Попробуйте снова.");
                        continue;
                    }
                    while (true) {
                        System.out.println("Введите построчно матрицу:");
                        try {
                            data.setMatrix(ioHandler.readMatrix(data.getMatrixSize()));
                            System.out.println("Введите коэффициенты уравнения:");
                            data.setEquationsRoots(ioHandler.readEquationsRoots(data.getMatrixSize()));
                            break;
                        } catch (IncorrectInputException exception) {
                            System.out.println(exception.getMessage());
                        } catch (NumberFormatException exception) {
                            System.out.println("Данные должны быть численного типа.");
                        }
                    }
                    try {
                        answerFinder.calculateResult(data.getMatrix(), data.getEquationsRoots(), data.getEpsilon());
                    } catch (IncorrectInputException exception) {
                        System.out.println(exception.getMessage());
                        continue;
                    }
                    isStopRequested = true;
                } while (!isStopRequested);
                break;
            case 2:
                try {
                    ioHandler.readInputFromFile(data);
                    answerFinder.calculateResult(data.getMatrix(), data.getEquationsRoots(), data.getEpsilon());
                } catch (IncorrectInputException exception) {
                    System.out.println(exception.getMessage());
                    System.exit(1);
                }
                break;
            case 3:
                while (true) {
                    System.out.println("Введите точность:");
                    try {
                        data.setEpsilon(Double.parseDouble(scanner.nextLine()));
                    } catch (NullPointerException | NumberFormatException exception) {
                        System.out.println("Введенные данные должны быть числом.");
                        continue;
                    }
                    System.out.println("Введите размеры матрицы из промежутка [3;20]: ");
                    try {
                        data.setMatrixSize(Integer.parseInt(scanner.nextLine()));
                    } catch (NullPointerException | NumberFormatException exception) {
                        System.out.println("Введенные данные должны быть числом.");
                        continue;
                    }
                    if (data.getMatrixSize() > 20 || data.getMatrixSize() < 3) {
                        System.out.println("Данные не соответсвуют промежутку. Попробуйте снова.");
                        continue;
                    }
                    break;
                }
                double[][] newMatrix = new double[data.getMatrixSize()][data.getMatrixSize()];
                double[] newEquationsRoots = new double[data.getMatrixSize()];
                for (int i = 0; i < data.getMatrixSize(); i++) {
                    newEquationsRoots[i] = Math.random() * 100;
                    for (int j = 0; j < data.getMatrixSize(); j++) {
                        newMatrix[i][j] = Math.round(Math.random() * 100);
                    }
                }
                data.setMatrix(newMatrix);
                data.setEquationsRoots(newEquationsRoots);
                while (true) {
                    try {
                        answerFinder.calculateResultWithoutOutputs(data.getMatrix(), data.getEquationsRoots(), data.getEpsilon());
                        break;
                    } catch (IncorrectInputException ignored){}
                }
                break;
        }
    }

}
