package org.io.serialization.jackson.inheritance;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * The @JsonTypeInfo annotation is used to indicate details of what type information is included in serialization
 * and how it is included when dealing with polymorphism (i.e., inheritance in classes).
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
/**
 * The @JsonSubTypes annotation is used to indicate subtypes of the annotated type (Animal in this case).
 * It helps in deserializing the correct subclass based on the type information provided in the JSON.
 */
@JsonSubTypes({
    @JsonSubTypes.Type(value = Dog.class, name = "dog"),
    @JsonSubTypes.Type(value = Cat.class, name = "cat")
})
public abstract class Animal {
    private String name;

    // Default constructor
    public Animal() {}

    // Constructor with name
    public Animal(String name) {
        this.name = name;
    }

    // Getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
