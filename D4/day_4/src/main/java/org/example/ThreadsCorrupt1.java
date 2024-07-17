package org.example;

import java.util.ArrayList;
class Corruptor1 implements Runnable {
    public static int cnt = 0;
    public void run() {
        for(int i = 0; i < 1000; i++) {
            cnt += 1;
        }
    }
}
public class ThreadsCorrupt1 {
    public static void main(String[] args) {
        ArrayList<Thread> c = new ArrayList<>(); //Code to the interface not the implementation
        for(int i = 0; i < 1000; i++) {
            c.add(new Thread(new Corruptor1()));
        }
        for(int i = 0; i < 1000; i++) {
            c.get(i).start();
        }
        try {
            for(int i = 0; i < 1000; i++) {
c.get(i).join();
            }
        } catch(InterruptedException ex) {}
        System.out.println("Shared var is: " + Corruptor.cnt);
    }
}
