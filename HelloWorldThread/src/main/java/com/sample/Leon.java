package com.sample;

import java.util.ArrayList;
import java.util.List;

public class Leon {

    private int garras = 4;
    private List<String> listaPresas = new ArrayList<>();

    public void rugir() {
        System.out.println("¡Grrrrr!");
    }
    
    public void dormir() {
        System.out.println("Zzzzzz");
    }
    
    public void run() {
        System.out.println("Ataca con " + garras );
        listaPresas.add("nueva");
    }
}
