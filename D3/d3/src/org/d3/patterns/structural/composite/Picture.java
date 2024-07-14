package org.d3.patterns.structural.composite;

import java.util.LinkedList;
import java.util.List;

// Leaf interface in Composite Pattern
interface Drawable {
    void draw();
}

// Leaf class implementing Drawable
class Line implements Drawable {
    public void draw() { System.out.println("a Line"); }
}

// Another Leaf class implementing Drawable
class Box implements Drawable {
    public void draw() { System.out.println("a Box"); }
}

// Component class in Composite Pattern
abstract class Graphic implements Drawable {
    // Collection of children, adhering to the Composite pattern
    List<Drawable> children = new LinkedList<Drawable>();

    // Methods to manipulate the collection of children
    public void add(Drawable d) { children.add(d); }
    public Drawable del(int i) { return children.remove(i); }
    public Drawable get(int i) { return children.get(i); }
    public int childrenCount() { return children.size(); }
}

// Composite class extending Graphic
public class Picture extends Graphic {
    // draw method implementing the logic to handle the children Drawables
    public void draw() {
        System.out.println("Starting picture");
        // Iterating over all children and invoking their draw method
        for(int i = 0; i < childrenCount(); i++) {
            get(i).draw();
        }
        System.out.println("Finished picture");
    }

    // Demonstration of using the Composite pattern
    public static void main(String[] args) {
        Graphic pic1 = new Picture();
        pic1.add(new Line());
        pic1.add(new Line());
        pic1.add(new Line());
        Graphic pic2 = new Picture();
        pic2.add(new Line());
        pic2.add(new Box());
        pic1.add(pic2); // Adding a Picture (composite) to another Picture
        pic1.draw(); // Drawing the entire hierarchy
    }
}
