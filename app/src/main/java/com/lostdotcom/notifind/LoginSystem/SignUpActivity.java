package com.lostdotcom.notifind.LoginSystem;

/*
This screen is responsible for signing users up to the NotiFind application.
 */

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Random;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lostdotcom.notifind.Details.UserDetails;
import com.lostdotcom.notifind.R;
import com.lostdotcom.notifind.Viewing.ReportViewingActivity;

public class SignUpActivity extends AppCompatActivity {


    private Random ranNum;

    // Firebase
    private FirebaseAuth myAuth;
    private FirebaseDatabase myDatabase;
    private DatabaseReference myRef;
    private UserDetails userDetails;

    //------------------------------------
    private EditText txtSignUpEmail;
    private EditText txtSignUpPassword;
    private EditText txtSignUpPasswordConfirm;
    private EditText txtSignUpLocation;
    private EditText txtSignUpName;
    private EditText txtSignUpSurname;
    private Button btnSignUpButton;

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
        btnSignUpButton = findViewById(R.id.signUpButton2);

        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,r.getDisplayMetrics());

        txtSignUpEmail.setWidth(px);
        txtSignUpPassword.setWidth(px);
        txtSignUpLocation.setWidth(px);
        txtSignUpName.setWidth(px);
        txtSignUpPasswordConfirm.setWidth(px);
        txtSignUpSurname.setWidth(px);

        //---------------------------------
        //Firebase
        myAuth = FirebaseAuth.getInstance(); // Gets current database instance so we can perform changes
        myDatabase = FirebaseDatabase.getInstance();
        myRef = myDatabase.getReference("UserDetails");

    }

    // Makes Toast
    private void toaster (String bread){
        Toast.makeText(SignUpActivity.this, bread, Toast.LENGTH_SHORT).show();
    }

    public void signUpButton2Clicked (View view){

        /*
                // Checks if the user is already logged in or not
        if (myAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), ReportViewingActivity.class));
            finish();
        }
         */


        String email = txtSignUpEmail.getText().toString();
        String password = txtSignUpPassword.getText().toString();
        String passwordConfirm = txtSignUpPasswordConfirm.getText().toString();
        String name = txtSignUpName.getText().toString();
        String surname = txtSignUpSurname.getText().toString();
        String location = txtSignUpLocation.getText().toString();

        // Checks if its null or not
        if (TextUtils.isEmpty(email)){
            txtSignUpEmail.setError("Email is Required");
            return;
        }

        if (TextUtils.isEmpty(password)){
            txtSignUpPassword.setError("Password is Required");
            return;
        }

        if (TextUtils.isEmpty(passwordConfirm)){
            txtSignUpPassword.setError("Password Confirmation is Required");
            return;
        }

        if (!passwordConfirm.equals(password)){
            txtSignUpPasswordConfirm.setError("Password Mismatch");
            return;
        }

        if (password.length() < 6){
            txtSignUpPassword.setError("Password must 6 or more characters");
        }

        // Firebase Registration

        myAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    toaster("The User has been Created");
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }else{
                    toaster("Error! " + task.getException().getMessage());
                }
            }
        });

        userDetails = new UserDetails(email, password, location, name, surname);
        ranNum = new Random();
        int num = ranNum.nextInt(10000) + 1;
        String id = String.valueOf(num);
        myRef.child(id).setValue(userDetails);

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