package com.lostdotcom.notifind.LoginSystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lostdotcom.notifind.LoginSystem.LoginActivity;
import com.lostdotcom.notifind.R;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }

    public void forgotPasswordReturnButtonClicked (View view){
        Intent i = new Intent (this, LoginActivity.class); // Instance of intent class
        startActivity(i);
    }

    public void submitEmailClicked (View view){
        //TODO This class must send the email to user
    }
}