package org.io.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.File;
import java.io.IOException;
import java.util.List;

// Define the POJO (Plain Old Java Object) classes to match the JSON structure
class Person {
    public String name;
    public int age;
    public boolean isEmployed;
    public Address address;
    public List<PhoneNumber> phoneNumbers;
    public List<String> skills;
    public List<Dependent> dependents;

    // Define nested classes for address, phone numbers and dependents
    static class Address {
        public String street;
        public String city;
        public String postalCode;
    }

    static class PhoneNumber {
        public String type;
        public String number;
    }

    static class Dependent {
        public String name;
        public int age;
        public String relation;
    }
}

public class JsonDeserializerExample {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Read JSON from a file in the resources directory of a Gradle project
            Person person = mapper.readValue(new File("src/main/resources/person.json"), Person.class);

            // Output the deserialized data to the console (or use it as needed)
            System.out.println("Name: " + person.name);
            System.out.println("Age: " + person.age);
            // ... You can print out the rest or use the 'person' object as needed
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
