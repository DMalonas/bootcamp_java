package org.d3.patterns.creational.abstract_factory.concrete_products;

import org.d3.patterns.creational.abstract_factory.abstract_product_interfaces.Button;



//Concrete product implementations of the corresponding abstract interface
public class MacOSButton implements Button {
    public void paint() {
        System.out.println("Rendering a button in a MacOS style");
    }
}