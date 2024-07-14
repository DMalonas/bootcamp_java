package org.example.battleship;

public class Ship {
    private boolean isSunk;

    public Ship() {
        isSunk = false;
    }

    public void hit() {
        isSunk = true;
        System.out.println("Hit a ship!");
    }

    public boolean isSunk() {
        return isSunk;
    }
}

