package com.lostdotcom.notifind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class ReportCreation extends AppCompatActivity {

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

        txtName = (EditText) findViewById(R.id.name);
        txtSurname = (EditText) findViewById(R.id.surname);
        txtAge = (EditText) findViewById(R.id.age);
        txtEyeColor = (EditText) findViewById(R.id.eyecolor);
        txtWeight = (EditText) findViewById(R.id.weight);
        txtHeight = (EditText) findViewById(R.id.height);
        txtLastSeenLocation = (EditText) findViewById(R.id.lastLocation);
        txtDescription = (EditText) findViewById(R.id.description);
    }


}