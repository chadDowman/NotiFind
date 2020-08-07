package com.lostdotcom.notifind.LoginSystem;

/*
This screen is responsible for signing users up to the NotiFind application.
 */

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.lostdotcom.notifind.Details.UserDetails;
import com.lostdotcom.notifind.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText txtSignUpEmail;
    private EditText txtSignUpPassword;
    private EditText txtSignUpPasswordConfirm;
    private EditText txtSignUpLocation;
    private EditText txtSignUpName;
    private EditText txtSignUpSurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txtSignUpEmail = findViewById(R.id.signUpEmail);
        txtSignUpPassword = findViewById(R.id.signUpPassword);
        txtSignUpPasswordConfirm = findViewById(R.id.signUpPassword2);
        txtSignUpLocation = findViewById(R.id.address);
        txtSignUpName = findViewById(R.id.signUpName);
        txtSignUpSurname = findViewById(R.id.signUpSurname);
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

    // Getters and Setters

    public EditText getTxtSignUpEmail() {
        return txtSignUpEmail;
    }

    public void setTxtSignUpEmail(EditText txtSignUpEmail) {
        this.txtSignUpEmail = txtSignUpEmail;
    }

    public EditText getTxtSignUpPassword() {
        return txtSignUpPassword;
    }

    public void setTxtSignUpPassword(EditText txtSignUpPassword) {
        this.txtSignUpPassword = txtSignUpPassword;
    }

    public EditText getTxtSignUpPasswordConfirm() {
        return txtSignUpPasswordConfirm;
    }

    public void setTxtSignUpPasswordConfirm(EditText txtSignUpPasswordConfirm) {
        this.txtSignUpPasswordConfirm = txtSignUpPasswordConfirm;
    }

    public EditText getTxtSignUpLocation() {
        return txtSignUpLocation;
    }

    public void setTxtSignUpLocation(EditText txtSignUpLocation) {
        this.txtSignUpLocation = txtSignUpLocation;
    }

    public EditText getTxtSignUpName() {
        return txtSignUpName;
    }

    public void setTxtSignUpName(EditText txtSignUpName) {
        this.txtSignUpName = txtSignUpName;
    }

    public EditText getTxtSignUpSurname() {
        return txtSignUpSurname;
    }

    public void setTxtSignUpSurname(EditText txtSignUpSurname) {
        this.txtSignUpSurname = txtSignUpSurname;
    }
}