package data;

import java.util.ArrayList;
import java.util.List;

public class FunctionHandler {
    public static double lowerBound;
    public static double upperBound;

    private static InputFunction inputFunction;

    public FunctionHandler(int funNum, double lowerBound, double upperBound){
        FunctionHandler.setLowerBound(lowerBound);
        FunctionHandler.setUpperBound(upperBound);
        inputFunction = new InputFunction(generateDots(funNum, FunctionHandler.getLowerBound(), FunctionHandler.getUpperBound()));
//        IOHandler.outDots(inputFunction.getDotList());
    }

    private List<Dot> generateDots(int funNum, double lowerBound, double upperBound){
        List<Dot> result = new ArrayList<>();
        switch (funNum){
            case 1:
                double step1 = Math.abs((upperBound - lowerBound)/64);
                for (int i = 0; i <=64; i++){
                    double x = lowerBound + step1*i;
                    result.add(new Dot(x, 3*x - 7));
                }
                break;
            case 2:
                double step2 = Math.abs((upperBound - lowerBound)/64);
                for (int i = 0; i <=64; i++){
                    double x = lowerBound + step2*i;
                    result.add(new Dot(x, 2*Math.pow(x,2) - x - 2));
                }
                break;
            case 3:
                double step3 = Math.abs((upperBound - lowerBound)/64);
                for (int i = 0; i <=64; i++){
                    double x = lowerBound + step3*i;
                    result.add(new Dot(x, Math.log(x)));
                }
                break;
            case 4:
                double step4 = Math.abs((upperBound - lowerBound)/64);
                for (int i = 0; i <=64; i++){
                    double x = lowerBound + step4*i;
                    result.add(new Dot(x, 2*Math.cos(x/2)));
                }
                break;
        }
        return result;
    }

    public static InputFunction getInputFunction() {
        return inputFunction;
    }

    public static double getLowerBound() {
        return lowerBound;
    }

    public static void setLowerBound(double lowerBound) {
        FunctionHandler.lowerBound = lowerBound;
    }

    public static double getUpperBound() {
        return upperBound;
    }

    public static void setUpperBound(double upperBound) {
        FunctionHandler.upperBound = upperBound;
    }

}
