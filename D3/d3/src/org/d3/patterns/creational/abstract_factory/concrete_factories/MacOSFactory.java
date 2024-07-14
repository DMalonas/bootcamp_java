package org.d3.patterns.creational.abstract_factory.concrete_factories;

import org.d3.patterns.creational.abstract_factory.abstract_factory_interface.GUIFactory;
import org.d3.patterns.creational.abstract_factory.abstract_product_interfaces.Button;
import org.d3.patterns.creational.abstract_factory.abstract_product_interfaces.Checkbox;
import org.d3.patterns.creational.abstract_factory.concrete_products.MacOSButton;
import org.d3.patterns.creational.abstract_factory.concrete_products.MacOSCheckbox;

//Concrete factory for creating and returing MacOS components
public class MacOSFactory implements GUIFactory {
    public Button createButton() {
        return new MacOSButton();
    }

    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}