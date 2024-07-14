package org.d3.patterns.creational.abstract_factory;

import org.d3.patterns.creational.abstract_factory.abstract_factory_interface.GUIFactory;
import org.d3.patterns.creational.abstract_factory.client.Application;
import org.d3.patterns.creational.abstract_factory.concrete_factories.MacOSFactory;
import org.d3.patterns.creational.abstract_factory.concrete_factories.WindowsFactory;

public class Demo {
    public static void main(String[] args) {
        Application client;
        GUIFactory factory;

        String osName = System.getProperty("os.name").toLowerCase();
        factory = createFactoryOfTypeX(osName);

        client = new Application(factory);
        client.paint();
    }

    private static GUIFactory createFactoryOfTypeX(String osName) {
        GUIFactory factory;
        if (osName.contains("windows")) {
            factory = new WindowsFactory();
        } else {
            factory = new MacOSFactory();
        }
        return factory;
    }
}