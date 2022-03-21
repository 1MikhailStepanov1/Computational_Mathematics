package data;

import java.util.Arrays;

public class Data {
    static double epsilonEquation;
    static double epsilonSystem;
    static int equationType;
    static int systemType;
    static double a;
    static double b;
    static double[] systemRoots;
    public Data() {
    }


    public static void setEpsilonEquation(double epsilon) {
        Data.epsilonEquation = epsilon;
    }

    public static double getEpsilonEquation() {
        return epsilonEquation;
    }

    public static void setEpsilonSystem(double epsilon) {
        Data.epsilonSystem = epsilon;
    }

    public static double getEpsilonSystem() {
        return epsilonSystem;
    }

    private static double getFirstEquation(double x, int order) {
        if (order == 0) {
            return Math.pow(x, 3) - x + 4;
        } else if (order == 1) {
            return Math.pow(x, 2) * 3 - 1;
        } else return 6 * x;
    }

    private static double getSecondEquation(double x, int order){
        if (order == 0) {
            return Math.log(Math.abs(x)) - 0.05*Math.pow(Math.E, x);
        } else if (order == 1) {
            return 1/x - 0.05*Math.pow(Math.E, x);
        } else return -1/Math.pow(x,2)-0.05*Math.pow(Math.E, x);
    }

    private static double getThirdEquation(double x, int order){
        if (order == 0) {
            return 2*Math.pow(x, 3) / (x*(Math.pow(x, 2) - 4)) + 5;
        } else if (order == 1) {
            return -4*Math.pow(x,3)/Math.pow(Math.pow(x, 2)-4,2) + (4*x)/(Math.pow(x,2)-4);
        } else return 16*Math.pow(x, 4) - 20*Math.pow(x, 2)/(Math.pow(Math.pow(x, 2)-4,2))+4/(Math.pow(x,2)-4);
    }

    private static double getFourthEquation(double x, int order){
        if (order == 0) {
            return 2*Math.pow(x, 1.6667) - 5*Math.pow(x, 0.6667) + 1;
        } else if (order == 1) {
            return 10*Math.pow(x, 0.6667)/3 - 10/(3*Math.pow(x, 0.3333));
        } else return 20/(9*Math.pow(x, 0.3333) + 10/(9*Math.pow(x, 1.33333)));
    }

    private static double getFifthEquation(double x, int order){
        if (order == 0) {
            return Math.log(2-Math.cos(3*x)) - 0.5;
        } else if (order == 1) {
            return 3*(Math.sin(3*x)/(2-Math.cos(3*x)));
        } else return 9*Math.cos(3*x)/(2-Math.cos(3*x)) - 9*Math.pow(Math.sin(3*x),2)/Math.pow(2-Math.cos(3*x),2);
    }

    public static double getEquation(double x, int order) {
        switch (equationType) {
            case 1:
                return getFirstEquation(x, order);
            case 2:
                return getSecondEquation(x, order);
            case 3:
                return getThirdEquation(x, order);
            case 4:
                return getFourthEquation(x, order);
            case 5:
                return getFifthEquation(x, order);
            default:
                return 0;
        }
    }

    public static double[] getSystem(double[] x, int dataType) {
        switch (systemType) {
            case 1:
                return getFirstSystem(x[0], x[1], dataType);
            case 2:
                return getSecondSystem(x[0], x[1], dataType);
            case 3:
                return getThirdSystem(x[0], x[1], x[2], dataType);
            default:
                return null;
        }
    }

    private static double[] getFirstSystem(double x1, double x2, int dataType) {
        if (dataType == 0) {
            return new double[]{Math.pow(x1, 2) + Math.pow(x2, 2) - 4, -3 * Math.pow(x1, 2) + x2};
        } else if (dataType == 1) {
            return new double[]{2 * x1, 2 * x2};
        } else return new double[]{-6 * x1, 1};
    }

    private static double[] getSecondSystem(double x1, double x2, int dataType) {
        if (dataType == 0) {
            return new double[]{x1 + 3*Math.log10(x1) - Math.pow(x2, 2), 2*Math.pow(x1, 2) - x1*x2 - 5*x1 +1};
        } else if (dataType == 1) {
            return new double[]{1+(3*0.43429/x1), -2*x2};
        } else return new double[]{4*x1-x2-5, -x1};
    }

    private static double[] getThirdSystem(double x1, double x2, double x3, int dataType){
        if (dataType == 0) {
            return new double[]{Math.pow(x1, 2) + Math.pow(x2, 2) + Math.pow(x3, 2)-1, 2*Math.pow(x1, 2) + Math.pow(x2, 2) - 4*x3, 3*Math.pow(x1, 2) - 4*x2 + Math.pow(x3, 2)};
        } else if (dataType == 1) {
            return new double[]{2*x1, 2*x2, 2*x3};
        } else if (dataType == 2){
            return new double[]{4*x1, 2*x2, -4};
        } else return new double[]{6*x1, -4, 2*x3};
    }

    public static void setEquationType(int type) {
        equationType = type;
    }

    public static void setSystemType(int type) {
        systemType = type;
    }

    public static void setA(double tempA) {
        a = tempA;
    }

    public static void setB(double tempB) {
        b = tempB;
    }

    public static double getA() {
        return a;
    }

    public static double getB() {
        return b;
    }

    public static double[] getSystemRoots() {
        return systemRoots;
    }

    public static void setDefaultSystemRoots() {
        switch (systemType) {
            case 1:
                systemRoots = new double[2];
                Arrays.fill(systemRoots, 0);
                return;
            case 2:
                systemRoots = new double[2];
                Arrays.fill(systemRoots, 0);
                return;
            case 3:
                systemRoots = new double[3];
                Arrays.fill(systemRoots, 0);
                return;
            default:
                return;
        }
    }

    public static void setSystemRoots(double[] systemRoots) {
        Data.systemRoots = systemRoots;
    }

    public static int getSystemType() {
        return systemType;
    }
}
