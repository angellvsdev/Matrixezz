package com.linearalgebra.matrix;



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
	public double getData(int row, int col) {
		if (row <0 || row >= rows || col < 0 || col >= cols) {
			throw new IllegalArgumentException("Indices fuera de rango");
	}
		return data[row][col];
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
	public void setData(int row, int col, double value) {
		if (row <0 || row >= rows || col < 0 || col >= cols) {
			throw new IllegalArgumentException("Indices fuera de rango");
	}
		data[row][col]=value;
	}
	public void printMatrix() {
		for (int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				System.out.println(data[i][j]);
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
}