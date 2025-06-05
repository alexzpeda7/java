package com.example.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.dao.UsuarioDao;

@Configuration
public class ConsoleApp {
    @Autowired
    private UsuarioDao usuarioDao;

    @Bean
    CommandLineRunner crearAppConsola() {
//        TengoNombre objeto = new TengoNombre();
//        return objeto;
        
//        CommandLineRunner objeto = new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                System.out.println("Hello, World!");
//            }
//        };
        
        
        return args -> {
            System.out.println(usuarioDao.getAllUsuarios());
            System.out.println(usuarioDao.getUsers());
        };
    }
}

class TengoNombre implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello, World!");
    }
}

