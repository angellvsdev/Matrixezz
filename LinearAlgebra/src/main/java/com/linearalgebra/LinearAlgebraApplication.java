package com.linearalgebra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages = "com.linearalgebra.controllers")
@SpringBootApplication
public class LinearAlgebraApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinearAlgebraApplication.class, args);
	}

}