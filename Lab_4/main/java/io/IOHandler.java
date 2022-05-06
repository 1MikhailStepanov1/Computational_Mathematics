package io;

import data.Dot;

import java.util.List;
import java.util.Scanner;

public class IOHandler {

    public static Scanner scanner;

    public static void outDots(List<Dot> dots){
        for (Dot dot : dots){
            System.out.println("x: "+ dot.getX() + " y: " + dot.getY());
        }
    }

    public static int readFunctionNumber(){
        System.out.println("Выберите функцию: ");
        System.out.println("1) 3x - 7");
        System.out.println("2) 2x^2 - x -2");
        System.out.println("3) log(x)");
        System.out.println("4) 2*cos(x/2)");
        return scanner.nextInt();
    }
L
    public static double readLowerBound() {
        System.out.println("Введите нижнюю границу отрезка:");
        return scanner.nextDouble();
    }

    public static double readUpperBound(){
        System.out.println("Введите верхнюю границу отрезка:");
        return scanner.nextDouble();
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner) {
        IOHandler.scanner = scanner;
    }
}
