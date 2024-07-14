package org.io.files.rxjava;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.ResourceSubscriber;

public class RxJavaBackPressureDemo {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Integer> flowable = Flowable.create(emitter -> {
            for (int i = 0; i < 1000; i++) {
                if (emitter.isCancelled()) {
                    return;
                }
                System.out.println("Emitting " + i);
                emitter.onNext(i);
                Thread.sleep(5); // simulate some delay
            }
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER); // Backpressure strategy

        flowable
        .observeOn(Schedulers.io())
        .subscribeWith(new ResourceSubscriber<>() {
        @Override
        public void onStart() {
            // Requesting the first item
            request(1);
        }

        @Override
        public void onNext(Integer integer) {
            // Processing received item
            System.out.println("Processing " + integer);
            try {
                Thread.sleep(100); // Simulate some work on the thread.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Requesting the next item after processing
            request(1);
        }

        @Override
        public void onError(Throwable t) {
            t.printStackTrace();
        }

        @Override
        public void onComplete() {
            System.out.println("Completed");
            }
        });

        // Sleep to prevent the program from exiting immediately since the processing is happening on a different thread.
        Thread.sleep(20000); // Sleep longer if you have more items or processing is taking longer.
    }
}


