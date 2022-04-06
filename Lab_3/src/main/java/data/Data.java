package data;

public class Data {
    static double upperBound;
    static double lowerBound;
    static int functionType;
    static double epsilon;

    public static Double getFunction(double x, int order) {
        switch (functionType) {
            case 1:
                return getFirstFunction(x, order);
            case 2:
                return getSecondFunction(x, order);
            case 3:
                return getThirdFunction(x, order);
            default:
                return 0d;
        }
    }

    private static double getFirstFunction(double x, int order) {
        if (order == 0) {
            return Math.pow(x, 2);
        } else return 2;
    }

    private static double getSecondFunction(double x, int order) {
        if (order == 0) {
            return 1 / x;
        } else return 2 / Math.pow(x, 3);
    }

    private static double getThirdFunction(double x, int order) {
        double exp = Math.exp(Math.pow(x, 2) - x);
        if (order == 0) {
            return 3* exp;
        } else return (2*x-1)*(6*x-3)* exp + 6* exp;
    }

    public static void setEpsilon(double epsilon) {
        Data.epsilon = epsilon;
    }

    public static double getEpsilon() {
        return epsilon;
    }

    public static void setFunctionType(int functionType) {
        Data.functionType = functionType;
    }

    public static int getFunctionType() {
        return functionType;
    }

    public static void setUpperBound(double upperBound) {
        Data.upperBound = upperBound;
    }

    public static void setLowerBound(double lowerBound) {
        Data.lowerBound = lowerBound;
    }

    public static double getLowerBound() {
        return lowerBound;
    }

    public static double getUpperBound() {
        return upperBound;
    }
}
