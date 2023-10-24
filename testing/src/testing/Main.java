package testing;

public class Main {
	    public static void main(String[] args) {
	        // Crear dos matrices
	        Matrix matrixA = new Matrix(3, 2);
	        Matrix matrixB = new Matrix(3, 2);

	        // Llenar las matrices con datos
	        double[][] dataA = {
	            {1.0, 2.0},
	            {3.0, 4.0},
	            {5.0, 6.0}
	        };

	        double[][] dataB = {
	            {7.0, 8.0},
	            {9.0, 10.0},
	            {11.0, 12.0}
	        };

	        matrixA.fillMatrix(dataA);
	        matrixB.fillMatrix(dataB);

	        // Imprimir las matrices
	        System.out.println("Matrix A:");
	        matrixA.printMatrix();

	        System.out.println("Matrix B:");
	        matrixB.printMatrix();

	        // Realizar operaciones de suma y resta
	        Matrix sumResult = matrixA.sum(matrixB);
	        Matrix subResult = matrixA.subt(matrixB);

	        System.out.println("Suma de matrices A y B:");
	        sumResult.printMatrix();

	        System.out.println("Resta de matrices A y B:");
	        subResult.printMatrix();

	        // Realizar multiplicación de matrices
	        double[][] dataC = {
	            {1.0, 2.0, 3.0},
	            {4.0, 5.0, 6.0}
	        };

	        Matrix matrixC = new Matrix(2, 3);
	        matrixC.fillMatrix(dataC);

	        Matrix multResult = matrixA.multi(matrixC);

	        System.out.println("Multiplicación de matrices A y C:");
	        multResult.printMatrix();
	    }
	}

