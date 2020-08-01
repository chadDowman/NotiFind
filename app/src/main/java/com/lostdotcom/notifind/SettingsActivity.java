package com.lostdotcom.notifind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SettingsActivity extends AppCompatActivity {

    Button btnPrevious;
    ImageButton btnAccountSettings;
    ImageButton btnNotificationSettings;
    ImageButton btnPrivacySettings;
    ImageButton btnAbout;
    ImageButton btnCloseAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnPrevious = (Button) findViewById(R.id.btnPrevious);
        btnAccountSettings = (ImageButton) findViewById(R.id.btnAccountSettings);
        btnNotificationSettings = (ImageButton) findViewById(R.id.btnNotificationSettings);
        btnPrivacySettings = (ImageButton) findViewById(R.id.btnPrivacySettings);
        btnAbout = (ImageButton) findViewById(R.id.btnAbout);
        btnCloseAccount = (ImageButton) findViewById(R.id.btnCloseAccount);

    }

    public void btnPreviousClicked (View view){
        Intent i = new Intent (this, ReportViewingActivity.class); // Instance of intent class
        startActivity(i);
    }

    public void btnAccountSettingsClicked (View view){
        Intent i = new Intent (this, AccountSettingsActivity.class); // Instance of intent class
        startActivity(i);
    }

    public void btnNotificationSettingsClicked (View view){
        Intent i = new Intent (this, NotificationSettingsActivity.class); // Instance of intent class
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

}