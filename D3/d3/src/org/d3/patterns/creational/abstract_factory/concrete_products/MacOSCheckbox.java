package org.d3.patterns.creational.abstract_factory.concrete_products;

import org.d3.patterns.creational.abstract_factory.abstract_product_interfaces.Checkbox;


//Concrete product implementations of the corresponding abstract interface
public class MacOSCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Rendering a checkbox in a MacOS style");
    }
}