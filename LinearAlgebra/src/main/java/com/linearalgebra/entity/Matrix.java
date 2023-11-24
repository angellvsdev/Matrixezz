package com.linearalgebra.entity;

import java.text.DecimalFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Matrix {
    private int rows; // Número de filas de la matriz.
    private int getCols; // Número de columnas de la matriz.
    private Value[][] data; // Los datos de la matriz representados como objetos de tipo Value.
    public Matrix() {
    	
    }
    /**
     * Constructor de la clase Matrix que crea una matriz con el número especificado de filas y columnas.
     *
     * @param rows El número de filas de la matriz.
     * @param cols El número de columnas de la matriz.
     */
    public Matrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Las dimensiones de la matriz deben ser positivas.");
        }
        this.setRows(rows);
        this.setCols(cols);
        // Creamos una matriz vacía.
        this.data = new Value[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] = new Value(0);
            }
        }
    }
    /**
     * Obtiene el número de filas de la matriz.
     *
     * @return El número de filas.
     */
    public int getRows() {
        return rows;
    }

    /**
	 * Establece el número de filas de la matriz.
	 *
	 * @param rows El número de filas a establecer.
	 */
	public void setRows(int rows) {
	    this.rows = rows;
	}
	/**
	 * Obtiene el número de columnas de la matriz.
	 *
	 * @return El número de columnas.
	 */
	public int getCols() {
	    return getCols;
	}
	/**
     * Establece el número de columnas de la matriz.
     *
     * @param cols El número de columnas a establecer.
     */
    public void setCols(int cols) {
        this.getCols = cols;
    }

    public Value[][] getData() {
	    Value[][] matrixData = new Value[rows][getCols];
	    for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < getCols; j++) {
	            matrixData[i][j] = getData(i, j);
	        }
	    }
	    return matrixData;
	}
	/**
	 * Llena la matriz con nuevos datos representados como un arreglo bidimensional de objetos Value.
	 *
	 * @param newData Los nuevos datos para llenar la matriz.
	 * @throws IllegalArgumentException Si el arreglo de datos de entrada es nulo o no tiene las mismas dimensiones que la matriz actual.
	 */
	public void setData(Value[][] data) {
		this.data = data;
	}
	/**
	 * Obtiene el valor en una ubicación específica de la matriz.
	 *
	 * @param row La fila de la ubicación.
	 * @param col La columna de la ubicación.
	 * @return El valor en la ubicación especificada.
	 * @throws IllegalArgumentException Si los índices de fila o columna están fuera de rango.
	 */
	@JsonIgnore
	public Value getData(int row, int col) {
	    if (row < 0 || row >= rows || col < 0 || col >= getCols) {
	        throw new IllegalArgumentException("Índices fuera de rango");
	    }
	    return data[row][col];
	}
	/**
 	 * Establece el valor en una ubicación específica de la matriz.
 	 *
 	 * @param row   La fila de la ubicación.
 	 * @param col   La columna de la ubicación.
 	 * @param value El valor a establecer en la ubicación especificada.
 	 * @throws IllegalArgumentException Si los índices de fila o columna están fuera de rango.
 	 */
 	public void setData(int row, int col, Value value) {
 	    if (row < 0 || row >= rows || col < 0 || col >= getCols) {
 	        throw new IllegalArgumentException("Índices fuera de rango");
 	    }
 	    data[row][col] = value;
 	}
    /**
     * Llena la matriz con nuevos datos representados como un arreglo bidimensional de objetos Value.
     *
     * @param newData Los nuevos datos para llenar la matriz.
     * @throws IllegalArgumentException Si el arreglo de datos de entrada es nulo o no tiene las mismas dimensiones que la matriz actual.
     */
    @Override
    public String toString() {
 	    StringBuilder stringBuilder = new StringBuilder();
 	    DecimalFormat df = new DecimalFormat("0.##"); // Utiliza un formato para redondear a 3 decimales

 	    for (int i = 0; i < rows; i++) {
 	        for (int j = 0; j < getCols(); j++) {
 	            Value value = data[i][j];

 	            if (value.isDouble()) {
 	                double numericValue = (double) value.getValue();

 	                // Si el valor es muy cercano a cero, considéralo como cero
 	                if (Math.abs(numericValue) < 1e-10) {
 	                    numericValue = 0.0;
 	                }

 	                String formattedValue = df.format(numericValue); // Aplica el formato
 	                stringBuilder.append(formattedValue);
 	            } else if (value.isRational()) {
 	                String fractionalString = value.toString();
 	                stringBuilder.append(fractionalString);
 	            }

 	            stringBuilder.append("\t"); // Agrega un tabulador para separar las columnas
 	        }
 	        stringBuilder.append("\n"); // Agrega un salto de línea al final de cada fila
 	    }
 	    return stringBuilder.toString();
 	}
}
