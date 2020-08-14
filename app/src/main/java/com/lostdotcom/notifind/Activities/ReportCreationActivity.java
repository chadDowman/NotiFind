package com.lostdotcom.notifind.Activities;

// This is the screen that will post the missing persons reports.

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lostdotcom.notifind.Details.ReportDetails;
import com.lostdotcom.notifind.LoginSystem.LoginActivity;
import com.lostdotcom.notifind.R;

import java.util.Random;

public class ReportCreationActivity extends AppCompatActivity {

    ReportDetails reportDetails;
    private Random ranNum;
    //---------------------------------------------------
    //Firebase
    private FirebaseDatabase myDatabase;
    private DatabaseReference myRef;

    //---------------------------------------------------
    private EditText txtName;
    private EditText txtSurname;
    private EditText txtAge;
    private EditText txtEyeColor;
    private EditText txtWeight;
    private EditText txtHeight;
    private EditText txtLastSeenLocation;
    private EditText txtDescription;
    private Button btnPostReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_creation);

        txtName = findViewById(R.id.name);
        txtSurname = findViewById(R.id.surname);
        txtAge = findViewById(R.id.age);
        txtEyeColor = findViewById(R.id.eyecolor);
        txtWeight = findViewById(R.id.weight);
        txtHeight = findViewById(R.id.height);
        txtLastSeenLocation = findViewById(R.id.lastLocation);
        txtDescription = findViewById(R.id.description);
        btnPostReport = findViewById(R.id.postReport);

        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,r.getDisplayMetrics());

        txtAge.setWidth(px);
        txtDescription.setWidth(px);
        txtEyeColor.setWidth(px);
        txtHeight.setWidth(px);
        txtLastSeenLocation.setWidth(px);
        txtSurname.setWidth(px);
        txtName.setWidth(px);
        txtWeight.setWidth(px);

        //----------------------------------------------------------------------------------------------------------
        //FireBase
        myDatabase = FirebaseDatabase.getInstance();
        myRef = myDatabase.getReference("ReportDetails");
    }

    public void postReportButtonClicked (View view){
        String name = txtName.getText().toString();
        String surname = txtSurname.getText().toString();
        String age = txtAge.getText().toString();
        String eyeColor = txtEyeColor.getText().toString();
        String weight = txtWeight.getText().toString();
        String height = txtHeight.getText().toString();
        String location = txtLastSeenLocation.getText().toString();
        String description = txtDescription.getText().toString();

        // Makes sure the user or admin does not put in any null values
        if (TextUtils.isEmpty(name)){
            txtName.setError("Missing Name Field");
            return;
        }

        if (TextUtils.isEmpty(surname)){
            txtSurname.setError("Missing Surname Field");
            return;
        }

        if (TextUtils.isEmpty(age)){
            txtAge.setError("Missing Age Field");
            return;
        }

        if (TextUtils.isEmpty(eyeColor)){
            txtEyeColor.setError("Missing Eye Color Field");
            return;
        }

        if (TextUtils.isEmpty(weight)){
            txtWeight.setError("Missing Weight Field");
            return;
        }

        if (TextUtils.isEmpty(height)){
            txtHeight.setError("Missing Height Field");
            return;
        }

        if (TextUtils.isEmpty(location)){
            txtLastSeenLocation.setError("Missing Location Field");
            return;
        }

        if (TextUtils.isEmpty(description)){
            txtDescription.setError("Missing Description Field");
            return;
        }

        reportDetails = new ReportDetails(name, surname, age, eyeColor, weight, height, location, description);
        ranNum = new Random();
        int num = ranNum.nextInt(10000) + 1;
        String id = String.valueOf(num);
        myRef.child(id).setValue(reportDetails);


        txtName.setText("");
        txtSurname.setText("");
        txtAge.setText("");
        txtEyeColor.setText("");
        txtWeight.setText("");
        txtHeight.setText("");
        txtLastSeenLocation.setText("");
        txtDescription.setText("");

        toaster("The report has been posted");
        //TODO PUSH NOTIFICATIONS
    }

    // Makes toast
    private void toaster (String bread){
        Toast.makeText(ReportCreationActivity.this, bread, Toast.LENGTH_SHORT).show();
    }

    //Getters and Setters

    public EditText getTxtName() {
        return txtName;
    }

    public void setTxtName(EditText txtName) {
        this.txtName = txtName;
    }

    public EditText getTxtSurname() {
        return txtSurname;
    }

    public void setTxtSurname(EditText txtSurname) {
        this.txtSurname = txtSurname;
    }

    public EditText getTxtAge() {
        return txtAge;
    }

    public void setTxtAge(EditText txtAge) {
        this.txtAge = txtAge;
    }

    public EditText getTxtEyeColor() {
        return txtEyeColor;
    }

    public void setTxtEyeColor(EditText txtEyeColor) {
        this.txtEyeColor = txtEyeColor;
    }

    public EditText getTxtWeight() {
        return txtWeight;
    }

    public void setTxtWeight(EditText txtWeight) {
        this.txtWeight = txtWeight;
    }

    public EditText getTxtHeight() {
        return txtHeight;
    }

    public void setTxtHeight(EditText txtHeight) {
        this.txtHeight = txtHeight;
    }

    public EditText getTxtLastSeenLocation() {
        return txtLastSeenLocation;
    }

    public void setTxtLastSeenLocation(EditText txtLastSeenLocation) {
        this.txtLastSeenLocation = txtLastSeenLocation;
    }

    public EditText getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(EditText txtDescription) {
        this.txtDescription = txtDescription;
    }

    public void signOut (View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}