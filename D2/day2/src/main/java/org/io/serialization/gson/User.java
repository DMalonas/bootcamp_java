package org.io.serialization.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import org.io.serialization.gson.adapters.CustomDateAdapter;

import java.util.Date;

public class User {

    // This annotation specifies the serialized name of this field as 'username'.
    @Expose
    @SerializedName("username")
    private String name;

    // This field is exposed for serialization and deserialization and is available since version 1.0.
    @Expose
    @Since(1.0)
    private String email;

    // This field will be included only until version 2.0.
    @Until(2.0)
    private int age;

    // Specifies a custom serializer/deserializer for this field.
    @Expose
    @JsonAdapter(CustomDateAdapter.class)
    private Date registeredDate;

    // Marked as transient, this field will be ignored during serialization and deserialization.
    private transient String password;

    // Default constructor.
    public User() {
    }

    // Constructor with all fields.
    public User(String name, String email, int age, Date registeredDate, String password) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.registeredDate = registeredDate;
        this.password = password;
    }

    // Standard getters and setters for all fields.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Overrides the toString method to provide a string representation of the User object.
    // Note: Password is intentionally omitted for security reasons.
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", registeredDate=" + registeredDate +
                '}';
    }
}
