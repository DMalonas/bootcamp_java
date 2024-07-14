package org.d3.practice.game_patterns;

import java.util.LinkedList;
import java.util.List;

// Mappable interface representing the Composite pattern's component interface
interface Mappable {
  void displayMap();
}


// Abstract class Area implementing Mappable, acting as the Composite in the Composite pattern
abstract class Area implements Mappable {
  private List<Mappable> children;
  private String name;
  public Area(String n) {
    name = n;
    children = new LinkedList<Mappable>();
  }

  // Method to add sub-areas, conforming to the Composite pattern
  public void addSubArea(Mappable m) {
    children.add(m);
  }

  // Overridden displayMap method, showcasing the Composite pattern
  public void displayMap() {
    System.out.println("Map of " + name);
    for(Mappable m : children) {
      m.displayMap();
    }
    System.out.println("End map of " + name);
  }
}


// Specific area classes extending Area or implementing Mappable
// These represent the Leaf in the Composite pattern
class EarthSea extends Area {
  public EarthSea() {
    super("The Earth Sea");
  }
}

class North extends Area {
  public North() {
    super("The North Reach");
  }
}

class Bereswek implements Mappable {
  public void displayMap() {
    System.out.println("Bereswek");
  }
}

class Rogmy implements Mappable {
  public void displayMap() {
    System.out.println("Rogmy");
  }
}

class Osskil extends Area {
  public Osskil() {
    super("Osskil");
  }
}

class MountainOfOs implements Mappable {
  public void displayMap() {
    System.out.println("Mountain of Oss");
  }
}

class Ebosskil implements Mappable {
  public void displayMap() {
    System.out.println("Eboskill");
  }
}

class South extends Area {
  public South() {
    super("The South Reach");
  }
}

class FarSorr implements Mappable {
  public void displayMap() {
    System.out.println("Far Sorr");
  }
}
class East extends Area {
  public East() {
    super("The East Reach");
  }
}

class Kargad extends Area {
  public Kargad() {
    super("The Kargad Lands");
  }
}

class Atuan extends Area {
  public Atuan() {
    super("Atuan");
  }
}

class Tombs implements Mappable {
  public void displayMap() {
    System.out.println("The Tombs of Atuan");
  }
}


// Decorator pattern implementation
class UnknownArea implements Mappable {
  Mappable child;
  public UnknownArea(Mappable m) {
    child = m;
  }

  // Modified behavior of displayMap method as per the Decorator pattern
  public void displayMap() {
    System.out.println("You have not been there yet");
  }
}

class Misty implements Mappable {
  Mappable child;
  public Misty(Mappable m) {
    child = m;
  }


  // Modified behavior of displayMap method as per the Decorator pattern
  public void displayMap() {
    System.out.println("The following area is covered by heavy mist today");
    child.displayMap();
  }
}

public class GamePatterns {
  // Example method showing how the Composite and Decorator patterns are used together
  static void run1() {
    // Run 1
    var north = new North();
    north.addSubArea(new Bereswek());
    //   adding more areas and applying decorators
    var oss = new Osskil();
    oss.addSubArea(new MountainOfOs());
    oss.addSubArea(new Ebosskil());
    north.addSubArea(oss);
    north.addSubArea(new Rogmy());

    var south = new South();
    south.addSubArea(new FarSorr());

    var east = new East();
    var karg = new Kargad();
    east.addSubArea(karg);
    var atuan = new Atuan();
    karg.addSubArea(atuan);
    var tombs = new Tombs();
    atuan.addSubArea(tombs);

    var earthSea = new EarthSea();
    earthSea.addSubArea(north);
    earthSea.addSubArea(east);
    earthSea.addSubArea(south);
    earthSea.displayMap();
  }

  static void run2() {
    // Run 2
    var north = new North();
    north.addSubArea(new Bereswek());
    var oss = new Osskil();
    oss.addSubArea(new UnknownArea(new MountainOfOs()));
    oss.addSubArea(new Ebosskil());
    north.addSubArea(new Misty(oss));
    north.addSubArea(new UnknownArea(new Rogmy()));

    var south = new South();
    south.addSubArea(new Misty(new UnknownArea(new FarSorr())));

    var east = new East();
    var karg = new Kargad();
    east.addSubArea(karg);
    var atuan = new Atuan();
    karg.addSubArea(new Misty(new UnknownArea(atuan)));
    var tombs = new Tombs();
    atuan.addSubArea(tombs);

    var earthSea = new EarthSea();
    earthSea.addSubArea(north);
    earthSea.addSubArea(east);
    earthSea.addSubArea(south);

    earthSea.displayMap();
  }

  public static void main(String[] args) {
    run1();
    System.out.println("\n========\n");
    run2();
  }
}

