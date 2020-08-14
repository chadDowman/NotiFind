package com.lostdotcom.notifind.Details;

// The purpose of this class is to initialise the activity_custom_rows of the user when logging in and when the user account is created. We will use the getters and setters in this
// class once the attributes are initialised

public class UserDetails {

    private String userEmail;
    private String password;
    private String location;
    private String userName;
    private String userSurname;

    public UserDetails() {

    }

    public UserDetails(String userEmail, String password, String location, String userName, String userSurname) {
        this.userEmail = userEmail;
        this.password = password;
        this.location = location;
        this.userName = userName;
        this.userSurname = userSurname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }
}
