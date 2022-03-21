package main;

import data.Data;
import equationSystem.EquationSystemSolver;
import equations.EquationSolvingMethods;
import exceptions.IncorrectDataException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IOHandler ioHandler = new IOHandler(scanner);
        EquationSolvingMethods solver = new EquationSolvingMethods();
        EquationSystemSolver systemSolver = new EquationSystemSolver(ioHandler);
        ioHandler.printEquations();
        ioHandler.setEquationType();
        ioHandler.setIntervalsAndEpsilon();
        try {
            double resultChords = solver.chordsMethod();
            double resultTangent = solver.tangentsMethod();
            ioHandler.printEquationResults(resultChords, resultTangent);
        } catch (IncorrectDataException e) {
            System.out.println(e.getMessage());
        }

        ioHandler.printSystems();
        ioHandler.setSystemType();
        ioHandler.setSystemEpsilonAndRoots();
        try {
            systemSolver.solveWithNewtonMethod(Data.getSystemRoots());
        } catch (IncorrectDataException e) {
            System.out.println(e.getMessage());
        }

    }
}
