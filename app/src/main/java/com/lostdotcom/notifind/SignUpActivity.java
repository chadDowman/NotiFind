package com.lostdotcom.notifind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    EditText txtSignUpEmail;
    EditText txtSignUpPassword;
    EditText txtSignUpPasswordConfirm;
    EditText txtSignUpLocation;
    EditText txtSignUpName;
    EditText txtSignUpSurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txtSignUpEmail = (EditText) findViewById(R.id.signUpEmail);
        txtSignUpPassword = (EditText) findViewById(R.id.signUpPassword);
        txtSignUpPasswordConfirm = (EditText) findViewById(R.id.signUpPassword2);
        txtSignUpLocation = (EditText) findViewById(R.id.address);
        txtSignUpName = (EditText) findViewById(R.id.signUpName);
        txtSignUpSurname = (EditText) findViewById(R.id.signUpSurname);
    }

    public void signUpButton2Clicked (View view){
        Intent i = new Intent (this, LoginActivity.class); // Instance of intent class
        startActivity(i);

        UserDetails user = new UserDetails(txtSignUpEmail.getText().toString(), txtSignUpPassword.getText().toString(), txtSignUpLocation.getText().toString(), txtSignUpName.getText().toString(), txtSignUpSurname.getText().toString());
    }

    public void returnLoginButtonClicked (View view){
        Intent i = new Intent (this, LoginActivity.class); // Instance of intent class
        startActivity(i);
    }
}