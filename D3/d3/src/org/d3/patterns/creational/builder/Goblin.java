package org.d3.patterns.creational.builder;

import java.util.Random;

class Goblin implements Enemy {
    Random rng = new Random();
    public int attack() { return rng.nextInt(6); }
}