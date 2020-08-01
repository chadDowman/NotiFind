package com.lostdotcom.notifind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    Button btnPrevious;
    Button btnAccountSettings;
    Button btnNotificationSettings;
    Button btnPrivacySettings;
    Button btnAbout;
    Button btnCloseAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

    //    btnPrevious = (Button) findViewById(R.id.btnPrevious);
    //    btnAccountSettings = (Button) findViewById(R.id.btnAccountSettings);
    //    btnNotificationSettings = (Button) findViewById(R.id.btnNotificationSettings);
    //    btnPrivacySettings = (Button) findViewById(R.id.btnPrivacySettings);
    //    btnAbout = (Button) findViewById(R.id.btnAbout);
    //    btnCloseAccount = (Button) findViewById(R.id.btnCloseAccount);
    }

    public void btnPreviousClicked (View view){

    }

    public void btnAccountSettingsClicked (View view){

    }

    public void btnNotificationSettingsClicked (View view){

    }

    public void btnPrivacySettingsClicked (View view){

    }

    public void btnAboutClicked (View view){

    }

}