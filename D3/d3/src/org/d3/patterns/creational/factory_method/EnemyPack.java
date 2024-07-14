package org.d3.patterns.creational.factory_method;

import java.util.LinkedList;
import java.util.List;

abstract class EnemyPack {
    List<Enemy> pack = new LinkedList<Enemy>();
    protected abstract Enemy makeEnemy();

    public EnemyPack(int n) {
        for(int i = 0; i < n; i++) {
            pack.add(makeEnemy());
        }
    }

    // Let's add a method to simulate attacks from the pack
    public void simulateAttack() {
        for (Enemy enemy : pack) {
            System.out.println("An enemy attacks and deals " + enemy.attack() + " damage!");
        }
    }
}