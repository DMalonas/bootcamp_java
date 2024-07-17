package org.example;

public class Threads2b {
    public static void main(String[] args) {
        Thread t0 = new Thread(() -> {
            long cnt = 0;
            boolean receivedInt = false;

            while(
                    !Thread.currentThread().isInterrupted()
                    & (cnt < 10000000)
            ) {
                cnt = cnt + 1;
            }
            System.out.println("Count: " + cnt + " Interrupted: " +
                    Thread.currentThread().isInterrupted());
        });

        t0.start();


        try {
            Thread.sleep(5);
            t0.interrupt();
            t0.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
