package com.sample;
public class LostCountExample {

    // Shared variable
    static int counter = 10;

    public static void main(String[] args) {
        // Create 10 threads to increment the shared counter
        int numThreads = 10;

        // Create and start threads
        for (int i = 1; i <= numThreads; i++) {
            new Thread(() -> {
                for (int x = 1; x <= 1000; x++) {
                    counter++;  // Increment the shared counter (no synchronization)
                }
                System.out.println("Thread finished");
            }).start();
        }
        
        // Allow some time for threads to finish
        try {
            Thread.sleep(2000);  // Sleep for 2 seconds to let threads run
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Print the final value of the counter
        System.out.println("Final counter value: " + counter);
    }
}
