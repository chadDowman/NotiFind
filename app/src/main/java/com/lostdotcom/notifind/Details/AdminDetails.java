package com.lostdotcom.notifind.Details;


// The purpose of this class is to initialise the activity_custom_rows of the admin when logging in and when the admin account is created. We will use the getters and setters in this
// class once the thing is initialised

public class AdminDetails {

    private String adminEmail;
    private String adminPassword;
    private String adminLocation;
    private String adminPhoneNumber;
    private boolean admin;

    public AdminDetails() {
    }

    //Constructors
    public AdminDetails(String adminEmail, String adminPassword, String adminLocation, String adminPhoneNumber, boolean admin) {
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
        this.adminLocation = adminLocation;
        this.adminPhoneNumber = adminPhoneNumber;
        this.admin = admin;
    }

    // Getters and Setters

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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }


}
