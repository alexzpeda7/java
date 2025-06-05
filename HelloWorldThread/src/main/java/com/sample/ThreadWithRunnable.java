package com.sample;

public class ThreadWithRunnable {

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableSample());
        thread.start();
        Runnable runnableAnonimo = 
                () -> System.out.println("Running RunnableAnonimo");
        Thread threadAnonimo = new Thread(runnableAnonimo);
        threadAnonimo.start();
                
        System.out.println("Terminate main thread");
    }

}
