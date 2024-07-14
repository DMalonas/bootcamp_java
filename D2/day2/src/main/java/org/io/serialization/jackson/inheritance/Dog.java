package org.io.serialization.jackson.inheritance;

public class Dog extends Animal {
    private double barkVolume;

    // Default constructor
    public Dog() {}

    // Constructor with name and barkVolume
    public Dog(String name, double barkVolume) {
        super(name);
        this.barkVolume = barkVolume;
    }

    // Getter and setter
    public double getBarkVolume() {
        return barkVolume;
    }

    public void setBarkVolume(double barkVolume) {
        this.barkVolume = barkVolume;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + getName() + '\'' +
                ", barkVolume=" + barkVolume +
                '}';
    }
}
