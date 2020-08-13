package com.lostdotcom.notifind.LoginSystem;

/*
This class is responsible for the login interface and allows users to login, access the forgot password screen, and access the sign up screen.
 */

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.lostdotcom.notifind.R;
import com.lostdotcom.notifind.Viewing.ReportViewingActivity;
import android.content.res.Resources;
import android.util.TypedValue;


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

        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,r.getDisplayMetrics());

        txtLoginEmail.setWidth(px);
        txtLoginPassword.setWidth(px);
        btnLogin.setWidth(px);


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

    public void signUpButtonClicked (View view){
        Intent i = new Intent (this, SignUpActivity.class); // Instance of intent class
        startActivity(i);

    }


    public void forgotPasswordClick(View view) {
        Intent i = new Intent (this, ReportViewingActivity.class); // Instance of intent class
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