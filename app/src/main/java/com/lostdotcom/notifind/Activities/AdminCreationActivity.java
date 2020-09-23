package com.lostdotcom.notifind.Activities;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.lostdotcom.notifind.Databases.DatabaseHelperAdmins;
import com.lostdotcom.notifind.Details.AdminDetails;
import com.lostdotcom.notifind.LoginSystem.LoginActivity;
import com.lostdotcom.notifind.R;
import com.lostdotcom.notifind.Viewing.AdminViewingActivity;



import java.util.List;


public class AdminCreationActivity extends AppCompatActivity {

    //---------------------------------------------------
    //Firebase
    private FirebaseAuth myAuth;
    private FirebaseDatabase myDatabase;
    private DatabaseReference myRef;
    private AdminDetails admin;
    //---------------------------------------------------

    private EditText adminEmail;
    private EditText adminPassword;
    private EditText adminPhoneNo;
    private Switch adminSwitch;

    private String key;

    private Spinner mySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_creation);

        adminEmail = findViewById(R.id.adminEmail);
        adminPassword = findViewById(R.id.adminPassword);
        adminPhoneNo = findViewById(R.id.adminPhoneNo);
        adminSwitch = findViewById(R.id.switch2);

        mySpinner = findViewById(R.id.admin_spinner);
        ArrayAdapter<String> myAdapter =  new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.locations));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        myAuth = FirebaseAuth.getInstance();
        myDatabase = FirebaseDatabase.getInstance();
        myRef = myDatabase.getReference("AdminDetails");

        key = getIntent().getStringExtra("key");
        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");
        String location = getIntent().getStringExtra("location");
        String phoneNo = getIntent().getStringExtra("phoneNo");

    }

    public void AdminSignUpButtonClicked (View view){

        admin = new AdminDetails();

        boolean adminPriv;
        String email = adminEmail.getText().toString();
        String password = adminPassword.getText().toString();
        String location = mySpinner.getSelectedItem().toString();
        String phoneNo = adminPhoneNo.getText().toString();

        if (adminSwitch.isChecked()){
            adminPriv = true;
        }else{
            adminPriv = false;
        }

        admin.setAdminEmail(email);
        admin.setAdminPassword(password);
        admin.setAdminLocation(location);
        admin.setAdminPhoneNumber(phoneNo);
        admin.setAdmin(adminPriv);

        if (TextUtils.isEmpty(email)){
            adminEmail.setError("Missing Email");
            return;
        }

        if (TextUtils.isEmpty(password)){
            adminPassword.setError("Missing Password");
            return;
        }

        if (TextUtils.isEmpty(phoneNo)){
            adminPhoneNo.setError(("Missing Phone Number"));
            return;
        }

        myAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                new DatabaseHelperAdmins().addAdmin(admin, new DatabaseHelperAdmins.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<AdminDetails> adminCreations, List<String> keys) {

                    }

                    @Override
                    public void DataInserted() {
                        toaster("The admin has been created");
                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

    }

    public void deleteAdminClicked (View view){
        new DatabaseHelperAdmins().deleteAdmin(key, new DatabaseHelperAdmins.DataStatus() {
            @Override
            public void DataIsLoaded(List<AdminDetails> adminCreations, List<String> keys) {

            }

            @Override
            public void DataInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {
                toaster("The report has been deleted successfully");
                finish();

            }
        });
    }

    // Makes toast
    private void toaster (String bread){
        Toast.makeText(AdminCreationActivity.this, bread, Toast.LENGTH_SHORT).show();
    }

    public void searchAdminBtnClicked (View view){
        startActivity(new Intent(getApplicationContext(), AdminViewingActivity.class));
    }

    public void signOut (View view){
        LoginActivity.setAdminOrNot(false);
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}