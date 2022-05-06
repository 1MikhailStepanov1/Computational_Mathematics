package data;

import java.util.List;

public class InputFunction {

    private List<Dot> dotList;

    public InputFunction(List<Dot> dots){
        dotList = dots;
    }

    public double[] getArrayX() {
        return getDotList().stream()
                .mapToDouble(Dot::getX)
                .toArray();
    }

    public double[] getArrayY() {
        return getDotList().stream()
                .mapToDouble(Dot::getY)
                .toArray();
    }

    public List<Dot> getDotList() {
        return dotList;
    }
}
