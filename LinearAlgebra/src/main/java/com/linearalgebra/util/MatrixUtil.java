package com.linearalgebra.util;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linearalgebra.entity.Matrix;
import com.linearalgebra.entity.Value;
import com.linearalgebra.service.ValueService;

@Component
public class MatrixUtil {
	@Autowired
	private ValueService valueService;

	public Matrix copyMatrix(Matrix matrix) {
		Value[][] originalData = matrix.getData();
		Matrix copiedMatrix = new Matrix(matrix.getRows(), matrix.getCols());
		copiedMatrix.setData(originalData);
		return copiedMatrix;
	}

	public boolean isSquare(Matrix matrix) {
		return matrix.getRows() == matrix.getCols();
	}

	/**
	 * Realiza la suma de dos filas de la matriz y devuelve una nueva fila como
	 * resultado.
	 *
	 * @param rowIndex1 Índice de la primera fila.
	 * @param rowIndex2 Índice de la segunda fila.
	 * @return Una nueva fila que representa la suma de las filas.
	 */
	public Value[] addRows(Matrix matrix, int rowIndex1, int rowIndex2) {
		if (rowIndex1 < 0 || rowIndex1 >= matrix.getRows() || rowIndex2 < 0 || rowIndex2 >= matrix.getRows()) {
			throw new IllegalArgumentException("Los índices de fila están fuera de rango");
		}

		Value[] resultRow = new Value[matrix.getCols()];

		for (int j = 0; j < matrix.getCols(); j++) {
			Value sum = valueService.add(matrix.getData(rowIndex1, j), matrix.getData(rowIndex2, j));
			resultRow[j] = sum;
		}

		return resultRow;
	}

	/**
	 * Realiza la resta de dos filas de la matriz y devuelve una nueva fila como
	 * resultado.
	 *
	 * @param rowIndex1 Índice de la primera fila.
	 * @param rowIndex2 Índice de la segunda fila.
	 * @return Una nueva fila que representa la resta de las filas.
	 */
	public Value[] subtractRows(Matrix matrix, int rowIndex1, int rowIndex2) {
		if (rowIndex1 < 0 || rowIndex1 >= matrix.getRows() || rowIndex2 < 0 || rowIndex2 >= matrix.getRows()) {
			throw new IllegalArgumentException("Los índices de fila están fuera de rango");
		}

		Value[] resultRow = new Value[matrix.getCols()];

		for (int j = 0; j < matrix.getCols(); j++) {
			Value difference = valueService.subtract(matrix.getData(rowIndex1, j), matrix.getData(rowIndex2, j));
			resultRow[j] = difference;
		}

		return resultRow;
	}
    /**
     * Realiza la multiplicación de una fila por un valor escalar y devuelve una nueva fila como resultado.
     *
     * @param rowIndex Índice de la fila a multiplicar.
     * @param scalar Valor escalar por el que se multiplicará la fila.
     * @return Una nueva fila que representa la fila multiplicada por el valor escalar.
     */
    public Value[] multiplyRow(Matrix matrix,int rowIndex, Value scalar) {
        if (rowIndex < 0 || rowIndex >= matrix.getRows()) {
            throw new IllegalArgumentException("El índice de fila está fuera de rango");
        }

        Value[] resultRow = new Value[matrix.getCols()];

        for (int j = 0; j < matrix.getCols(); j++) {
            Value product = valueService.multiply(matrix.getData(rowIndex, j), scalar);
            resultRow[j] = product;
        }

        return resultRow;
    }
    public Matrix generateRandomMatrix(int rows, int cols) {
        return generateRandomMatrix(rows, cols, -100, 100); // Valores mínimos y máximos predefinidos (-100 a 100)
    }

    public Matrix generateRandomMatrix(int rows, int cols, int minValue, int maxValue) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Las dimensiones de la matriz deben ser positivas.");
        }

        Matrix randomMatrix = new Matrix(rows, cols);

        Random random = new Random();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int randomValue = random.nextInt(maxValue - minValue + 1) + minValue;
                randomMatrix.setData(row, col, new Value(randomValue));
            }
        }

        return randomMatrix;
    }
	public Matrix createSubMatrix(Matrix matrix,int excludingRow, int excludingCol) {
	    int subMatrixSize = matrix.getRows() - 1;
	    Matrix subMatrix = new Matrix(subMatrixSize, subMatrixSize);
	
	    int newRow = 0;
	    for (int row = 0; row < matrix.getRows(); row++) {
	        if (row == excludingRow) {
	            continue;
	        }
	
	        int newCol = 0;
	        for (int col = 0; col < matrix.getCols(); col++) {
	            if (col == excludingCol) {
	                continue;
	            }
	            subMatrix.setData(newRow, newCol, matrix.getData(row, col));
	            newCol++;
	        }
	
	        newRow++;
	    }
	    return subMatrix;
	}
    /**
     * Calcula el determinante de la matriz.
     *
     * @return El determinante de la matriz.
     * @throws IllegalArgumentException Si la matriz no es cuadrada.
     */
    public Value calculateDeterminant(Matrix matrix) {
        if (matrix.getRows() != matrix.getCols()) {
            throw new IllegalArgumentException("La matriz no es cuadrada. Debe tener el mismo número de filas y columnas.");
        }

        int n = matrix.getRows();

        if (n == 1) {
            return matrix.getData(0, 0); // Caso base para matriz de 1x1
        } else if (n == 2) {
            // Caso base para matriz de 2x2
            Value a = matrix.getData(0, 0);
            Value b = matrix.getData(0, 1);
            Value c = matrix.getData(1, 0);
            Value d = matrix.getData(1, 1);
            return valueService.subtract(valueService.multiply(a, d), valueService.multiply(b, c));
        } else {
            Value determinant = new Value(0.0);

            for (int col = 0; col < n; col++) {
                Value term = valueService.multiply(matrix.getData(0, col), cofactor(matrix, 0, col));
                if (col % 2 == 1) {
                    term = term.negate(); // Cambia el signo de términos en columnas impares
                }
                Matrix subMatrix = createSubMatrix(matrix, 0, col);
                Value subMatrixDeterminant = valueService.multiply(term, calculateDeterminant(subMatrix));
                determinant = valueService.add(determinant, subMatrixDeterminant);
            }

            return determinant;
        }
    }
    public Value cofactor(Matrix matrix,int row, int col) {
        Matrix subMatrix = createSubMatrix(matrix,row, col);
        return calculateDeterminant(subMatrix);
    }
	public Matrix getCoefficients(Matrix matrix) {
	    int coefCols = matrix.getRows();
	    int coefRows = matrix.getRows();
	    Matrix coefficientsMatrix = new Matrix(coefRows, coefCols);
	
	    // Copia los valores de la matriz original a la matriz de coeficientes
	    for (int row = 0; row < matrix.getRows(); row++) {
	        for (int col = 0; col < coefCols; col++) {
	            coefficientsMatrix.setData(row, col, matrix.getData(row, col));
	        }
	    }
	    return coefficientsMatrix;
	}
    /**
     * Intercambia dos filas en la matriz.
     *
     * @param row1 El índice de la primera fila a intercambiar.
     * @param row2 El índice de la segunda fila a intercambiar.
     */
    public void swapRows(Matrix matrix, int row1, int row2) {
        if (row1 < 0 || row1 >= matrix.getRows() || row2 < 0 || row2 >= matrix.getRows()) {
            throw new IllegalArgumentException("Índices de fila fuera de rango");
        }
        Value[][] data = matrix.getData();
        Value[] temp = data[row1];
        data[row1] = data[row2];
        data[row2] = temp;
    }
    public void performRowReduction(Matrix matrix, int col, int row) {
        Value factor = matrix.getData(row, col);
        int n = matrix.getCols() -1;

        // Iterar a través de los elementos de la fila y reducirlos
        for (int i = col; i < n + 1; i++) {
            Value newValue = valueService.subtract(matrix.getData(row, i), valueService.multiply(factor, (matrix.getData(col, i))));
            matrix.setData(row, i, newValue);
        }
    }
    public int findNonZeroPivot(Matrix matrix, int col) {
        int n = matrix.getRows();
        for (int row = col; row < n; row++) {
            if (!matrix.getData(row, col).isZero()) {
                return row; // Devuelve el índice de la primera fila con un pivote no nulo
            }
        }
        return -1; // Devuelve -1 si no se encuentra un pivote no nulo
    }

    /**
     * Realiza la división del pivote para hacer que el elemento en el pivote sea igual a 1.
     *
     * @param matrix La matriz en la que se realizará la división del pivote.
     * @param col La columna actual que contiene el pivote.
     */
    public void performPivotDivision(Matrix matrix, int col) {
        Value pivotValue = matrix.getData(col, col);

        if (!pivotValue.isZero()) {
            Value pivotInverse = pivotValue.getInverse();
            int n = matrix.getCols()  - 1;

            // Iterar a través de los elementos de la fila y realizar la división
            for (int i = col; i < n + 1; i++) {
                Value newValue = valueService.multiply(matrix.getData(col, i), pivotInverse);
                matrix.setData(col, i, newValue);
            }
        }
    }

}
