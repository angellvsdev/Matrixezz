
package com.linearalgebra.response;

import com.linearalgebra.entity.StepWithMatrix;
import com.linearalgebra.entity.Steps;

import java.util.List;

public class StepsResponse {
    private List<StepWithMatrix> stepsWithMatrices;
    public StepsResponse() {
    	
    }
    public StepsResponse(List<StepWithMatrix> stepsWithMatrices) {
        this.stepsWithMatrices = stepsWithMatrices;
    }
    public StepsResponse(Steps steps) {
    	this.stepsWithMatrices = steps.getStepsWithMatrices();
    }

    public List<StepWithMatrix> getStepsWithMatrices() {
        return stepsWithMatrices;
    }

    public void setStepsWithMatrices(List<StepWithMatrix> stepsWithMatrices) {
        this.stepsWithMatrices = stepsWithMatrices;
    }
}
