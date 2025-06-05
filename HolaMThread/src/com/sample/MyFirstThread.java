package com.sample;

public class MyFirstThread extends Thread {

    /**
     * Crea un hilo con un nombre específico.
     * @param i Sufijo del nombre del hilo.
     */
    public MyFirstThread(int i) {
        this.setName("MyThread-" + i);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(this.getName() + " running " + i);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
