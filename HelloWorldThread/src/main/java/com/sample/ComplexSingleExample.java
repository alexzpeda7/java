package com.sample;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ComplexSingleExample {

    // Shared variable
    static int counter = 0;

    /**
     * Método que evita las condiciones de carrera.
     */
    public synchronized static void tareaCompleja() {
        System.out.println();
        counter++;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Create 10 threads to increment the shared counter
        int numThreads = 10;
        List<Thread> threads = new ArrayList<>();

        LocalDateTime start = LocalDateTime.now();
        // Create and start threads
        for (int i = 1; i <= numThreads; i++) {
            for (int x = 1; x <= 3; x++) {
                tareaCompleja();
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
