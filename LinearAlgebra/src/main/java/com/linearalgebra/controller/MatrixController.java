package com.linearalgebra.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.linearalgebra.entity.Matrix;
import com.linearalgebra.entity.Steps;
import com.linearalgebra.entity.Value;
import com.linearalgebra.request.MatrixListRequest;
import com.linearalgebra.request.MatrixRequest;
import com.linearalgebra.response.MatrixResponse;
import com.linearalgebra.response.StepsResponse;
import com.linearalgebra.service.MatrixService;

@RestController
@RequestMapping("/matrix/")
public class MatrixController {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private MatrixService matrixService;
    @PostMapping("/add")
    public MatrixResponse sumMatrices(@RequestBody MatrixListRequest request) {
    	Matrix matrix1 = request.getMatrix1();
        Matrix matrix2 = request.getMatrix2();
        // Perform matrix addition
        Matrix result = matrixService.add(matrix1, matrix2);
        // Create and return a MatrixResponse object
        return new MatrixResponse(result);
    }
    @PostMapping("/subtract")
    public MatrixResponse subtractMatrices(@RequestBody MatrixListRequest request) {
        Matrix matrix1 = request.getMatrix1();
        Matrix matrix2 = request.getMatrix2();
        // Perform matrix subtraction
        Matrix result = matrixService.subtract(matrix1, matrix2);
        // Create and return a MatrixResponse object
        return new MatrixResponse(result);
    }
    @PostMapping("/multiply")
    public MatrixResponse multiplyMatrices(@RequestBody MatrixListRequest request) {
        Matrix matrix1 = request.getMatrix1();
        Matrix matrix2 = request.getMatrix2();
        // Perform matrix multiplication
        Matrix result = matrixService.multiply(matrix1, matrix2);
        // Create and return a MatrixResponse object
        return new MatrixResponse(result);
    }
    @PostMapping("/multiplyScalar")
    public MatrixResponse multiplyScalar(
            @RequestBody Matrix matrix,
            @RequestParam double scalar) {

        // Perform matrix multiplication by scalar
        Matrix result = matrixService.multiplyScalar(matrix, scalar);

        // Create and return a MatrixResponse object
        return new MatrixResponse(result);
    }
    @PostMapping("/gaussianElimination")
    public ResponseEntity<?> gaussianElimination(@RequestBody MatrixRequest matrixRequest) {
        Matrix inputMatrix = mapper.map(matrixRequest, Matrix.class);

        try {
            Matrix resultMatrix = matrixService.gaussianElimination(inputMatrix,false);
            return ResponseEntity.ok(new MatrixResponse(resultMatrix));
        } catch (ArithmeticException e) {
            // Manejar la excepción según tus necesidades, por ejemplo, devolver un mensaje de error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
    @PostMapping("/gaussJordanElimination")
    public ResponseEntity<?> gaussJordanElimination(@RequestBody MatrixRequest matrixRequest) {
        Matrix inputMatrix = mapper.map(matrixRequest, Matrix.class);

        try {
            Matrix resultMatrix = matrixService.gaussJordanElimination(inputMatrix, false);
            return ResponseEntity.ok(new MatrixResponse(resultMatrix));
        } catch (ArithmeticException e) {
            // Manejar la excepción según tus necesidades, por ejemplo, devolver un mensaje de error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
    @PostMapping("/inverseMatrix")
    public ResponseEntity<?> inverseMatrix(@RequestBody MatrixRequest matrixRequest) {
        Matrix inputMatrix = mapper.map(matrixRequest, Matrix.class);
        try {
            Matrix resultMatrix = matrixService.calculateInverse(inputMatrix);
            return ResponseEntity.ok(new MatrixResponse(resultMatrix));
        } catch (ArithmeticException e) {
            // Manejar la excepción según tus necesidades, por ejemplo, devolver un mensaje de error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
    @PostMapping("/calculateDeterminant")
    public ResponseEntity<?> calculateDeterminant(@RequestBody MatrixRequest matrixRequest){
    	Matrix inputMatrix = mapper.map(matrixRequest, Matrix.class);
    	try {
    		Value determinant = matrixService.calculateDeterminant(inputMatrix);
    		return ResponseEntity.ok(determinant);
    	} catch (ArithmeticException e) {
            // Manejar la excepción según tus necesidades, por ejemplo, devolver un mensaje de error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
    @PostMapping("/transpose")
    public ResponseEntity<?> transpose(@RequestBody MatrixRequest matrixRequest){
    	Matrix inputMatrix = mapper.map(matrixRequest, Matrix.class);
    	try {
    		Matrix resultMatrix = matrixService.transpose(inputMatrix);
    		return ResponseEntity.ok(new MatrixResponse(resultMatrix));
    	} catch (ArithmeticException e) {
            // Manejar la excepción según tus necesidades, por ejemplo, devolver un mensaje de error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
    @PostMapping("/gaussianEliminationWithSteps")
    public ResponseEntity<?> gaussianEliminationWithSteps(@RequestBody MatrixRequest matrixRequest){

    	Matrix inputMatrix = mapper.map(matrixRequest, Matrix.class);
    	try {
    		Steps resultMatrix = matrixService.gaussianEliminationWithSteps(inputMatrix, false);
    		return ResponseEntity.ok(new StepsResponse(resultMatrix));
    	} catch (ArithmeticException e) {
            // Manejar la excepción según tus necesidades, por ejemplo, devolver un mensaje de error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
    @PostMapping("/gaussJordanEliminationWithSteps")
    public ResponseEntity<?> gaussJordanEliminationWithSteps(@RequestBody MatrixRequest matrixRequest){

    	Matrix inputMatrix = mapper.map(matrixRequest, Matrix.class);
    	try {
    		Steps resultMatrix = matrixService.gaussJordanEliminationWithSteps(inputMatrix, false);
    		return ResponseEntity.ok(new StepsResponse(resultMatrix));
    	} catch (ArithmeticException e) {
            // Manejar la excepción según tus necesidades, por ejemplo, devolver un mensaje de error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
    @PostMapping("/inverseMatrixWithSteps")
    public ResponseEntity<?> inverseMatrixWithsteps(@RequestBody MatrixRequest matrixRequest){

    	Matrix inputMatrix = mapper.map(matrixRequest, Matrix.class);
    	try {
    		Steps resultMatrix = matrixService.calculateInverseWithSteps(inputMatrix);
    		return ResponseEntity.ok(new StepsResponse(resultMatrix));
    	} catch (ArithmeticException e) {
            // Manejar la excepción según tus necesidades, por ejemplo, devolver un mensaje de error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
}
