package com.lostdotcom.notifind.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.lostdotcom.notifind.R;

public class ReportCreationActivity extends AppCompatActivity {

    EditText txtName;
    EditText txtSurname;
    EditText txtAge;
    EditText txtEyeColor;
    EditText txtWeight;
    EditText txtHeight;
    EditText txtLastSeenLocation;
    EditText txtDescription;

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

}