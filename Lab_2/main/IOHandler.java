package main;

import data.Data;
import exceptions.IncorrectDataException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IOHandler {
    final Scanner scanner;

    public IOHandler(Scanner scanner) {
        this.scanner = scanner;
    }


    public void printSystemIteration(int iteration, double[] previous, double[] current) {
        printLines();
        System.out.print("Iteration:" + iteration + " | ");
        for (int i = 0; i < previous.length; i++) {
            System.out.print("x" + (i + 1) + ": " + current[i] + " | ");
            System.out.print("epsilon: " + (current[i] - previous[i]) + " | ");
        }
        System.out.println();
    }

    public void printLines() {
        System.out.println("--------------------------------");
    }

    public void printEquations() {
        System.out.println("Выберите уравнение для решения: ");
        System.out.println("1) x^3 - x + 4 = 0");
        System.out.println("2) ln|x| - 0.05*e^x");
        System.out.println("3) 2x^3 / (x * (x^2-4)) + 5");
        System.out.println("4) 2*x^(5/3) - 5*x^(2/3) + 1");
        System.out.println("5) ln(2-cos(3x)) - 0.5");
    }

    public void setEquationType() {
        while (true) {
            try {
                int temp = scanner.nextInt();
                if (temp < 1 || temp > 5) {
                    throw new IncorrectDataException("Введите число!");
                }
                Data.setEquationType(temp);
                break;
            } catch (NumberFormatException | InputMismatchException exception) {
                System.out.println("Введите число!");
                continue;
            } catch (IncorrectDataException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
    }

    public void setIntervalsAndEpsilon() {
        System.out.println("Введите интервал:");
        while (true) {
            try {
                Data.setA(scanner.nextDouble());
                Data.setB(scanner.nextDouble());
                break;
            } catch (NumberFormatException | InputMismatchException exception) {
                System.out.println("Введите число!");
                continue;
            }
        }
        System.out.println("Введите эпсилон:");
        while (true) {
            try {
                Data.setEpsilonEquation(scanner.nextDouble());
                break;
            } catch (NumberFormatException | InputMismatchException exception) {
                System.out.println("Введите число!");
                continue;
            }
        }
    }

    public void printEquationResults(double chords, double tangents){
        System.out.println("Ответ решением методом хорд: " + chords);
        System.out.println("Ответ решением методом касательных: " + tangents);
        System.out.println("Разница: " + Math.abs(chords - tangents));
    }

    public void printSystems(){
        System.out.println("Выберите систему для решения: ");
        System.out.println("1) x1^2 + x2^2 = 4");
        System.out.println("   x2 = 3*x1^2");
        System.out.println("2) x1 + 3*lg(x1) - x2^2 = 0");
        System.out.println("   2*x1^2 - x1*x2 - 5*x1 + 1 = 0");
        System.out.println("3) x1^2 + x2^2 + x3^2 = 1");
        System.out.println("   2*x1^2 + x1^2 - 4*x3^2 = 0");
        System.out.println("   3*x1^2 - 4*x2 + x3^2 = 0");
    }

    public void setSystemType() {
        while (true) {
            try {
                int temp = scanner.nextInt();

                if (temp < 1 || temp > 3) {
                    throw new IncorrectDataException("Введите число!");
                } else Data.setSystemType(temp);
                break;
            } catch (NumberFormatException exception) {
                System.out.println("Введите число!");
                continue;
            } catch (IncorrectDataException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
    }

    public void setSystemEpsilonAndRoots(){
        System.out.println("Введите эпсилон:");
        while (true) {
            try {
                Data.setEpsilonEquation(scanner.nextDouble());
                break;
            } catch (NumberFormatException exception) {
                System.out.println("Введите число!");
                continue;
            }
        }
        System.out.println("Введите примерные корни системы:");
        Data.setDefaultSystemRoots();
        System.out.println(Data.getSystemRoots().length);
        while (true) {
            try {
                double[] temp = new double[Data.getSystemRoots().length];
                for (int i = 0; i < temp.length; i++){
                    temp[i] = scanner.nextDouble();
                }
                Data.setSystemRoots(temp);
                break;
            } catch (NumberFormatException exception) {
                System.out.println("Введите число!");
                continue;
            }
        }
    }
}
