package testing;

public class Main {
	    public static void main(String[] args) {
	        // Crear dos matrices
	        Matrix matrixA = new Matrix(3, 4);
	        Matrix matrixB = new Matrix(3, 2);

	        // Llenar las matrices con datos
	        double[][] dataA = {
	            {2.0, 3.0, -1.0, 1.0},
	            {4.0, 2.0,2.0,2.0},
	            {3.0, 2.0,3.0,3.0}
	        };

	        double[][] dataB = {
	            {7.0, 8.0},
	            {9.0, 10.0},
	            {11.0, 12.0}
	        };

	        matrixA.fillMatrix(dataA);
	        matrixB.fillMatrix(dataB);
	        matrixA.printMatrix();
	        matrixB.printMatrix();
	        System.out.println("La matrix escalonada es:");
	        Matrix matrixC = matrixA.gaussianElimination();
	        matrixC.printMatrix();
	    }
	}

