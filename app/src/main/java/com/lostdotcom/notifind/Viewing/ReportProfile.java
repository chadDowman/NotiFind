package com.lostdotcom.notifind.Viewing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.lostdotcom.notifind.Activities.ReportCreationActivity;
import com.lostdotcom.notifind.LoginSystem.LoginActivity;
import com.lostdotcom.notifind.R;

public class ReportProfile extends AppCompatActivity {

    private TextView txtName;
    private TextView txtSurname;
    private TextView txtAge;
    private TextView txtEyeColor;
    private TextView txtWeight;
    private TextView txtHeight;
    private TextView txtLocation;
    private TextView txtDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_profile);

        String key = getIntent().getStringExtra("key");

        String name = getIntent().getStringExtra("name");
        String surname = getIntent().getStringExtra("surname");
        String age = getIntent().getStringExtra("age");
        String eyeColor = getIntent().getStringExtra("eyeColor");
        String weight = getIntent().getStringExtra("weight");
        String height = getIntent().getStringExtra("height");
        String location = getIntent().getStringExtra("lastSeenLocation");
        String description = getIntent().getStringExtra("description");

        txtName = findViewById(R.id.name);
        txtName.setText(name);

        txtSurname = findViewById(R.id.surname);
        txtSurname.setText(surname);

        txtAge = findViewById(R.id.age);
        txtAge.setText(age);

        txtEyeColor = findViewById(R.id.eyecolor);
        txtEyeColor.setText(eyeColor);

        txtWeight = findViewById(R.id.weight);
        txtWeight.setText(weight);

        txtHeight = findViewById(R.id.height);
        txtHeight.setText(height);

        txtLocation = findViewById(R.id.lastLocation);
        txtLocation.setText(location);

        txtDescription = findViewById(R.id.description);
        txtDescription.setText(description);

    }

    public void backBtnClicked (View view){
        startActivity(new Intent(getApplicationContext(), ReportViewingActivity.class));
    }

    // Getters and Setters

    public TextView getTxtName() {
        return txtName;
    }

    public void setTxtName(TextView txtName) {
        this.txtName = txtName;
    }

    public TextView getTxtSurname() {
        return txtSurname;
    }

    public void setTxtSurname(TextView txtSurname) {
        this.txtSurname = txtSurname;
    }

    public TextView getTxtAge() {
        return txtAge;
    }

    public void setTxtAge(TextView txtAge) {
        this.txtAge = txtAge;
    }

    public TextView getTxtEyeColor() {
        return txtEyeColor;
    }

    public void setTxtEyeColor(TextView txtEyeColor) {
        this.txtEyeColor = txtEyeColor;
    }

    public TextView getTxtWeight() {
        return txtWeight;
    }

    public void setTxtWeight(TextView txtWeight) {
        this.txtWeight = txtWeight;
    }

    public TextView getTxtHeight() {
        return txtHeight;
    }

    public void setTxtHeight(TextView txtHeight) {
        this.txtHeight = txtHeight;
    }

    public TextView getTxtLocation() {
        return txtLocation;
    }

    public void setTxtLocation(TextView txtLocation) {
        this.txtLocation = txtLocation;
    }

    public TextView getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(TextView txtDescription) {
        this.txtDescription = txtDescription;
    }
}