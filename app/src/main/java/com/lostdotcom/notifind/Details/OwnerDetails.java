package com.lostdotcom.notifind.Details;

// The purpose of this class is to initialise the activity_custom_rows of the Owner when logging in. We will use the getters and setters in this
// class once it is initialised

public class OwnerDetails {

    private int ownerID;
    private String ownerEmail;
    private String ownerPassword;

    public OwnerDetails(String ownerEmail, String ownerPassword) {
        this.ownerEmail = ownerEmail;
        this.ownerPassword = ownerPassword;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerPassword() {
        return ownerPassword;
    }

    public void setOwnerPassword(String ownerPassword) {
        this.ownerPassword = ownerPassword;
    }
}
