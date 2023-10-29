package testing;

public class Main {
    public static void main(String[] args) {
    	Matrix matrix = Matrix.generateRandomMatrix(3, 4, 1, 10);
    	Matrix matrix2 = matrix.gaussJordanEliminationWithSteps();
    	Matrix matrix3 = matrix.gaussianElimination();
    	Matrix matrix4 = matrix.gaussJordanElimination();
    	matrix4.printMatrix();
    }
}