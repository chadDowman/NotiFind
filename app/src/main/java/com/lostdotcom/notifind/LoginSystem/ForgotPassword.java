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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.lostdotcom.notifind.R;

public class ForgotPassword extends AppCompatActivity {

    Button forgotPasswordButton;
    EditText forgotPasswordEmail;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgotPasswordButton = findViewById(R.id.forgotPasswordButton);
        fAuth = FirebaseAuth.getInstance();
    }

    public void forgotPasswordReturnButtonClicked (View view){
        Intent i = new Intent (this, LoginActivity.class); // Instance of intent class
        startActivity(i);
    }

    public void submitEmailClicked (View view){
        //TODO This class must send the email to user


        final EditText forgotPasswordEmail = new EditText(view.getContext());
        AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setTitle("Reset your password ?");
                passwordResetDialog.setMessage("Enter your email to recieve the password link");
                passwordResetDialog.setView(forgotPasswordEmail);
                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //to extract the email and send reset link

                        String mail = forgotPasswordEmail.getText().toString();
                        //passing the email here
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(ForgotPassword.this, "Reset link sent to you email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Message to the user
                                Toast.makeText(ForgotPassword.this, "Error! Reset link is not sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                //negative button for no
                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // to close the dialog

                    }


                });


    }
}