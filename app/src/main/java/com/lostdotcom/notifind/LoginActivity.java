package com.lostdotcom.notifind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText txtLoginEmail;
    EditText txtLoginPassword;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtLoginEmail = (EditText) findViewById(R.id.email);
        txtLoginPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.login);

    }

    public void loginButtonClicked (View view){
        String email = txtLoginEmail.getText().toString();
        String password = txtLoginPassword.getText().toString();


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