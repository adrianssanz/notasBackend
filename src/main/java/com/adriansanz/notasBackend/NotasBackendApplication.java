package com.adriansanz.notasBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@Validated
public class NotasBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotasBackendApplication.class, args);
		System.out.println("--------------------------------------------------------------");
		System.out.println("Aplicación iniciada en la ruta http://localhost:8080/api/notas");
		System.out.println("--------------------------------------------------------------");
	}

}
