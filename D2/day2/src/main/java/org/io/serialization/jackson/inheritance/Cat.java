package org.io.serialization.jackson.inheritance;

public class Cat extends Animal {
    private boolean likesCream;
    private int lives;

    // Default constructor
    public Cat() {}

    // Constructor with name, likesCream, and lives
    public Cat(String name, boolean likesCream, int lives) {
        super(name);
        this.likesCream = likesCream;
        this.lives = lives;
    }

    // Getter and setter
    public boolean isLikesCream() {
        return likesCream;
    }

    public void setLikesCream(boolean likesCream) {
        this.likesCream = likesCream;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + getName() + '\'' +
                ", likesCream=" + likesCream +
                ", lives=" + lives +
                '}';
    }
}
