package com.linearalgebra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linearalgebra.entity.Matrix;
import com.linearalgebra.entity.Steps;
import com.linearalgebra.entity.Value;
import com.linearalgebra.util.MatrixUtil;

@Service
public class MatrixService {
	@Autowired
	private MatrixUtil matrixUtil;
	@Autowired
	private ValueService valueService;

	public Matrix add(Matrix first, Matrix second) {
		// Verifica si las matrices tienen las mismas dimensiones.
		if (first.getRows() != second.getRows() || first.getCols() != second.getCols()) {
			throw new IllegalArgumentException("Las matrices no tienen las mismas dimensiones");
		}

		// Crea una copia de la matriz actual para almacenar el resultado.
		Matrix result = matrixUtil.copyMatrix(first);

		// Realiza la suma elemento por elemento y actualiza la matriz de resultado.
		for (int i = 0; i < first.getRows(); i++) {
			for (int j = 0; j < second.getCols(); j++) {
				// Realiza la suma de los elementos en la misma posición de ambas matrices.
				Value sum = valueService.add(first.getData(i, j), second.getData(i, j));
				// Actualiza el valor en la matriz de resultado.
				result.setData(i, j, sum);
			}
		}
		return result;
	}

	/**
	 * Realiza la resta de dos matrices y devuelve una nueva matriz como resultado.
	 *
	 * @param otherMatrix La otra matriz a restar.
	 * @return Una nueva matriz que representa la resta.
	 * @throws IllegalArgumentException Si las matrices no tienen las mismas
	 *                                  dimensiones.
	 */
	public Matrix subtract(Matrix first, Matrix second) {
		// Verifica si las matrices tienen las mismas dimensiones.
		if (first.getRows() != second.getRows() || first.getCols() != second.getCols()) {
			throw new IllegalArgumentException("Las matrices no tienen las mismas dimensiones");
		}

		// Crea una copia de la matriz actual para almacenar el resultado.
		Matrix result = matrixUtil.copyMatrix(first);

		// Realiza la resta elemento por elemento y actualiza la matriz de resultado.
		for (int i = 0; i < first.getRows(); i++) {
			for (int j = 0; j < first.getCols(); j++) {
				// Realiza la resta de los elementos en la misma posición de ambas matrices.
				Value difference = valueService.subtract(first.getData(i, j), second.getData(i, j));
				// Actualiza el valor en la matriz de resultado.
				result.setData(i, j, difference);
			}
		}

		return result;
	}

	/**
	 * Realiza la multiplicación de dos matrices y devuelve una nueva matriz como
	 * resultado.
	 *
	 * @param otherMatrix La otra matriz a multiplicar.
	 * @return Una nueva matriz que representa la multiplicación.
	 * @throws IllegalArgumentException Si las matrices no tienen las mismas
	 *                                  dimensiones.
	 */
	public Matrix multiply(Matrix first, Matrix second) {
		// Verifica si el número de columnas de la matriz actual es igual al número de
		// filas de la otra matriz.
		if (first.getCols() != second.getRows()) {
			throw new IllegalArgumentException("Las matrices no tienen las mismas dimensiones");
		}

		// Crea una nueva matriz para almacenar el resultado de la multiplicación.
		Matrix result = new Matrix(first.getRows(), second.getCols());

		// Realiza la multiplicación de matrices usando tres bucles anidados.
		for (int i = 0; i < first.getRows(); i++) {
			for (int j = 0; j < second.getCols(); j++) {
				Value product = new Value(0);

				// Calcula el producto escalar acumulativo de los elementos de las matrices.
				for (int k = 0; k < first.getCols(); k++) {
					Value partialProduct = valueService.multiply(first.getData(i, k), second.getData(k, j));
					product = valueService.add(product, partialProduct);
				}

				// Almacena el resultado en la matriz de resultado.
				result.setData(i, j, product);
			}
		}

		return result;
	}

	public Matrix multiplyScalar(Matrix matrix, double scalar) {
		// Crea una nueva matriz para almacenar el resultado de la multiplicación.
		Matrix result = new Matrix(matrix.getRows(), matrix.getCols());
		// Realiza la multiplicación de matrices usando tres bucles anidados.
		for (int i = 0; i < matrix.getRows(); i++) {
			for (int j = 0; j < matrix.getCols(); j++) {
				Value product = valueService.multiply(matrix.getData(i, j), new Value(scalar));
				result.setData(i, j, product);
			}
		}
		return result;
	}

	public Matrix gaussianElimination(Matrix matrix, boolean calculateInverse) {
		if (matrixUtil.calculateDeterminant(matrixUtil.getCoefficients(matrix)).isZero()) {
	        throw new ArithmeticException("La determinante es igual a cero, el sistema de ecuaciones lineales no se puede realizar.");
	    }
	    if (!calculateInverse) {
	        // Validaciones para el sistema de ecuaciones lineales.
	    	if (matrix.getRows() != matrix.getCols() - 1) {
	    	    throw new ArithmeticException("El número de ecuaciones debe ser igual al número de incógnitas.");
	    	}
	    }
	
		Matrix copyMatrix = matrixUtil.copyMatrix(matrix);
		int n = copyMatrix.getRows();

		for (int col = 0; col < n; col++) {
			int pivotRow = matrixUtil.findNonZeroPivot(copyMatrix, col);

			if (pivotRow == -1) {
				continue; // No se encontró un pivote válido en esta columna, se salta este paso.
			} else {
				if (pivotRow != col) {
					matrixUtil.swapRows(copyMatrix, col, pivotRow);
				}

				matrixUtil.performPivotDivision(copyMatrix, col);

				for (int row = col + 1; row < n; row++) {
					matrixUtil.performRowReduction(copyMatrix, col, row);
				}
			}
		}

		return copyMatrix;
	}

	public Matrix gaussJordanElimination(Matrix matrix, boolean calculateInverse) {
		if (matrixUtil.calculateDeterminant(matrixUtil.getCoefficients(matrix)).isZero()) {
	        throw new ArithmeticException("La determinante es igual a cero, el sistema de ecuaciones lineales no se puede realizar.");
	    }
	    if (!calculateInverse) {
	        // Validaciones para el sistema de ecuaciones lineales.
	    	if (matrix.getRows() != matrix.getCols() - 1) {
	    	    throw new ArithmeticException("El número de ecuaciones debe ser igual al número de incógnitas.");
	    	}
	    }
	
		Matrix copyMatrix = matrixUtil.copyMatrix(matrix); // Crea una copia de la matriz original
		int n = copyMatrix.getRows();

		for (int col = 0; col < n; col++) {
			int pivotRow = matrixUtil.findNonZeroPivot(copyMatrix, col);

			if (pivotRow == -1) {
				continue; // No se encontró un pivote válido en esta columna, se salta este paso.
			} else {
				if (pivotRow != col) {
					matrixUtil.swapRows(copyMatrix, col, pivotRow);
				}

				matrixUtil.performPivotDivision(copyMatrix, col);

				for (int row = 0; row < n; row++) {
					if (row != col) {
						matrixUtil.performRowReduction(copyMatrix, col, row);
					}
				}
			}
		}

		return copyMatrix;
	}

	public Matrix calculateInverse(Matrix matrix) {
		// Comprueba si la matriz es cuadrada.
		if (!matrixUtil.isSquare(matrix)) {
			throw new ArithmeticException("La matriz no es cuadrada y, por lo tanto, no tiene inversa.");
		}

		// Crea una matriz aumentada que consta de la matriz original y la matriz
		// identidad.
		Matrix augmentedMatrix = new Matrix(matrix.getRows(), 2 * matrix.getCols());

		// Copia la matriz original en la parte izquierda de la matriz aumentada.
		for (int i = 0; i < matrix.getRows(); i++) {
			for (int j = 0; j < matrix.getCols(); j++) {
				augmentedMatrix.setData(i, j, matrix.getData(j, i));
			}
			// Rellena la matriz identidad en la parte derecha de la matriz aumentada.
			augmentedMatrix.setData(i, i + matrix.getCols(), new Value(1.0));
		}

		// Realiza eliminación de Gauss-Jordan en la matriz aumentada.
		Matrix preInverseMatrix = gaussJordanElimination(augmentedMatrix, true);

		// Extrae la parte derecha (la matriz inversa) de la matriz aumentada.
		Matrix inverseMatrix = new Matrix(matrix.getRows(), matrix.getCols());
		for (int i = 0; i < matrix.getRows(); i++) {
			for (int j = 0; j < matrix.getCols(); j++) {
				inverseMatrix.setData(i, j, preInverseMatrix.getData(i, j + matrix.getCols()));
			}
		}

		return inverseMatrix;
	}
	public Value calculateDeterminant(Matrix matrix) {
	   return matrixUtil.calculateDeterminant(matrix);
	}
    public Matrix transpose(Matrix matrix) {
        int transposedRows = matrix.getCols();
        int transposedCols = matrix.getRows();
        Value[][] transposedData = new Value[transposedRows][transposedCols];

        for (int row = 0; row < transposedRows; row++) {
            for (int col = 0; col < transposedCols; col++) {
                transposedData[row][col] = matrix.getData(col, row);
            }
        }

        Matrix transposedMatrix = new Matrix(transposedRows, transposedCols);
        transposedMatrix.setData(transposedData);
        
        return transposedMatrix;
    }
	/**
	 * Realiza la eliminación gaussiana para resolver un sistema de ecuaciones lineales y registra
	 * los pasos intermedios en una lista.
	 * 
	 * @return Un arreglo de cadenas que contiene los pasos intermedios de la eliminación gaussiana.
	 * @throws ArithmeticException Si la determinante de la matriz de coeficientes es igual a cero,
	 *                           lo que significa que el sistema de ecuaciones no se puede resolver.
	 */
	public Steps gaussianEliminationWithSteps(Matrix matrix, boolean calculateInverse) {
	    // Verificar si la determinante de la matriz de coeficientes es igual a cero.
		if (matrixUtil.calculateDeterminant(matrixUtil.getCoefficients(matrix)).isZero()) {
	        throw new ArithmeticException("La determinante es igual a cero, el sistema de ecuaciones lineales no se puede realizar.");
	    }
	    if (!calculateInverse) {
	        // Validaciones para el sistema de ecuaciones lineales.
	    	if (matrix.getRows() != matrix.getCols() - 1) {
	    	    throw new ArithmeticException("El número de ecuaciones debe ser igual al número de incógnitas.");
	    	}
	    }
	
	    // Hacer una copia de la matriz para realizar la eliminación gaussiana en ella.
	    Matrix copyMatrix = matrixUtil.copyMatrix(matrix);
	    int n = copyMatrix.getRows();
	    int step = 1;
	    Steps steps = new Steps();
	    for (int col = 0; col < n; col++) {
	        // Encontrar la fila con un pivote no nulo en la columna actual.
	        int pivotRow = matrixUtil.findNonZeroPivot(copyMatrix, col);
	
	        if (pivotRow == -1) {
	            continue; // No se encontró un pivote válido en esta columna, se salta este paso.
	        } else {
	            if (pivotRow != col) {
	                // Intercambiar las filas si es necesario para tener un pivote en la diagonal principal.
	                matrixUtil.swapRows(copyMatrix, pivotRow, pivotRow);
	                steps.addStep("Paso " + (step) + " (Intercambio de fila " + col + " <-> fila " + pivotRow + "):", copyMatrix);
	                step++;
	            }
	
	            // Realizar la división de la fila actual para hacer que el pivote sea igual a 1.
	            matrixUtil.performPivotDivision(copyMatrix, col);
	
	            for (int row = col + 1; row < n; row++) {
	                // Realizar la reducción de filas para hacer ceros debajo del pivote actual.
	                matrixUtil.performRowReduction(copyMatrix, col, row);
	                steps.addStep("Paso " + (step) + " (Fila " + (row + 1) + " - Fila " + (col + 1) + ")", copyMatrix);
	                step++;
	            }
	        }
	    }
	
	    // Convierte la lista de pasos en un arreglo de cadenas.
	
	    return steps;
	}
	public Steps gaussJordanEliminationWithSteps(Matrix matrix, boolean calculateInverse) {
	    // Verificar si la determinante de la matriz de coeficientes es igual a cero.
		if (matrixUtil.calculateDeterminant(matrixUtil.getCoefficients(matrix)).isZero()) {
	        throw new ArithmeticException("La determinante es igual a cero, el sistema de ecuaciones lineales no se puede realizar.");
	    }
	    // Si calculateInverse es verdadero, ignorar las validaciones relacionadas con la determinante y el número de ecuaciones e incógnitas.
	    if (!calculateInverse) {
	        // Validaciones para el sistema de ecuaciones lineales.
	    	if (matrix.getRows() != matrix.getCols() - 1) {
	    	    throw new ArithmeticException("El número de ecuaciones debe ser igual al número de incógnitas.");
	    	}
	    }

	    // Hacer una copia de la matriz para realizar la eliminación de Gauss-Jordan en ella.
	    Matrix copyMatrix = matrixUtil.copyMatrix(matrix);
	    int n = copyMatrix.getRows();
	    int step = 1;
	    Steps steps = new Steps();

	    for (int col = 0; col < n; col++) {
	        // Encontrar la fila con un pivote no nulo en la columna actual.
	        int pivotRow = matrixUtil.findNonZeroPivot(copyMatrix, col);

	        if (pivotRow == -1) {
	            continue; // No se encontró un pivote válido en esta columna, se salta este paso.
	        } else {
	            if (pivotRow != col) {
	                // Intercambiar las filas si es necesario para tener un pivote en la diagonal principal.
	                matrixUtil.swapRows(copyMatrix, col, pivotRow);
	                steps.addStep("Paso " + (step) + " (Intercambio de fila " + col + " <-> fila " + pivotRow + "):", copyMatrix);
	                step++;
	            }

	            // Realizar la división de la fila actual para hacer que el pivote sea igual a 1.
	            matrixUtil.performPivotDivision(copyMatrix, col);

	            for (int row = 0; row < n; row++) {
	                if (row != col) {
	                    // Realizar la reducción de filas para hacer ceros en todas las demás filas en esta columna.
	                    matrixUtil.performRowReduction(copyMatrix, col, row);
	                    steps.addStep("Paso " + (step) + " (Fila " + (row + 1) + " - Fila " + (col + 1) + ")", copyMatrix);
	                    step++;
	                }
	            }
	        }
	    }

	    return steps;
	}
	public Steps calculateInverseWithSteps(Matrix matrix) {
	    // Comprueba si la matriz es cuadrada.
	    if (matrix.getRows() != matrix.getCols()) {
	        throw new ArithmeticException("La matriz no es cuadrada y, por lo tanto, no tiene inversa.");
	    }

	    // Crea una matriz aumentada que consta de la matriz original y la matriz identidad.
	    Matrix augmentedMatrix = new Matrix(matrix.getRows(), 2 * matrix.getCols());

	    // Copia la matriz original en la parte izquierda de la matriz aumentada.
	    for (int i = 0; i < matrix.getRows(); i++) {
	        for (int j = 0; j < matrix.getCols(); j++) {
	            augmentedMatrix.setData(i, j, matrix.getData(i, j));
	        }
	        // Rellena la matriz identidad en la parte derecha de la matriz aumentada.
	        augmentedMatrix.setData(i, i + matrix.getCols(), new Value(1.0));
	    }

	    // Realiza eliminación de Gauss-Jordan en la matriz aumentada.
	    Steps steps = gaussJordanEliminationWithSteps(augmentedMatrix, true);
	    Matrix preInverseMatrix = gaussJordanElimination(augmentedMatrix, true);

	    // Extrae la parte derecha (la matriz inversa) de la matriz aumentada.
	    Matrix inverseMatrix = new Matrix(matrix.getRows(), matrix.getCols());
	    for (int i = 0; i < matrix.getRows(); i++) {
	        for (int j = 0; j < matrix.getCols(); j++) {
	            inverseMatrix.setData(i, j, preInverseMatrix.getData(i, j + matrix.getCols()));
	        }
	    }

	    // Agrega la matriz inversa como un nuevo paso en la lista de pasos.
	    steps.addStep("Matriz Inversa:", inverseMatrix);

	    return steps;
	}
}
