package com.sample;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MainCountExample {

    // Shared variable
    static Integer counter = 0;

    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.now();
        // Create and start threads
        for (int i = 1; i <= 10000000; i++) {
            counter++;
        }
        Duration duration = Duration.between(start, 
                LocalDateTime.now());
        System.out.println("Total time: " 
                + duration.toMillis() + "ms");
        // Print the final value of the counter
        System.out.println("Final counter value: " + counter);
    }
}

