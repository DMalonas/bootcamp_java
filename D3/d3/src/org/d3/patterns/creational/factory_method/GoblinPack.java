package org.d3.patterns.creational.factory_method;

// Concrete subclass of EnemyPack for creating Goblins
class GoblinPack extends EnemyPack {
    public GoblinPack(int n) {
        super(n);
    }

    @Override
    protected Enemy makeEnemy() {
        return new Goblin();
    }
}