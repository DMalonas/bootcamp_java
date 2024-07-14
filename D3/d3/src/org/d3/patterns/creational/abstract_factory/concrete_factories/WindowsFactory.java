package org.d3.patterns.creational.abstract_factory.concrete_factories;

import org.d3.patterns.creational.abstract_factory.abstract_factory_interface.GUIFactory;
import org.d3.patterns.creational.abstract_factory.abstract_product_interfaces.Button;
import org.d3.patterns.creational.abstract_factory.abstract_product_interfaces.Checkbox;
import org.d3.patterns.creational.abstract_factory.concrete_products.WindowsButton;
import org.d3.patterns.creational.abstract_factory.concrete_products.WindowsCheckbox;

//Concrete factory for creating and returing Windows components
public class WindowsFactory implements GUIFactory {
    public Button createButton() {
        return new WindowsButton();
    }

    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}