package org.io.serialization.jackson.simple;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.util.List;

// User class with Jackson annotations


public class User {

    @JsonProperty("full_name")
    private String name;

    @JsonIgnore
    private int age;

    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    // Date will be a timestamp (machine readable only),
    // e.g.: 1699699200000 which is 2023-11-11 10:40:00 UTC
    private Date birthDate;

    private Date otherAnniversaryDate;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date startDate;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date endDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String optionalField;

    private List<String> skills;

    public User() {
    }

    public User(String name, int age, Date birthDate, Date startDate, Date endDate, Date otherAnniversaryDate, String optionalField, List<String> skills) {
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
        this.otherAnniversaryDate = otherAnniversaryDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.optionalField = optionalField;
        this.skills = skills;
    }


    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getOptionalField() {
        return optionalField;
    }

    public List<String> getSkills() {
        return skills;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setOptionalField(String optionalField) {
        this.optionalField = optionalField;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public Date getOtherAnniversaryDate() {
        return otherAnniversaryDate;
    }

    public void setOtherAnniversaryDate(Date otherAnniversaryDate) {
        this.otherAnniversaryDate = otherAnniversaryDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthDate=" + birthDate +
                ", otherAnniversaryDate=" + otherAnniversaryDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", optionalField='" + optionalField + '\'' +
                ", skills=" + skills +
                '}';
    }
}

