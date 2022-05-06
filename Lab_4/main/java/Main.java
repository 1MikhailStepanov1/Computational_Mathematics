import data.FunctionHandler;
import drawer.Drawer;
import interpolation.LagrangeMethod;
import io.IOHandler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IOHandler.setScanner(scanner);
        int funNum = IOHandler.readFunctionNumber();
        double low = IOHandler.readLowerBound();
        double up = IOHandler.readUpperBound();
        FunctionHandler functionHandler = new FunctionHandler(funNum, low, up);
        LagrangeMethod lagrangeMethod = new LagrangeMethod();
//        System.out.println();
//        IOHandler.outDots(lagrangeMethod.lagrange(low, up).getDotList());
        Drawer.drawResult(FunctionHandler.getInputFunction(), lagrangeMethod.lagrange(low, up));
        while (true){
            System.out.println("Введите x:");
            double x = scanner.nextDouble();
            System.out.println("y = " + lagrangeMethod.lagrangeFunctional().apply(x));
        }
    }
}
