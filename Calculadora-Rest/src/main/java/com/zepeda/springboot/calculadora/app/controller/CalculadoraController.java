package com.zepeda.springboot.calculadora.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.zepeda.springboot.calculadora.app.CalculadoraApplication;
import com.zepeda.springboot.calculadora.app.dto.CalculadoraDto;

@RestController
public class CalculadoraController {

    @Autowired
    private CalculadoraApplication calculadora;

    @GetMapping("/sumar/{a}/{b}")
    Double sumar(@PathVariable Double a, @PathVariable Double b) {
        CalculadoraDto objeto = new CalculadoraDto();
        objeto.setNumero1(a);
        objeto.setNumero2(b);
        return calculadora.sumar(objeto);
    }

    @GetMapping("/restar/{a}/{b}")
    Double restar(@PathVariable Double a, @PathVariable Double b) {
        CalculadoraDto objeto = new CalculadoraDto();
        objeto.setNumero1(a);
        objeto.setNumero2(b);
        return calculadora.restar(objeto);
    }

    @GetMapping("/multiplicar/{a}/{b}")
    Double multiplicar(@PathVariable Double a, @PathVariable Double b) {
        CalculadoraDto objeto = new CalculadoraDto();
        objeto.setNumero1(a);
        objeto.setNumero2(b);
        return calculadora.multiplicar(objeto);
    }

    @GetMapping("/dividir/{a}/{b}")
    Double dividir(@PathVariable Double a, @PathVariable Double b) {
        CalculadoraDto objeto = new CalculadoraDto();
        objeto.setNumero1(a);
        objeto.setNumero2(b);
        return calculadora.dividir(objeto);
    }
}
