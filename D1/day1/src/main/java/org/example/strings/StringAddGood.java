package org.example.strings;

public class StringAddGood {
    public static void main(String[] args) {
        StringBuilder b = new StringBuilder();
        for(int i = 0; i < 1000; i++) {
            b.append(i);
        }
        System.out.println(";" + b.toString().substring(0, 30));
    }
}

