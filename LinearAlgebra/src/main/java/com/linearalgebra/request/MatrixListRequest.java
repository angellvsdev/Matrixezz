package com.linearalgebra.request;

import com.linearalgebra.entity.*;

import jakarta.validation.constraints.NotNull;
public class MatrixListRequest {
    @NotNull(message = "Los datos de la matriz no pueden ser nulos")
    private Matrix matrix1;
    @NotNull(message = "Los datos de la matriz no pueden ser nulos")
    private Matrix matrix2;
	public MatrixListRequest() {
	}
	public MatrixListRequest(Matrix matrix1, Matrix matrix2) {
		this.matrix1 = matrix1;
		this.matrix2 = matrix2;
	}
	public Matrix getMatrix1() {
		return matrix1;
	}
	public void setMatrix1(Matrix matrix1) {
		this.matrix1 = matrix1;
	}
	public Matrix getMatrix2() {
		return matrix2;
	}
	public void setMatrix2(Matrix matrix2) {
		this.matrix2 = matrix2;
	}
	
}