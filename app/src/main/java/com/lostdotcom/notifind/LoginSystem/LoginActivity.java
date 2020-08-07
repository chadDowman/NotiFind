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

    private EditText txtLoginEmail;
    private EditText txtLoginPassword;
    private Button btnLogin;
    private MenuItem settingsMenu;

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
        String email = getTxtLoginEmail().getText().toString();
        String password = getTxtLoginPassword().getText().toString();

        if (!email.isEmpty() && !password.isEmpty()){
            //TODO FireBase Connection Here
            /*
            if  (email.equals(insert database email here) && password.equals(insert database password here)){
                Intent i = new Intent (this, ReportViewingActivity.class); // Instance of intent class
                startActivity(i);
            }else if (email.equals(insert admin database email here) && password.equals(insert admin database password here)){
                Intent i = new Intent (this, ReportCreationActivity.class); // Instance of intent class
                startActivity(i);
            }else if ((email.equals(insert owner database email here) && password.equals(insert owner database password here))){
                Intent i = new Intent (this, .class); // Instance of intent class
                startActivity(i);
            }else{
                Toast Incorrect Password Or Username
            }
             */
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

    // Getters and Setters

    public EditText getTxtLoginEmail() {
        return txtLoginEmail;
    }

    public void setTxtLoginEmail(EditText txtLoginEmail) {
        this.txtLoginEmail = txtLoginEmail;
    }

    public EditText getTxtLoginPassword() {
        return txtLoginPassword;
    }

    public void setTxtLoginPassword(EditText txtLoginPassword) {
        this.txtLoginPassword = txtLoginPassword;
    }

    public Button getBtnLogin() {
        return btnLogin;
    }

    public void setBtnLogin(Button btnLogin) {
        this.btnLogin = btnLogin;
    }

    public MenuItem getSettingsMenu() {
        return settingsMenu;
    }

    public void setSettingsMenu(MenuItem settingsMenu) {
        this.settingsMenu = settingsMenu;
    }
}