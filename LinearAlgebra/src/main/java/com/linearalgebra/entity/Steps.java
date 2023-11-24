package com.linearalgebra.entity;

import java.util.ArrayList;
import java.util.List;

public class Steps {
    private List<StepWithMatrix> stepsWithMatrices;

    public Steps() {
        stepsWithMatrices = new ArrayList<>();
    }

    public void addStep(String step, Matrix matrix) {
        stepsWithMatrices.add(new StepWithMatrix(step, matrix));
    }

    public List<StepWithMatrix> getStepsWithMatrices() {
        return stepsWithMatrices;
    }

	public void setStepsWithMatrices(List<StepWithMatrix> stepsWithMatrices) {
		this.stepsWithMatrices = stepsWithMatrices;
	}
    
}
