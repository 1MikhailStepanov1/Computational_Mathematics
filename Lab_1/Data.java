public class Data {
    private int matrixSize;
    private double epsilon;
    private double[][] matrix;
    private double[] equationsRoots;

    public int getMatrixSize() {
        return matrixSize;
    }

    public void setMatrixSize(int matrixSize) {
        this.matrixSize = matrixSize;
    }

    public double getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double[] getEquationsRoots() {
        return equationsRoots;
    }

    public void setEquationsRoots(double[] equationsRoots) {
        this.equationsRoots = equationsRoots;
    }
}
