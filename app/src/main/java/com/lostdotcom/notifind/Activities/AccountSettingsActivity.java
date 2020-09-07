package com.lostdotcom.notifind.Activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lostdotcom.notifind.R;


public class AccountSettingsActivity extends AppCompatActivity {

    private FirebaseDatabase myDatabase;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

    }

    public void changeEmailButtonClicked (View view){
        //TODO
    }

    public void changePasswordButtonClicked (View view){
        //TODO
    }
    public void changeLocationButtonClicked (View view){
        //TODO
    }

    public void buttonBack (View view){
        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
    }

    private void toaster (String bread){
        Toast.makeText(AccountSettingsActivity.this, bread, Toast.LENGTH_SHORT).show();
    }
}