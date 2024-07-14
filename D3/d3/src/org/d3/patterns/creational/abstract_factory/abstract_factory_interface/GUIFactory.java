package org.d3.patterns.creational.abstract_factory.abstract_factory_interface;

import org.d3.patterns.creational.abstract_factory.abstract_product_interfaces.Button;
import org.d3.patterns.creational.abstract_factory.abstract_product_interfaces.Checkbox;

//Abstract Factory Interface for creating abstract products
public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}