package com.lostdotcom.notifind.Viewing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
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

    private String key;
    private String name;
    private String surname;
    private String age;
    private String eyeColor;
    private String weight;
    private String height;
    private String location;
    private String description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_profile);

        key = getIntent().getStringExtra("key");

        name = getIntent().getStringExtra("name");
        surname = getIntent().getStringExtra("surname");
        age = getIntent().getStringExtra("age");
        eyeColor = getIntent().getStringExtra("eyeColor");
        weight = getIntent().getStringExtra("weight");
        height = getIntent().getStringExtra("height");
        location = getIntent().getStringExtra("lastSeenLocation");
        description = getIntent().getStringExtra("description");

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

    public void signOut (View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}