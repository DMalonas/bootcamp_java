package org.d3.patterns.creational.builder;

interface EnemyPackBuilder {
    EnemyPackBuilder addTroll();
    EnemyPackBuilder addGoblin();
    EnemyPack getPackManager();
    EnemyPackBuilder addTrollSwarm();
}