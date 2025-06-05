package com.zepeda.springboot.app.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zepeda.springboot.app.dto.MessageDto;

@RestController
public class MessageController {
    
    @GetMapping ("/view") 
    public MessageDto viewMewssage() {
        MessageDto message = new MessageDto();
        message.setDate(new Date());
        message.setMessage("HOLA MUNDO");
        return message;
    }
    
}