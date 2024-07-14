package org.d3.patterns.creational.factory_method;

public class Main {
    public static void main(String[] args) {
        EnemyPack trolls = new TrollPack(3); // Pack of 3 trolls
        EnemyPack goblins = new GoblinPack(5); // Pack of 5 goblins

        System.out.println("Troll attacks:");
        trolls.simulateAttack();

        System.out.println("\nGoblin attacks:");
        goblins.simulateAttack();
    }
}
