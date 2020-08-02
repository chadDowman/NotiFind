package com.lostdotcom.notifind.LoginSystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lostdotcom.notifind.R;

public class LoginActivity extends AppCompatActivity {

    EditText txtLoginEmail;
    EditText txtLoginPassword;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtLoginEmail = findViewById(R.id.email);
        txtLoginPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login);

    }

    public void loginButtonClicked (View view){
        String email = txtLoginEmail.getText().toString();
        String password = txtLoginPassword.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()){
            //TODO FireBase Connection Here

        }

    }

    public void signUpButtonClicked (View view){
        Intent i = new Intent (this, SignUpActivity.class); // Instance of intent class
        startActivity(i);

    }


    public void forgotPasswordClick(View view) {
        Intent i = new Intent (this, ForgotPassword.class); // Instance of intent class
        startActivity(i);

    }
}