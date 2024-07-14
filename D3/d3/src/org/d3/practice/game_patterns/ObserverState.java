package org.d3.practice.game_patterns;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

// EnemyAttack interface representing the State in the State pattern
interface EnemyAttack {
  int attack(AbstractEnemy enemy);
  String getName();

}


// Concrete states representing different behaviors of enemies
class Enraged implements EnemyAttack  {
  public int attack(AbstractEnemy enemy) {
    return enemy.getBaseDamage() * 2;
  }
  public String getName() {
    return "Enraged";
  }
}

// Concrete state representing different behaviors of Troll
class Normal implements EnemyAttack {
  public int attack(AbstractEnemy enemy) {
    return enemy.getBaseDamage();
  }
  public String getName() {
    return "Normal";
  }
}

// Concrete state representing different behaviors of Troll
class Afraid implements EnemyAttack {
  public int attack(AbstractEnemy enemy) {
    return enemy.getBaseDamage() / 2;
  }
  public String getName() {
    return "Afraid";
  }
}


// Observer pattern interfaces
interface HealthSubject { //
  void addObserver(HealthObserver o);
  void notifyObservers();
} //

interface HealthObserver { //
  void update(int newHealth); //
} //


// Concrete observer class
class HealthLogger implements HealthObserver { //
  public void update(int health) { //
    System.out.println("Enemy health is now: " + health); //
  } //
} //


// Abstract class representing common features of all enemies
abstract class AbstractEnemy implements HealthSubject {
  protected EnemyAttack state;
  protected Random rnd;
  protected final int baseHealth;
  protected int health;
  protected List<HealthObserver> observers;

  public AbstractEnemy() {
    state = new Normal();
    rnd = new Random();
    baseHealth = 100;
    health = baseHealth;
    observers = new LinkedList<>();
  }

  public void addObserver(HealthObserver o) {
    observers.add(o);
  }

  public void notifyObservers() {
    for(HealthObserver o: observers) {
      o.update(health);
    }
  }

  public int getBaseDamage() {
    return 10; // Base damage, can be overridden in derived classes
  }

  public void setState(EnemyAttack newState) {
    state = newState;
  }

  public void setHP(int h) {
    health = h;
    notifyObservers();
    if (h < baseHealth * 0.1) {
      setState(new Afraid());
    }
  }

  public abstract int attack();
}


// Class Troll implementing HealthSubject and managing its state
class Troll extends AbstractEnemy {
  public Troll() {
    super();
  }

  public int attack() {
    System.out.println("Troll is " + state.getName());
    int damage = state.attack(this);
    System.out.println("Troll Attacks for " + damage);
    return damage;
  }

  public boolean getEnraged() {
    return rnd.nextDouble() < 0.3;
  }

}


public class ObserverState {
  public static void main(String[] args) {
    Troll t = new Troll();
    HealthLogger logger = new HealthLogger();
    t.addObserver(logger);

    t.attack();
    t.setHP(30);
    t.attack();
    t.attack();
    t.setHP(8);
    t.attack();
  }
}
  
