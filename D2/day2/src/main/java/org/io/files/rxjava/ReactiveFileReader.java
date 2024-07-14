package org.io.files.rxjava;

import io.reactivex.rxjava3.core.Observable;
import java.nio.file.*;

public class ReactiveFileReader {
    public static void main(String[] args) {
        Path dir = Paths.get("/path/to/directory");

        // Create an Observable that lists all files in a directory
        Observable<Path> listFiles = Observable.create(emitter -> {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
                for (Path entry : stream) {
                    emitter.onNext(entry);
                }
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });

        // Subscribe to the Observable and print each file name
        listFiles.subscribe(
                System.out::println,
                error -> System.err.println("Error encountered: " + error),
                () -> System.out.println("Completed listing files.")
        );
    }
}


