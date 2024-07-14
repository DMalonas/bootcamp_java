package org.day4.behavior_parameterization;

public class AnonymousClassThreadExample {
    public static void main(String[] args) {
        // Creating a thread using an anonymous class that implements Runnable
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("The thread is running.");
            }
        });

        // Starting the thread
        myThread.start();
    }
}
