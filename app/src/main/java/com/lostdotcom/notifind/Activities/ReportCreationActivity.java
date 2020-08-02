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

        txtName = (EditText) findViewById(R.id.name);
        txtSurname = (EditText) findViewById(R.id.surname);
        txtAge = (EditText) findViewById(R.id.age);
        txtEyeColor = (EditText) findViewById(R.id.eyecolor);
        txtWeight = (EditText) findViewById(R.id.weight);
        txtHeight = (EditText) findViewById(R.id.height);
        txtLastSeenLocation = (EditText) findViewById(R.id.lastLocation);
        txtDescription = (EditText) findViewById(R.id.description);
    }

    public void postReportButtonClicked (View view){

    }


}