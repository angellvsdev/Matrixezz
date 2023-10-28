package testing;

public class Main {
    public static void main(String[] args) {
    	Matrix matriz = Matrix.generateRandomMatrix(3,4,0,2);
    	Matrix matrix1 = matriz.gaussianElimination();
    }
}