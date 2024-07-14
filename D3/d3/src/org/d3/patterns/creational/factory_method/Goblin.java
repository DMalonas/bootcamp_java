package org.d3.patterns.creational.factory_method;

import java.util.Random;

class Goblin implements Enemy {
    Random rng = new Random();
    public int attack() { return rng.nextInt(6); }
}