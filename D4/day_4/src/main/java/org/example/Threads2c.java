package org.example;

import java.util.Scanner;

public class Threads2c {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = 0;
        try {
            i = sc.nextInt();
        } catch (Exception e) {
            i = methodCall();
        }
        Thread t0 = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException iex) {
                System.out.println("Thread was interrupted while sleeping");
                methodCall();
            }
        });
        t0.start();

        // Interrupt the thread. Since the thread is sleeping, this will cause
        // the InterruptedException to be thrown in the thread
        t0.interrupt();
    }

    private static int methodCall() {
        return -1;
    }
}
