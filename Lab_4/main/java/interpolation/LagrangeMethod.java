package interpolation;

import data.Dot;
import data.FunctionHandler;
import data.InputFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class LagrangeMethod {

    private final InputFunction fun;

    public LagrangeMethod() {
        fun = FunctionHandler.getInputFunction();
    }

    public InputFunction lagrange(double low, double up){
        Function<Double, Double> newFunc = lagrangeFunctional();
        List<Dot> newDots = new ArrayList<>();
        double step = Math.abs((up - low)/128);
        for (int i = 0; i <=128; i++){
            double x = low + step*i+getNoise();
            newDots.add(new Dot(x,newFunc.apply(x)));
        }
        return new InputFunction(newDots);
    }

    public  Function<Double, Double> lagrangeFunctional(){
        return x -> {
            double result = 0d;
            for (int i = 0; i < fun.getDotList().size(); i++) {
                double tempResult = 1d;
                for (int j = 0; j < fun.getDotList().size(); j++) {
                    if (i != j) {
                        tempResult = tempResult * (x - fun.getArrayX()[j]);
                        tempResult = tempResult / (fun.getArrayX()[i] - fun.getArrayX()[j]);
                    }
                }
                tempResult = tempResult * fun.getArrayY()[i];
                result = result + tempResult;
            }
            return result;
        };
    }

    private double getNoise(){
        return Math.random()*0.001;
    }

    public InputFunction getFun() {
        return fun;
    }
}
