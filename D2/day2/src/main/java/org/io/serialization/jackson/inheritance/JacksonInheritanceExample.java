package org.io.serialization.jackson.inheritance;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonInheritanceExample {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Creating a dog and cat object
        Dog dog = new Dog();
        dog.setName("Rex");
        dog.setBarkVolume(5.5);

        Cat cat = new Cat();
        cat.setName("Whiskers");
        cat.setLikesCream(true);
        cat.setLives(9);

        // Serializing (object to JSON)
        String dogJson = mapper.writeValueAsString(dog);
        String catJson = mapper.writeValueAsString(cat);
        System.out.println("Dog JSON: " + dogJson);
        System.out.println("Cat JSON: " + catJson);

        // Deserializing (JSON to object)
        Animal dogObject = mapper.readValue(dogJson, Animal.class);
        Animal catObject = mapper.readValue(catJson, Animal.class);
        System.out.println("Dog Object: " + dogObject.toString());
        System.out.println("Cat Object: " + catObject);
    }
}
