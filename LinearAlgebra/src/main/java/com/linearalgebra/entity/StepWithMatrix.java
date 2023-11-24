package com.linearalgebra.entity;

import com.linearalgebra.response.MatrixResponse;

public class StepWithMatrix {
    private String step;
    private MatrixResponse matrix;

    public StepWithMatrix(String step, Matrix matrix) {
        this.step = step;
        this.matrix = new MatrixResponse(matrix); // Asegúrate de tener un método copy() en tu clase Matrix.
    }

    public String getStep() {
        return step;
    }

    public MatrixResponse getMatrix() {
        return matrix;
    }

	public void setStep(String step) {
		this.step = step;
	}

	public void setMatrix(Matrix matrix) {
		this.matrix = new MatrixResponse(matrix);
	}
    @Override
    public String toString() {
        return "StepWithMatrix{" +
                "step='" + step + '\'' +
                ", matrix=" + matrix +
                '}';
    }
}