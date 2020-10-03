package com.lostdotcom.notifind.LoginSystem;

/*
This class is responsible for the password recovery of users
 */

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.lostdotcom.notifind.R;

public class ForgotPassword extends AppCompatActivity {

    Button forgotPasswordButton;
    FirebaseAuth myAuth;
    EditText userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgotPasswordButton = findViewById(R.id.forgotPasswordButton);
        myAuth = FirebaseAuth.getInstance();
        userEmail = findViewById(R.id.forgotPasswordEmail);

    }

    public void forgotPasswordReturnButtonClicked(View view) {
        Intent i = new Intent(this, LoginActivity.class); // Instance of intent class
        startActivity(i);
    }

    public void submitEmailClicked(View view) {

        myAuth.sendPasswordResetEmail(userEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    toaster("Recovery Email Sent Please Check Your Email");
                }else{
                    toaster(task.getException().getMessage());
                }
            }
        });


    }

    private void toaster(String bread) {
        Toast.makeText(ForgotPassword.this, bread, Toast.LENGTH_LONG).show();
    }
}