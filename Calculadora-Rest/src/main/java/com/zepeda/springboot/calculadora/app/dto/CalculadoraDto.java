package com.zepeda.springboot.calculadora.app.dto;

public class CalculadoraDto {
    
    private Double numero1;
    
    private Double numero2;

    /**
     * @return the numero1
     */
    public Double getNumero1() {
        return numero1;
    }

    /**
     * @param numero1 the numero1 to set
     */
    public void setNumero1(Double numero1) {
        this.numero1 = numero1;
    }

    /**
     * @return the numero2
     */
    public Double getNumero2() {
        return numero2;
    }

    /**
     * @param numero2 the numero2 to set
     */
    public void setNumero2(Double numero2) {
        this.numero2 = numero2;
    }
}