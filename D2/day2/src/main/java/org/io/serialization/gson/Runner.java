package org.io.serialization.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.io.serialization.gson.adapters.CustomDateAdapter;

import java.util.Date;

public class Runner {

    public static void main(String[] args) {
        // Create a User object
        User user = new User("JohnDoe", "john@example.com", 30, new Date(), "secret123");

        // Create a GsonBuilder and configure it
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation() // Exclude fields without @Expose annotation
                .setVersion(1.5) // Set version for serialization/deserialization
                .registerTypeAdapter(Date.class, new CustomDateAdapter()) // Register CustomDateAdapter for Date class
                .create();

        // Serialize the user object to JSON
        String json = gson.toJson(user);
        System.out.println("Serialized JSON: " + json);

        // Deserialize the JSON string back into a User object
        User deserializedUser = gson.fromJson(json, User.class);
        System.out.println("Deserialized User: " + deserializedUser);

        // Deserialize the JSON string into a User object again
        user = gson.fromJson(json, User.class);
        System.out.println(": " + user.toString());
    }
}
