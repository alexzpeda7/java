package com.sample;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCountExample {

    // Shared variable
    static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        // Create 10 threads to increment the shared counter
        int numThreads = 10;
        List<Thread> threads = new ArrayList<>();

        LocalDateTime start = LocalDateTime.now();
        // Create and start threads
        for (int i = 1; i <= numThreads; i++) {
            Thread thread = new Thread(() -> {
                for (int x = 1; x <= 1000000; x++) {
                    counter.getAndIncrement();  // Increment the shared counter (no synchronization)
                }
//                System.out.println("Thread finished");
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
        
//        // Allow some time for threads to finish
//        try {
//            Thread.sleep(2000);  // Sleep for 2 seconds to let threads run
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        
        // Print the final value of the counter
        System.out.println("Final counter value: " + counter);
    }
    
    public void contar(int contador, List<Integer> lista) {
        contador++;
    }
}

class Counter implements Runnable {
    
    public synchronized void contar(int contador) {
        contador++;
    }
    int contador;
    List<Integer> lista;
    String nombre[];
    @Override
    public void run() {
        nombre = new String[100];
        contador++;
        lista.add(contador);
    }
}
