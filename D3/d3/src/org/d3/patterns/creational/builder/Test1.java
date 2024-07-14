package org.d3.patterns.creational.builder;

public class Test1 {
    public static void main(String[] args) {
        EnemyPackBuilder builder = new EnemyPack.MixedPackBuilder();
        EnemyPack p = builder.addTroll()
                            .addTroll()
                            .addTrollSwarm()
                            .addGoblin()
                            .getPackManager();
        for (Enemy enemy : p.pack) {
            System.out.println("Enemy attacks with power: " + enemy.attack());
        }
    }
}