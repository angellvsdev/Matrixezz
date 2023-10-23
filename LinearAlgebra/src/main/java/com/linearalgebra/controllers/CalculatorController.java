package com.linearalgebra.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CalculatorController {
	@GetMapping("/sum")
	public double sum(@RequestParam double num1, @RequestParam double num2) {
		return num1 + num2;
	}
	@GetMapping("/substract")
	public double substtract(@RequestParam double num1, @RequestParam double num2) {
		return num1 - num2;
	}
	@GetMapping("/multiply")
	public double multiply(@RequestParam double num1, @RequestParam double num2) {
		return num1 * num2;
	}
	@GetMapping("/divide")
	public double divide(@RequestParam double num1, @RequestParam double num2) {
		if (num2 != 0) {
			return num1/num2;
		} else {
			throw new IllegalArgumentException("Division by zero is not allowed.");
		}
	}
}