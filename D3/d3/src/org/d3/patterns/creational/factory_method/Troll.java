package org.d3.patterns.creational.factory_method;

import java.util.Random;

class Troll implements Enemy {
    Random rng = new Random();
    public int attack() { return rng.nextInt(6) + rng.nextInt(6) + 4; }
}