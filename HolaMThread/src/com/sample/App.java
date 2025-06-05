package com.sample;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        List<MyFirstThread> listaThreads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listaThreads.add(new MyFirstThread(i));
        }
        
        listaThreads.forEach(MyFirstThread::start);
    }
}
