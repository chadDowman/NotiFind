package com.lostdotcom.notifind.Details;


// The purpose of this class is to initialise the activity_custom_rows of the admin when logging in and when the admin account is created. We will use the getters and setters in this
// class once the thing is initialised

public class AdminDetails {

    private int adminID;
    private String adminLocation;
    private String adminPhoneNumber;
    private String adminEmail;
    private String adminPassword;

    //Constructors
    public AdminDetails(String adminLocation, String adminPhoneNumber, String adminEmail, String adminPassword) {
        this.adminLocation = adminLocation;
        this.adminPhoneNumber = adminPhoneNumber;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
    }

    public AdminDetails(String adminEmail, String adminPassword) {
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
    }

    // Getters and Setters
    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getAdminLocation() {
        return adminLocation;
    }

    public void setAdminLocation(String adminLocation) {
        this.adminLocation = adminLocation;
    }

    public String getAdminPhoneNumber() {
        return adminPhoneNumber;
    }

    public void setAdminPhoneNumber(String adminPhoneNumber) {
        this.adminPhoneNumber = adminPhoneNumber;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}
