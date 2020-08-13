package com.lostdotcom.notifind.Activities;



import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.lostdotcom.notifind.R;

public class AdminCreationActivity extends AppCompatActivity {

    private String adminEmail;
    private String adminPassword;
    private String adminLocation;
    private String adminPhoneNo;
    private Button adminPostBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_creation);

        /*
        adminEmail = findViewById(R.id.adminEmail);
        adminPassword = findViewById(R.id.adminPassword);
        adminLocation = findViewById(R.id.adminPassword);
        adminPhoneNo = findViewById(R.id.adminPhoneNo);
        adminPostBtn = findViewById(R.id.adminPostBtn);
         */

    }

    //Getters and Setters

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

    public String getAdminLocation() {
        return adminLocation;
    }

    public void setAdminLocation(String adminLocation) {
        this.adminLocation = adminLocation;
    }

    public String getAdminPhoneNo() {
        return adminPhoneNo;
    }

    public void setAdminPhoneNo(String adminPhoneNo) {
        this.adminPhoneNo = adminPhoneNo;
    }
}