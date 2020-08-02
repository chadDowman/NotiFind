package com.lostdotcom.notifind.Details;

public class ReportDetails {

    private String name;
    private String surname;
    private int age;
    private String eyeColor;
    private int weight;
    private int height;
    private String lastSeenLocation;
    private String description;

    public ReportDetails(String name, String surname, int age, String eyeColor, int weight, int height, String lastSeenLocation, String description) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.eyeColor = eyeColor;
        this.weight = weight;
        this.height = height;
        this.lastSeenLocation = lastSeenLocation;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getLastSeenLocation() {
        return lastSeenLocation;
    }

    public void setLastSeenLocation(String lastSeenLocation) {
        this.lastSeenLocation = lastSeenLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
