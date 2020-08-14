package com.lostdotcom.notifind.Activities;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lostdotcom.notifind.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void ReturnButtonClicked (View view){
        Intent i = new Intent (this, SettingsActivity.class); // Instance of intent class
        startActivity(i);
    }
}