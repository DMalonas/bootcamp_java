package org.d3.patterns.creational.abstract_factory.client;

import org.d3.patterns.creational.abstract_factory.abstract_factory_interface.GUIFactory;
import org.d3.patterns.creational.abstract_factory.abstract_product_interfaces.Button;
import org.d3.patterns.creational.abstract_factory.abstract_product_interfaces.Checkbox;

// Client code
public class Application {
    private Button button;
    private Checkbox checkbox;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}