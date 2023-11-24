package com.linearalgebra.request;

import com.linearalgebra.entity.Value;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MatrixRequest {
	@Min(value = 1, message = "El número de filas debe ser al menos 1")
	private int rows;
	@Min(value = 1, message = "El número de filas debe ser al menos 1")
	private int cols;
    @NotNull(message = "Los datos de la matriz no pueden ser nulos")
    @Size(min = 1, message = "Debe proporcionar al menos una fila")
	private Value[][] data;

	public MatrixRequest(int rows, int cols, Value[][] data) {
		this.rows = rows;
		this.cols = cols;
		this.data = data;
	}
	public MatrixRequest() {
		
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

	public Value[][] getData() {
		return data;
	}

	public void setData(Value[][] data) {
		this.data = data;
	}
	
}
