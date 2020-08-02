package com.lostdotcom.notifind.Details;

public class AdminDetails {

    private int adminID;
    private String adminLocation;
    private String adminType;
    private String adminPhoneNumber;
    private String adminEmail;
    private String adminPassword;

    public AdminDetails(String adminLocation, String adminType, String adminPhoneNumber, String adminEmail, String adminPassword) {
        this.adminLocation = adminLocation;
        this.adminType = adminType;
        this.adminPhoneNumber = adminPhoneNumber;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
    }


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

    public String getAdminType() {
        return adminType;
    }

    public void setAdminType(String adminType) {
        this.adminType = adminType;
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
