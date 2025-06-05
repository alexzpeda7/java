package com.sample;

public class MyThreadRunnable {
    Thread thread = new Thread (new RunnableSample());
    thread.start();
    System.out.println("Terminate mani thread");

}
