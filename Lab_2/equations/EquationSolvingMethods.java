package equations;

import data.Data;
import exceptions.IncorrectDataException;

public class EquationSolvingMethods {

    public EquationSolvingMethods() {
    }

    public double chordsMethod() throws IncorrectDataException {
        double startX;
        if (!isDifferentSigns(Data.getA(), Data.getB())) {
            throw new IncorrectDataException("Не выполняется одно из достаточных условий сходимости");
        }
        startX = getNextXForChordsMethod(Data.getA(), Data.getB());
        getNewInterval(Data.getA(), Data.getB(), startX);
        while (Math.abs(getNextXForChordsMethod(Data.getA(), Data.getB()) - startX) > Data.getEpsilonEquation() &&
                Math.abs(Data.getEquation(getNextXForChordsMethod(Data.getA(), Data.getB()), 0)) > Data.getEpsilonEquation()) {
            startX = getNextXForChordsMethod(Data.getA(), Data.getB());
            getNewInterval(Data.getA(), Data.getB(), startX);
        }
        startX = getNextXForChordsMethod(Data.getA(), Data.getB());
        return startX;
    }

    private double getNextXForChordsMethod(double a, double b) {
        double result;
        result = a - (b - a) / (Data.getEquation(b, 0) - Data.getEquation(a, 0)) * Data.getEquation(a, 0);
        return result;
    }

    private void getNewInterval(double a, double b, double x) throws IncorrectDataException {
        if (Data.getEquation(a, 0) * Data.getEquation(x, 0) < 0) {
            Data.setB(x);
        } else if (Data.getEquation(x, 0) * Data.getEquation(b, 0) < 0) {
            Data.setA(x);
        } else throw new IncorrectDataException("Невозможно выбрать начальное приближение");
    }

    public double tangentsMethod() throws IncorrectDataException {
        double startX = 0;
        double nextX;
        if (!isDifferentSigns(Data.getA(), Data.getB())) {
            throw new IncorrectDataException("Не выполняется одно из достаточных условий сходимости");
        }
        if (Data.getEquation(Data.getA(), 0) * Data.getEquation(Data.getA(), 2) > 0) {
            startX = Data.getA();
        } else if (Data.getEquation(Data.getB(), 0) * Data.getEquation(Data.getB(), 2) > 0) {
            startX = Data.getB();
        } else throw new IncorrectDataException("Невозможно выбрать начальное приближение");
        nextX = findNewXForTangentsMethod(startX);
        while (Math.abs(nextX - startX) > Data.getEpsilonEquation() &&
                Math.abs(Data.getEquation(startX, 0) / Data.getEquation(nextX, 1)) > Data.getEpsilonEquation() &&
                Math.abs(Data.getEquation(startX, 0)) > Data.getEpsilonEquation()) {
            startX = nextX;
            nextX = findNewXForTangentsMethod(startX);
        }
        if (Math.abs(nextX - startX) > Data.getEpsilonEquation()) {
            Data.setEpsilonEquation(Math.abs(nextX - startX));
        } else if (Math.abs(Data.getEquation(nextX, 0) / Data.getEquation(nextX, 1)) > Data.getEpsilonEquation()) {
            Data.setEpsilonEquation(Math.abs(Data.getEquation(nextX, 0) / Data.getEquation(nextX, 1)));
        } else Data.setEpsilonEquation(Math.abs(Data.getEquation(nextX, 0)));
        return nextX;
    }

    private boolean isDifferentSigns(double a, double b) {
        return (Data.getEquation(a, 0) * Data.getEquation(b, 0)) < 0;
    }

    private double findNewXForTangentsMethod(double prevX) {
        return prevX - Data.getEquation(prevX, 0) / Data.getEquation(prevX, 1);
    }

}
