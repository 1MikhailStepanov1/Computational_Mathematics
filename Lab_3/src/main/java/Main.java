
import exceptions.IncorrectCalculationException;
import io.IOHandler;
import solvers.IntegralSolver;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IntegralSolver solver = new IntegralSolver();
        IOHandler ioHandler = new IOHandler(scanner);

        ioHandler.inputFunction();
        ioHandler.inputEpsilon();
        ioHandler.inputBounds();

        try {
            ioHandler.printAnswer(solver.trapezoidalMethod());
        } catch (IncorrectCalculationException e) {
            System.out.println(e.getMessage());
        }
    }
}
