package com.sample;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        List<MyFirstThread> listaThreads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MyFirstThread myThread = new MyFirstThread(i);
            listaThreads.add(myThread);
        }
        for (int i = 0; i < listaThreads.size(); i++) {
            listaThreads.get(i).start();
        }
//        for (MyFirstThread myThread : listaThreads) {
//        }
        
//        MyFirstThread myFirstThread = new MyFirstThread(1);
//        MyFirstThread mySecondThread = new MyFirstThread(2);
//        myFirstThread.start();
//        mySecondThread.start();
    }

}
