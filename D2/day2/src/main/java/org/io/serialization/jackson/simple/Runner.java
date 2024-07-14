package org.io.serialization.jackson.simple;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Runner {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

        try {
            User user = new User();
            user.setName("John Doe");
            user.setBirthDate(new Date()); // use current date for example
            user.setStartDate(new Date());
            user.setEndDate(new Date());
            user.setOtherAnniversaryDate(sdf.parse("14 Feb 2023")); // Set otherAnniversaryDate
            user.setSkills(Arrays.asList("Java", "SQL", "Management"));

            // Serialize the user object to JSON
            String userJson = mapper.writeValueAsString(user);
            System.out.println("Serialized user to JSON:");
            System.out.println(userJson);

            // Deserialize JSON back to User object
            User deserializedUser = mapper.readValue(userJson, User.class);
            System.out.println("Deserialized JSON to user object:");
            System.out.println("Name: " + deserializedUser.getName());

            System.out.println("BirthDate: " + sdf.format(deserializedUser.getBirthDate()));
            System.out.println("OtherAnniversaryDate: " + sdf.format(deserializedUser.getOtherAnniversaryDate()));
            System.out.println("OtherAnniversaryDate: " + deserializedUser.getOtherAnniversaryDate());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
