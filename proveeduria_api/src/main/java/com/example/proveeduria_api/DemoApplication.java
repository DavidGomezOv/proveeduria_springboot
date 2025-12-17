package com.example.proveeduria_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Clase principal del proyecto Spring Boot.
 * Inicia la aplicación y configura el contexto de Spring.
 */
@SpringBootApplication
@EnableAsync
public class DemoApplication {

    /**
     * Punto de entrada de la aplicación.
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
