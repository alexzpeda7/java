package com.sample;

public class RunnableSample implements Runnable {

    private Object dao;

    @Override
    public void run() {
        System.out.println("Running RunnableSample");
    }


}
