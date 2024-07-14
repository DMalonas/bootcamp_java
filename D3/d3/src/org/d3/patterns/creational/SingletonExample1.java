package org.d3.patterns.creational;

class GlobalCounter {
    private int count = 0;
    // The single instance of this class
    //Static so it belongs the class itself rather than an instance of the class
    private static GlobalCounter instance = new GlobalCounter();
    // instance accessor
    public static GlobalCounter getInstance() { return instance; }
    // Methods
    public int getCount() { return count; }
    public void countUp() { count = count + 1; }
    // private -- no one can instantiate
    private GlobalCounter() { }
}

public class SingletonExample1 {
    public static void main(String[] args) {
        GlobalCounter c1 = GlobalCounter.getInstance();
        GlobalCounter c2 = GlobalCounter.getInstance();
        System.out.println("c1 == c2 => " + (c1 == c2));
        c1.countUp();
        c1.countUp();
        System.out.println("c1: " + c1.getCount() + 
                           " c2: " + c2.getCount());
    }
}
