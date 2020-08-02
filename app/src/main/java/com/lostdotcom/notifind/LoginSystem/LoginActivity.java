package com.lostdotcom.notifind.LoginSystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lostdotcom.notifind.Activities.SettingsActivity;
import com.lostdotcom.notifind.R;

public class LoginActivity extends AppCompatActivity {

    EditText txtLoginEmail;
    EditText txtLoginPassword;
    Button btnLogin;
    MenuItem settingsMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtLoginEmail = findViewById(R.id.email);
        txtLoginPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login);
        settingsMenu = findViewById(R.id.settings_menu);

    }

    public void loginButtonClicked (View view){
        String email = txtLoginEmail.getText().toString();
        String password = txtLoginPassword.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()){
            //TODO FireBase Connection Here

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.settings_menu:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                Intent i = new Intent (this, SettingsActivity.class); // Instance of intent class
                startActivity(i);
                return true;
            case R.id.light_theme:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);// Just default functionality that makes sure everything doest break
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

    public void settingsOverFlowClicked (View view){

    }
}