package io;

import data.Data;

import java.util.Scanner;

public class IOHandler {
    private final Scanner scanner;

    public IOHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public void printAnswer(double answer) {
        System.out.println("Ответ: " + answer);
    }

    public void inputFunction() {
        System.out.println("Choose function");
        System.out.println("1) x^2");
        System.out.println("2) 1/x");
        System.out.println("3) 3*exp(x^2 - x)");
        Data.setFunctionType(scanner.nextInt());
    }

    public void inputEpsilon() {
        System.out.println("Set epsilon:");
        Data.setEpsilon(scanner.nextDouble());
    }

    public void inputBounds() {
        System.out.println("Upper bound");
        Data.setUpperBound(scanner.nextDouble());
        System.out.println("Lower bound");
        Data.setLowerBound(scanner.nextDouble());
    }
}
