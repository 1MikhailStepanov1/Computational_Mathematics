package solvers;

import data.Data;
import exceptions.IncorrectCalculationException;

import java.util.LinkedHashSet;

public class IntegralSolver {

    public double trapezoidalMethod() throws IncorrectCalculationException {
        LinkedHashSet<Double> list = new LinkedHashSet<Double>();
        double prevResult = 0;
        double result = Double.MIN_VALUE + 2;
        double error = Double.MIN_VALUE;
        int n = 2;
        while (Math.abs((result - prevResult)) > Data.getEpsilon()) {
            prevResult = result;
            result = 0;
            double step = (Data.getUpperBound() - Data.getLowerBound()) / n;
            for (int i = 0; i < n; i++) {
                double temp = 0;

                double leftY = Data.getFunction(Data.getLowerBound() + step * i, 0);
                double rightY = Data.getFunction(Data.getLowerBound() + step * (i + 1), 0);

                boolean isNormalLeftY = Double.isFinite(leftY);
                boolean isNormalRightY = Double.isFinite(rightY);

                if (!isNormalRightY) {
                    temp = Data.getFunction(Data.getLowerBound() + step * (i + 1), 2);
                    if (temp > error) {
                        error = temp;
                    }
                } else if (!isNormalLeftY) {
                    temp = Data.getFunction(Data.getLowerBound() + step * i, 2);
                    if (temp > error) {
                        error = temp;
                    }
                } else result = result + leftY + rightY;
            }
            result *= (step / 2);
            n *= 2;
        }
        if (Math.abs(error) > Math.abs(result)) {
            throw new IncorrectCalculationException("Коэффициент R больше, чем полученный результат. Возможно, интеграл расходится.");
        }
        return result;
    }

}
