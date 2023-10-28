package testing;

public class Main {
    public static void main(String[] args) {
    	Matrix matriz = Matrix.generateRandomMatrix(2,2,1,2);
    	Matrix matriz2 = Matrix.generateRandomMatrix(2, 2,1,2); 
    	System.out.println("Matriz 1");
    	matriz.printMatrix();
    	System.out.println("Matriz 2");
    	matriz2.printMatrix();/*
    	System.out.println("Suma");
    	Matrix suma = matriz.add(matriz2);
    	suma.printMatrix();
    	System.out.println("Resta");
    	Matrix resta = matriz.subtract(matriz2);
    	resta.printMatrix();
    	System.out.println("Matriz 1");
    	matriz.printMatrix();
    	System.out.println("Matriz 2");
    	matriz2.printMatrix();*/
    		System.out.println("Multiplicacion");
        Matrix multiplicacion = matriz.multiply(matriz2);
        multiplicacion.printMatrix();
        /*
        System.out.println("Division");
        Matrix division = matriz.divide(matriz2);
        division.printMatrix();
        System.out.println("Determinante");
        System.out.println(matriz.calculateDeterminant());
        System.out.println("Cofactor");*/
    	
    }
}