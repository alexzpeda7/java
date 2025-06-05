package com.zepeda.springboot.calculadora.app.dto;

import java.util.Date;

public class CalculadoraDto {
    private String Message;
    private Date date;
    /**
     * @return the message
     */
    public String getMessage() {
        return Message;
    }
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        Message = message;
    }
    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }
    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
      
}