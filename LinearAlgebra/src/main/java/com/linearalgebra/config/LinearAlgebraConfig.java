package com.linearalgebra.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LinearAlgebraConfig {
	@Bean
	ModelMapper ModelMapper(){
		return new ModelMapper();
	}
}
