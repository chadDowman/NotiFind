package com.lostdotcom.notifind.Activities;

/*
This class controls the settings screen and is how users access the account settings, notification settings, privacy settings and about settings screens
respectively. It is also where users are able to request closure of their accounts.
 */

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,r.getDisplayMetrics());

        btnPrevious.setWidth(px);
        btnAbout.setWidth(px);
        btnAccountSettings.setWidth(px);
        btnCloseAccount.setWidth(px);
        btnPrivacySettings.setWidth(px);

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

    public void closeAccountClicked (View view){
        //Todo Delete account
        toaster("Your Account Will be Deleted Within 24 Hours");
    }

    private void toaster (String bread){
        Toast.makeText(SettingsActivity.this, bread, Toast.LENGTH_LONG).show();
    }

}