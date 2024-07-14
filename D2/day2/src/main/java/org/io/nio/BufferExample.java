package org.io.nio;

import java.nio.ByteBuffer;

public class BufferExample {
    public static void main(String[] args) {

        // Allocate a ByteBuffer with a capacity of 10 bytes
        ByteBuffer buffer = ByteBuffer.allocate(10);
        // Check the initial status of the buffer
        System.out.println("Initial Position: " + buffer.position());
        System.out.println("Initial Limit: " + buffer.limit());
        System.out.println("Capacity: " + buffer.capacity());

        // Write data into the buffer
        for (int i = 0; i < 5; i++) {
            buffer.put((byte) ('a' + i)); // Store letters a, b, c, d, e
        }

        // Check the status after writing data
        System.out.println("Position after data write: " + buffer.position());

        // Flip the buffer to prepare it for reading
        buffer.flip();
        System.out.println("Position after flip: " + buffer.position());
        System.out.println("Limit after flip: " + buffer.limit());

        // Read data from the buffer
        while (buffer.hasRemaining()) {
            char c = (char) buffer.get();
            System.out.println("Read data: " + c);
        }
        // Clear the buffer to make it ready for writing again
        buffer.clear();
        System.out.println("Buffer cleared: ");
        System.out.println("Position after clear: " + buffer.position());
        System.out.println("Limit after clear: " + buffer.limit());
    }
}

