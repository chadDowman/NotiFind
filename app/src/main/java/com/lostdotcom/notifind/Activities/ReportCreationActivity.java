package com.lostdotcom.notifind.Activities;

// This is the screen that will post the missing persons reports.

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.lostdotcom.notifind.R;

public class ReportCreationActivity extends AppCompatActivity {

    private EditText txtName;
    private EditText txtSurname;
    private EditText txtAge;
    private EditText txtEyeColor;
    private EditText txtWeight;
    private EditText txtHeight;
    private EditText txtLastSeenLocation;
    private EditText txtDescription;

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
    }

    public void postReportButtonClicked (View view){

    }

    //TODO onClick for logout Button


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
}