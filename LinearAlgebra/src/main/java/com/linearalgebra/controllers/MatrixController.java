package com.linearalgebra.controllers;

import com.linearalgebra.matrix.Matrix;
import com.linearalgebra.request.MatrixRequest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matrix")
public class MatrixController {
    @PostMapping("/sum")
    public Map<String, Object> sumMatrices(@RequestBody MatrixRequest request) {
        // Parse and create two Matrix instances from the request
        Matrix matrixA = parseMatrix(request.getMatrixAData());
        Matrix matrixB = parseMatrix(request.getMatrixBData());

        // Perform matrix addition
        Matrix result = matrixA.sum(matrixB);

        // Preparar la respuesta en formato JSON
        Map<String, Object> response = new HashMap<>();
        response.put("rows", result.getRows());
        response.put("cols", result.getCols());
        response.put("data", result.getArrayData());

        return response;
    }
    @PostMapping("/subst")
    public Map<String, Object> substMatrices(@RequestBody MatrixRequest request) {
        // Parse and create two Matrix instances from the request
        Matrix matrixA = parseMatrix(request.getMatrixAData());
        Matrix matrixB = parseMatrix(request.getMatrixBData());

        // Perform matrix addition
        Matrix result = matrixA.subt(matrixB);

        // Preparar la respuesta en formato JSON
        Map<String, Object> response = new HashMap<>();
        response.put("rows", result.getRows());
        response.put("cols", result.getCols());
        response.put("data", result.getArrayData());

        return response;
    }
    @PostMapping("/mult")
    public Map<String, Object> multMatrices(@RequestBody MatrixRequest request) {
        // Parse and create two Matrix instances from the request
        Matrix matrixA = parseMatrix(request.getMatrixAData());
        Matrix matrixB = parseMatrix(request.getMatrixBData());

        // Perform matrix addition
        Matrix result = matrixA.mult(matrixB);

        // Preparar la respuesta en formato JSON
        Map<String, Object> response = new HashMap<>();
        response.put("rows", result.getRows());
        response.put("cols", result.getCols());
        response.put("data", result.getArrayData());

        return response;
    }
	private Matrix parseMatrix(String matrixData) {
		
	    String[] rowStrings = matrixData.split(";");
	    
	    // Determinar el número de filas y columnas
	    int numRows = rowStrings.length;
	    int numCols = rowStrings[0].split(",").length;

	    double[][] matrixArray = new double[numRows][numCols];
	    
	    for (int i = 0; i < numRows; i++) {
	        String[] colStrings = rowStrings[i].split(",");
	        for (int j = 0; j < numCols; j++) {
	            matrixArray[i][j] = Double.parseDouble(colStrings[j]);
	        }
	    }
	    Matrix matrix = new Matrix(numRows, numCols);
	    matrix.fillMatrix(matrixArray);
	    return matrix;
	}
}