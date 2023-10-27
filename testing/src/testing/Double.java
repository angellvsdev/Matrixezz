package testing;

public abstract class MatrixElement {
    public abstract MatrixElement add(MatrixElement other);
    public abstract MatrixElement subtract(MatrixElement other);
    public abstract MatrixElement multiply(MatrixElement other);
    public abstract MatrixElement divide(MatrixElement other);
}
