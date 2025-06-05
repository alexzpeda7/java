package edu.itq.tools;

import java.util.Random;

public class RandomWordGenerator {

    private static final int LONG_NO_CONTROL = 8;

    public static String generateRandomWord() {
        Random random = new Random();
        String consonantes = "bcdfghjklmnpqrstvwxyz"; // Consonantes para elegir
        String vocales = "aeiou"; // Vocales para elegir
        
        // Longitud aleatoria entre 5 y 20
        int length = random.nextInt(16) + 5; // Genera un número entre 5 y 20
        
        StringBuilder noControl = new StringBuilder();
        
        // Variable para llevar un control de la última letra (vocal o consonante)
        boolean lastWasVowel = false;

        for (int i = 0; i < length; i++) {
            random.nextInt() ;
            char randomChar;
            
            // Si la última letra fue una vocal, seleccionamos una consonante
            if (lastWasVowel) {
                randomChar = consonantes.charAt(random.nextInt(consonantes.length()));
                lastWasVowel = false; // Ahora la última letra es consonante
            } else {
                // Si la última letra fue una consonante o es la primera letra, seleccionamos una vocal
                randomChar = vocales.charAt(random.nextInt(vocales.length()));
                lastWasVowel = true; // Ahora la última letra es vocal
            }
            
            noControl.append(randomChar); // Añadimos la letra al StringBuilder
        }
        
        return noControl.toString(); // Devolvemos la palabra generada
    }

    // Método para generar una palabra aleatoria con longitud entre 5 y 20
    public static String generateNoControl() {
        Random random = new Random();
        StringBuilder noControl = new StringBuilder();
        for (int i = 0; i < LONG_NO_CONTROL; i++) {
            int nextInt = Math.abs(random.nextInt()  % 10);
            noControl.append(nextInt);
        }
        return noControl.toString();
    }

    public static void main(String[] args) {
        int numWords = 5; // Número de palabras a generar

        System.out.println("Palabras aleatorias generadas:");
        for (int i = 0; i < numWords; i++) {
            String randomWord = generateRandomWord();
            System.out.println(randomWord);
        }
    }
}
