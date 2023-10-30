package com.linearalgebra.controllers;


import com.linearalgebra.matrix.Matrix;
import com.linearalgebra.numbers.Value;
import com.linearalgebra.request.MatrixRequest;
import com.linearalgebra.responsers.MatrixResponse;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matrix")
public class MatrixController {

    @PostMapping("/sum")
    public MatrixResponse sumMatrices(@RequestBody MatrixRequest request) {
        // Parse and create two Matrix instances from the request
        Matrix matrixA = parseMatrix(request.getMatrixAData());
        Matrix matrixB = parseMatrix(request.getMatrixBData());

        // Perform matrix addition
        Matrix result = matrixA.add(matrixB);

        // Create and return a MatrixResponse object
        return new MatrixResponse(result);
    }
    @PostMapping("/subtract")
    public MatrixResponse subtractMatrices(@RequestBody MatrixRequest request) {
        // Parse and create two Matrix instances from the request
        Matrix matrixA = parseMatrix(request.getMatrixAData());
        Matrix matrixB = parseMatrix(request.getMatrixBData());

        // Perform matrix addition
        Matrix result = matrixA.subtract(matrixB);

        // Create and return a MatrixResponse object
        return new MatrixResponse(result);
    }
    @PostMapping("/multiply")
    public MatrixResponse multiplyMatrices(@RequestBody MatrixRequest request) {
        // Parse and create two Matrix instances from the request
        Matrix matrixA = parseMatrix(request.getMatrixAData());
        Matrix matrixB = parseMatrix(request.getMatrixBData());

        // Perform matrix addition
        Matrix result = matrixA.multiply(matrixB);

        // Create and return a MatrixResponse object
        return new MatrixResponse(result);
    }
    private Matrix parseMatrix(String matrixData) {
        String[] rowStrings = matrixData.split(";");
        
        // Determinar el número de filas y columnas
        int numRows = rowStrings.length;
        int numCols = rowStrings[0].split(",").length;

        Value[][] matrixArray = new Value[numRows][numCols];
        
        for (int i = 0; i < numRows; i++) {
            String[] colStrings = rowStrings[i].split(",");
            for (int j = 0; j < numCols; j++) {
                matrixArray[i][j] = new Value(colStrings[j]); // Suponiendo que Value tiene un constructor que acepta una cadena
            }
        }
        
        Matrix matrix = new Matrix(numRows, numCols);
        matrix.fillMatrix(matrixArray);
        return matrix;
    }

}