package com.lostdotcom.notifind.Activities;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.lostdotcom.notifind.Details.AdminDetails;
import com.lostdotcom.notifind.LoginSystem.LoginActivity;
import com.lostdotcom.notifind.R;


import java.util.Random;

public class AdminCreationActivity extends AppCompatActivity {

    AdminDetails adminDetails;
    private Random ranNum;
    //---------------------------------------------------
    //Firebase

    private FirebaseDatabase myDatabase;
    private DatabaseReference myRef;

    //---------------------------------------------------

    private EditText adminEmail;
    private EditText adminPassword;
    private EditText adminLocation;
    private EditText adminPhoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_creation);

        adminEmail = findViewById(R.id.adminEmail);
        adminPassword = findViewById(R.id.adminPassword);
        adminLocation = findViewById(R.id.adminLocation);
        adminPhoneNo = findViewById(R.id.adminPhoneNo);

        myDatabase = FirebaseDatabase.getInstance();
        myRef = myDatabase.getReference("AdminDetails");

    }

    public void AdminSignUpButtonClicked (View view){

        String email = adminEmail.getText().toString();
        String password = adminPassword.getText().toString();
        String location = adminLocation.getText().toString();
        String phoneNo = adminPhoneNo.getText().toString();

        if (TextUtils.isEmpty(email)){
            adminEmail.setError("Missing Email");
            return;
        }

        if (TextUtils.isEmpty(password)){
            adminPassword.setError("Missing Password");
            return;
        }

        if (TextUtils.isEmpty(location)){
            adminLocation.setError("Missing Location Info");
            return;
        }

        if (TextUtils.isEmpty(phoneNo)){
            adminPhoneNo.setError(("Missing Phone Number"));
            return;
        }

        adminDetails = new AdminDetails(email, password, location, phoneNo);
        myRef.child(phoneNo).setValue(adminDetails);

        adminEmail.setText("");
        adminPassword.setText("");
        adminLocation.setText("");
        adminPhoneNo.setText("");

        toaster("The admin has been created");
    }

    // Makes toast
    private void toaster (String bread){
        Toast.makeText(AdminCreationActivity.this, bread, Toast.LENGTH_SHORT).show();
    }

    public void signOut (View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}