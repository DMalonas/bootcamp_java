package org.d3.patterns.creational.factory_method;

// Concrete subclass of EnemyPack for creating Trolls
class TrollPack extends EnemyPack {
    public TrollPack(int n) {
        super(n);
    }

    @Override
    protected Enemy makeEnemy() {
        return new Troll();
    }
}