package com.sample;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizedCountExample {

    // Shared variable
    static int counter = 0;
    
    /**
     * Método que evita las condiciones de carrera.
     */
    public synchronized static void incrementar() {
        counter++;
    }

    public static void main(String[] args) {
        // Create 10 threads to increment the shared counter
        int numThreads = 10;
        List<Thread> threads = new ArrayList<>();

        LocalDateTime start = LocalDateTime.now();
        // Create and start threads
        for (int i = 1; i <= numThreads; i++) {
            Thread thread = new Thread(() -> {
                for (int x = 1; x <= 1000; x++) {
                    incrementar();
                }
            });
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Duration duration = Duration.between(start, 
                LocalDateTime.now());
        System.out.println("Total time: " 
                + duration.toMillis() + "ms");
        
        // Print the final value of the counter
        System.out.println("Final counter value: " + counter);
    }
    
    public void contar(int contador, List<Integer> lista) {
        contador++;
    }
}
