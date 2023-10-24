package com.linearalgebra.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @GetMapping("/saludo")
    public String helloWorld() {
        return "Hello, World!";
    }
	
}
