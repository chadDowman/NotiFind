package com.lostdotcom.notifind.Details;

// The purpose of this class is to initialise the activity_custom_rows of the missing persons once a admin clicks post missing persons report. We will use the getters and setters in this
// class once the attributes are initialised

public class ReportDetails {

    private String name;
    private String surname;
    private String age;
    private String eyeColor;
    private String weight;
    private String height;
    private String lastSeenLocation;
    private String description;
    private String imageUri;

    public ReportDetails() {
    }

    public ReportDetails(String name, String surname, String age, String eyeColor, String weight, String height, String lastSeenLocation, String description, String imageUri) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.eyeColor = eyeColor;
        this.weight = weight;
        this.height = height;
        this.lastSeenLocation = lastSeenLocation;
        this.description = description;
        this.imageUri = imageUri;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
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

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
