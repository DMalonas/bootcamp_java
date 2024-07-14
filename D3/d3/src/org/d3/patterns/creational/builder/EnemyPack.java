package org.d3.patterns.creational.builder;

import java.util.LinkedList;
import java.util.List;

class EnemyPack {
    List<Enemy> pack = new LinkedList<>();

    // This constructor should be private to prevent direct instantiation.
    private EnemyPack() { }

    // Static inner class that implements the Builder interface.
    static class MixedPackBuilder implements EnemyPackBuilder {
        private EnemyPack packManager;

        public MixedPackBuilder() {
            packManager = new EnemyPack();
        }

        public MixedPackBuilder addGoblin() {
            packManager.pack.add(new Goblin());
            return this;
        }

        public MixedPackBuilder addTroll() {
            packManager.pack.add(new Troll());
            return this;
        }

        public MixedPackBuilder addTrollSwarm() {
            for (int i = 0; i < 10; i++)
                packManager.pack.add(new Troll());
            return this;
        }
        public EnemyPack getPackManager() {
            return packManager;
        }
    }
}