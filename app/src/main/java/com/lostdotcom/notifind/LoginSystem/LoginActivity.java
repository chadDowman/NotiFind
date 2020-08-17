package com.lostdotcom.notifind.LoginSystem;

/*
This class is responsible for the login interface and allows users to login, access the forgot password screen, and access the sign up screen.
 */

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.lostdotcom.notifind.Activities.AdminCreationActivity;
import com.lostdotcom.notifind.Activities.ReportCreationActivity;
import com.lostdotcom.notifind.R;
import com.lostdotcom.notifind.Viewing.ReportViewingActivity;
import android.content.res.Resources;
import android.util.TypedValue;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth myAuth;
    private static final String TAG = "LoginActivity";
    //----------------------------------------
    private EditText txtLoginEmail;
    private EditText txtLoginPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtLoginEmail = findViewById(R.id.email);
        txtLoginPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);

        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,r.getDisplayMetrics());

        txtLoginEmail.setWidth(px);
        txtLoginPassword.setWidth(px);
        btnLogin.setWidth(px);

        myAuth = FirebaseAuth.getInstance(); // Gets current database instance or state

    }

    public void LoginButtonClicked(View view){
        String email = txtLoginEmail.getText().toString();
        String password = txtLoginPassword.getText().toString();
        // Checks if email is null or not
        if (TextUtils.isEmpty(email)){
            txtLoginEmail.setError("Email is Required");
            return;
        }
        // Checks if password is null or not
        if (TextUtils.isEmpty(password)){
            txtLoginPassword.setError("Password is Required");
            return;
        }
        //Makes sure the password length is above 6 characters
        if (password.length() < 6){
            txtLoginPassword.setError("Password must 6 or more characters");
        }

        // User Authentication                              // Event Handler
        myAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // If the task is successful the user will be able to login
                if (task.isSuccessful()){
                    toaster("Login Successful");
                    startActivity(new Intent(getApplicationContext(), ReportCreationActivity.class));
                    // If not the following error message will be displayed as a toast
                }else{
                    toaster("Error! " + task.getException().getMessage()); // Displays the error message
                }
            }
        });

    }

    public void signUpButtonClicked (View view){
        Intent i = new Intent (this, SignUpActivity.class); // Instance of intent class
        startActivity(i);
    }


    public void forgotPasswordClick(View view) {
        Intent i = new Intent (this, ForgotPassword.class); // Instance of intent class
        startActivity(i);
    }

    // Makes toast
    private void toaster (String bread){
        Toast.makeText(LoginActivity.this, bread, Toast.LENGTH_SHORT).show();
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

}