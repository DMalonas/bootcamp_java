package org.example;

import java.util.concurrent.CompletableFuture;

public class ComplFut2 {
    public static void main(String[] args) throws Exception {
        final CompletableFuture<Integer> f1 =
                // Supply async follows the supplier interface
                CompletableFuture.supplyAsync( () -> {
    	        try { Thread.sleep(500); } catch (InterruptedException ex) {}
    		return 101;
   	    });
        // also add something on the completion stage
        f1.whenComplete( (r, ex) -> { System.out.println("I am complete " + r); });


        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");


        while(!f1.isDone()) {
            System.out.println("Still not reporting that it is done..");
            try { Thread.sleep(300); } catch (InterruptedException ex) {}
        }
        System.out.println("Now it is done: " + f1.get());
    }
}
