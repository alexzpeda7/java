package com.zepeda.springboot.calculadora.app.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zepeda.springboot.calculadora.app.dto.CalculadoraDto;

@RestController
public class CalculadoraController {
    
    @GetMapping ("/view") 
    public CalculadoraDto viewMewssage() {
        CalculadoraDto message = new CalculadoraDto();
        message.setDate(new Date());
        message.setMessage("HOLA CALCULADORA");
        return message;
    }
 
}