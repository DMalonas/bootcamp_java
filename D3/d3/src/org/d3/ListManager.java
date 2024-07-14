package org.d3;

import java.util.List;

public class ListManager {

    public void listConsumer(List<String> listOfStrings) {
        for(String element : listOfStrings) {
            System.out.println(element);
        }
    }
}
