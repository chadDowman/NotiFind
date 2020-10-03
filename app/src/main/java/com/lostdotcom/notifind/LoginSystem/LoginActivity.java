package com.lostdotcom.notifind.LoginSystem;

/*
This class is responsible for the login interface and allows users to login, access the forgot password screen, and access the sign up screen.
 */

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lostdotcom.notifind.Activities.AdminCreationActivity;
import com.lostdotcom.notifind.Activities.ReportCreationActivity;
import com.lostdotcom.notifind.Activities.ReportEditsActivity;
import com.lostdotcom.notifind.Databases.RecyclerViewConfig;
import com.lostdotcom.notifind.Details.AdminDetails;
import com.lostdotcom.notifind.R;
import com.lostdotcom.notifind.Viewing.ReportViewingActivity;
import android.content.res.Resources;
import android.util.TypedValue;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class LoginActivity extends AppCompatActivity {

    protected static final String ownerEmail = "owner@gmail.com";
    protected static final String ownerPassword = "password69";
    //
    private FirebaseAuth myAuth;
    private static final String TAG = "LoginActivity";
    //----------------------------------------
    private EditText txtLoginEmail;
    private EditText txtLoginPassword;
    private Button btnLogin;
    private DatabaseReference myRef;
    private List<AdminDetails> adminCreations = new ArrayList<>();
    boolean owner = false;
    private boolean testBoolean = false;
    private String emailTest;
    private String passwordTest;
    private String email;
    private String password;
    private ProgressBar pBar;
    private TextView pText;
    private int pStat;
    private Handler handle = new Handler();
    private boolean sendTest = false;

    private static boolean adminOrNot = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtLoginEmail = findViewById(R.id.email);
        txtLoginPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);

        pBar = (ProgressBar) findViewById(R.id.loading);
        pText = (TextView) findViewById(R.id.loadingProgress);

        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,r.getDisplayMetrics());

        txtLoginEmail.setWidth(px);
        txtLoginPassword.setWidth(px);
        btnLogin.setWidth(px);

        myAuth = FirebaseAuth.getInstance(); // Gets current database instance or state
        //----------------------------------------
        FirebaseDatabase myDatabase = FirebaseDatabase.getInstance();
        myRef = myDatabase.getReference("AdminDetails");

    }

    public void LoginButtonClicked(View view){

          email = txtLoginEmail.getText().toString();
          password = txtLoginPassword.getText().toString();
          pBar.setVisibility(View.VISIBLE);
          pText.setVisibility(View.VISIBLE);
          pText.setText("Loading...");

        if (email.equalsIgnoreCase(ownerEmail) && password.equals(ownerPassword)){
            owner = true;
        }


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

                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                                AdminDetails admin = snapshot.getValue(AdminDetails.class);
                                assert admin != null;
                                testBoolean = admin.isAdmin();
                                emailTest = admin.getAdminEmail();
                                passwordTest = admin.getAdminPassword();
                                if (email.equals(emailTest) && password.equals(passwordTest) && testBoolean){
                                    toaster("Welcome Admin");
                                    adminOrNot = true;
                                    startActivity(new Intent(getApplicationContext(), ReportCreationActivity.class));
                                    break;
                                } else if (owner){
                                    toaster("Welcome Owner");
                                    startActivity(new Intent(getApplicationContext(), AdminCreationActivity.class));
                                    break;
                                }
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    if(!email.equals(emailTest) && !password.equals(passwordTest) && !owner){
                        startActivity(new Intent(getApplicationContext(), ReportViewingActivity.class));

                    }



                    // If not the following error message will be displayed as a toast
                }else{
                    toaster("Error! " + Objects.requireNonNull(task.getException()).getMessage()); // Displays the error message
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

    public static boolean isAdminOrNot() {
        return adminOrNot;
    }

    public static void setAdminOrNot(boolean adminOrNot) {
        LoginActivity.adminOrNot = adminOrNot;
    }
}