package org.d3.patterns.structural.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MyList {
    public static <T> Map<T, T> toMap(List<T> data) {
        if((data.size() % 2) != 0) {
            throw new IllegalArgumentException("Need an even number of arguments");
        }
        Map<T, T> ret = new HashMap<>();
        for(int i = 0; i < data.size(); i += 2) {
            ret.put(data.get(i), data.get(i+1));
        }
        return ret;
    }


    public static void main(String[] args) {
        List<String> lst = List.of("Hi", "John", "john", "hi");
        System.out.println(lst);
        Map<String, String> map = MyList.toMap(lst);
        System.out.println(map);
    }
}
