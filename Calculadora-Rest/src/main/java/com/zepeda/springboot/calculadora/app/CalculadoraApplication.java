package com.zepeda.springboot.calculadora.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import com.zepeda.springboot.calculadora.app.dto.CalculadoraDto;

@Service
@SpringBootApplication
public class CalculadoraApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculadoraApplication.class, args);
    }

    public Double sumar(CalculadoraDto operacion) {
        return operacion.getNumero1() + operacion.getNumero2();
    }

    public Double restar(CalculadoraDto operacion) {
        return operacion.getNumero1() - operacion.getNumero2();
    }

    public Double multiplicar(CalculadoraDto operacion) {
        return operacion.getNumero1() * operacion.getNumero2();
    }

    public Double dividir(CalculadoraDto operacion) {
        if (operacion.getNumero2() == 0) {
            throw new ArithmeticException("División por cero no permitida.");
        }
        return operacion.getNumero1() / operacion.getNumero2();
    }
}
