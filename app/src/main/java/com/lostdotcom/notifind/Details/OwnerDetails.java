package com.lostdotcom.notifind.Details;

public class OwnerDetails {

    private int ownerID;
    private String ownerEmail;
    private String ownerPassowrd;

    public OwnerDetails(String ownerEmail, String ownerPassowrd) {
        this.ownerEmail = ownerEmail;
        this.ownerPassowrd = ownerPassowrd;
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

    public String getOwnerPassowrd() {
        return ownerPassowrd;
    }

    public void setOwnerPassowrd(String ownerPassowrd) {
        this.ownerPassowrd = ownerPassowrd;
    }
}
