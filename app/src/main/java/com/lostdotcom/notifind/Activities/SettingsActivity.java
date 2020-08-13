package com.lostdotcom.notifind.Activities;

/*
This class controls the settings screen and is how users access the account settings, notification settings, privacy settings and about settings screens
respectively. It is also where users are able to request closure of their accounts.
 */

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.lostdotcom.notifind.R;
import com.lostdotcom.notifind.Viewing.ReportViewingActivity;

public class SettingsActivity extends AppCompatActivity {

    private Button btnPrevious;
    private Button btnAccountSettings;
    private Button btnNotificationSettings;
    private Button btnPrivacySettings;
    private Button btnAbout;
    private Button btnCloseAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnPrevious =  findViewById(R.id.btnPrevious);
        btnAccountSettings = findViewById(R.id.btnAccountSettings);
        btnPrivacySettings = findViewById(R.id.btnPrivacySettings);
        btnAbout = findViewById(R.id.btnAbout);
        btnCloseAccount = findViewById(R.id.btnCloseAccount);

    }

    public void btnPreviousClicked (View view){
        Intent i = new Intent (this, ReportViewingActivity.class); // Instance of intent class
        startActivity(i);
    }

    public void btnAccountSettingsClicked (View view){
        Intent i = new Intent (this, AccountSettingsActivity.class); // Instance of intent class
        startActivity(i);
    }

    public void btnPrivacySettingsClicked (View view){
        Intent i = new Intent (this, PrivacySettingsActivity.class); // Instance of intent class
        startActivity(i);
    }

    public void btnAboutClicked (View view){
        Intent i = new Intent (this, AboutActivity.class); // Instance of intent class
        startActivity(i);
    }

    //Getters and Setters

    public Button getBtnPrevious() {
        return btnPrevious;
    }

    public void setBtnPrevious(Button btnPrevious) {
        this.btnPrevious = btnPrevious;
    }

    public Button getBtnAccountSettings() {
        return btnAccountSettings;
    }

    public void setBtnAccountSettings(Button btnAccountSettings) {
        this.btnAccountSettings = btnAccountSettings;
    }

    public Button getBtnNotificationSettings() {
        return btnNotificationSettings;
    }

    public void setBtnNotificationSettings(Button btnNotificationSettings) {
        this.btnNotificationSettings = btnNotificationSettings;
    }

    public Button getBtnPrivacySettings() {
        return btnPrivacySettings;
    }

    public void setBtnPrivacySettings(Button btnPrivacySettings) {
        this.btnPrivacySettings = btnPrivacySettings;
    }

    public Button getBtnAbout() {
        return btnAbout;
    }

    public void setBtnAbout(Button btnAbout) {
        this.btnAbout = btnAbout;
    }

    public Button getBtnCloseAccount() {
        return btnCloseAccount;
    }

    public void setBtnCloseAccount(Button btnCloseAccount) {
        this.btnCloseAccount = btnCloseAccount;
    }
}