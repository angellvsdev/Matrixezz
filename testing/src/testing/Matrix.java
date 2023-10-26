package testing;



public class Matrix {
	private int rows;
	private int cols;
	private double[][] data;
	public Matrix(int rows, int cols) {
		this.setRows(rows);
		this.setCols(cols);
		this.data = new double[rows][cols];
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getCols() {
		return cols;
	}
	public void setCols(int cols) {
		this.cols = cols;
	}
	private void validateIndices(int row, int col) {
	    if (row < 0 || row >= rows || col < 0 || col >= cols) {
	        throw new IllegalArgumentException("Índices fuera de rango");
	    }
	}
	public double getData(int row, int col) {
		validateIndices(row, col);
		return data[row][col];
	}
	public void setData(int row, int col, double value) {
		validateIndices(row, col);
		data[row][col]=value;
	}
	public double[][] getArrayData(){
	    double[][] matrixData = new double[rows][cols];
	    for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < cols; j++) {
	            matrixData[i][j] = getData(i, j);
	        }
	    }
	    return matrixData;
	}
    public void fillMatrix(double[][] newData) {
        if (newData == null) {
            throw new IllegalArgumentException("La matriz de entrada no puede ser nula");
        }

        int numRows = newData.length;
        int numCols = newData[0].length;

        if (numRows != this.rows || numCols != this.cols) {
            throw new IllegalArgumentException("Las dimensiones de la matriz de entrada no coinciden con la matriz actual");
        }

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                this.setData(row, col, newData[row][col]);
            }
        }
    }
	public void printMatrix() {
		for (int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				System.out.print(data[i][j]+"\t");
			}
			System.out.println();
		}
	}
	// Operations With MATRICES
	public Matrix sum(Matrix otherMatrix) {
		if (this.rows != otherMatrix.rows || this.cols != otherMatrix.cols) {
			throw new IllegalArgumentException("Las matrices no tienen las mismas dimensiones");
		}
		Matrix result = new Matrix(rows, cols);
		for (int i=0; i < rows; i++) {
			for (int j=0; j < cols; j++) {
				result.setData(i, j, this.getData(i,j) + otherMatrix.getData(i,j));
			}
		}
		return result;
	}
	public Matrix subt(Matrix otherMatrix) {
		if (this.rows != otherMatrix.rows || this.cols != otherMatrix.cols) {
			throw new IllegalArgumentException("Las matrices no tienen las mismas dimensiones");
		}
		Matrix result = new Matrix(rows, cols);
		for (int i=0; i < rows; i++) {
			for (int j=0; j < cols; j++) {
				result.setData(i, j, this.getData(i,j) - otherMatrix.getData(i,j));
			}
		}
		return result;
	}
	public Matrix mult(Matrix otherMatrix) {
	    if (this.cols != otherMatrix.rows) {
	        throw new IllegalArgumentException("La matriz A debe tener la misma cantidad de columnas que las filas de la matriz B");
	    }
	    Matrix result = new Matrix(this.rows, otherMatrix.cols);
	    for (int i = 0; i < this.rows; i++) {
	        for (int j = 0; j < otherMatrix.cols; j++) {
	            double sum = 0;
	            for (int k = 0; k < this.cols; k++) {
	                sum += this.getData(i, k) * otherMatrix.getData(k, j);
	            }
	            result.setData(i, j, sum);
	        }
	    }
	    return result;
	}
	// Calculate determinant of a matrix
	public double calculateDeterminant() {
		   if (this.getRows() != this.getCols()) {
		        throw new IllegalArgumentException("La matriz no es cuadrada. Debe tener el mismo número de filas y columnas.");
		    }
	    int n = getRows();
	    if (n == 1) {
	        return getData(0, 0);
	    }
	    double determinant = 0;
	    for (int col = 0; col < n; col++) {
	        determinant += getData(0, col) * cofactor(0, col);
	    }
	    return determinant;
	}

	public double cofactor(int row, int col) {
	    // Create a matrix with one row and one column less than the original matrix
	    Matrix minorMatrix = new Matrix(getRows() - 1, getCols() - 1);
	    
	    int minorRow = 0;
	    for (int i = 0; i < getRows(); i++) {
	        if (i == row) continue;
	        
	        int minorCol = 0;
	        for (int j = 0; j < getCols(); j++) {
	            if (j == col) continue;
	            minorMatrix.setData(minorRow, minorCol, getData(i, j));
	            minorCol++;
	        }
	        
	        minorRow++;
	    }
	    
	    double sign = (row + col) % 2 == 0 ? 1.0 : -1.0;
	    
	    return sign * minorMatrix.calculateDeterminant();
	}
	// Gauss Method
	public Matrix getCoefficients() {
	    int coefCols = this.getCols() - 1;
	    int coefRows = this.getRows();
	    Matrix coefficientsMatrix = new Matrix(coefRows, coefCols);

	    // Copia los valores de la matriz original a la matriz de coeficientes
	    for (int row = 0; row < getRows(); row++) {
	        for (int col = 0; col < coefCols; col++) {
	            coefficientsMatrix.setData(row, col, this.getData(row, col));
	        }
	    }
		return coefficientsMatrix;
	}
	public Matrix gaussianElimination() {
		Matrix matrix = this.getCoefficients();
		if (matrix.calculateDeterminant()==0) {
	        throw new IllegalArgumentException("La determinante de la matriz es 0, el sistema de ecuaciones puede no tener una solución única o ser indeterminado.");
		}
		if (matrix.getRows() != matrix.getCols()) {
			throw new IllegalArgumentException("La cantidad de incógnitas debe ser igual a la cantidad de ecuaciones.");
		}
		for (int col = 0; col < getCols() - 1; col++) {
			for (int row = col + 1; row < getRows(); row++) {
		// Calculamos el factor automáticamente
				double factor = getData(row, col) / getData(col, col);
		    	for (int i = col; i < getCols(); i++) {
		    		double newValue = getData(row, i) - factor * getData(col, i);
		    		setData(row, i, newValue); // Asignamos el nuevo valor a la matriz
		    	}
		    }
		}
		return this;
	}
}