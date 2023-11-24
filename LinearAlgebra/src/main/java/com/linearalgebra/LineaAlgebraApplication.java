package com.linearalgebra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.linearalgebra"})
public class LineaAlgebraApplication {

	public static void main(String[] args) {
		SpringApplication.run(LineaAlgebraApplication.class, args);
	}

}
