package com.lostdotcom.notifind.Viewing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lostdotcom.notifind.Activities.ReportCreationActivity;
import com.lostdotcom.notifind.LoginSystem.LoginActivity;
import com.lostdotcom.notifind.R;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ReportProfile extends AppCompatActivity {

    private TextView txtName;
    private TextView txtSurname;
    private TextView txtAge;
    private TextView txtEyeColor;
    private TextView txtWeight;
    private TextView txtHeight;
    private TextView txtLocation;
    private TextView txtDescription;
    private ImageView pic;


    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_profile);

        txtName = findViewById(R.id.name);
        txtSurname = findViewById(R.id.surname);
        txtAge = findViewById(R.id.age);
        txtEyeColor = findViewById(R.id.eyecolor);
        txtWeight = findViewById(R.id.weight);
        txtHeight = findViewById(R.id.height);
        txtLocation = findViewById(R.id.lastLocation);
        txtDescription = findViewById(R.id.description);
        pic = findViewById(R.id.reportViewingPFP);
        ref = FirebaseDatabase.getInstance().getReference().child("ReportDetails");

        String key = getIntent().getStringExtra("key");
        assert key != null;
        ref.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String name = Objects.requireNonNull(snapshot.child("name").getValue()).toString(); // Prevents Null Point Exception
                    String surname = Objects.requireNonNull(snapshot.child("surname").getValue()).toString();
                    String age = Objects.requireNonNull(snapshot.child("age").getValue()).toString();
                    String eyeColor = Objects.requireNonNull(snapshot.child("eyeColor").getValue()).toString();
                    String weight = Objects.requireNonNull(snapshot.child("weight").getValue()).toString();
                    String height = Objects.requireNonNull(snapshot.child("height").getValue()).toString();
                    String lastSeenLocation = Objects.requireNonNull(snapshot.child("lastSeenLocation").getValue()).toString();
                    String description = Objects.requireNonNull(snapshot.child("description").getValue()).toString();
                    String imageUri = Objects.requireNonNull(snapshot.child("imageUri").getValue()).toString();

                    Picasso.get().load(imageUri).into(pic);
                    txtName.setText(name);
                    txtSurname.setText(surname);
                    txtAge.setText(age);
                    txtEyeColor.setText(eyeColor);
                    txtWeight.setText(weight);
                    txtHeight.setText(height);
                    txtLocation.setText(lastSeenLocation);
                    txtDescription.setText(description);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void backBtnClicked (View view){
        startActivity(new Intent(getApplicationContext(), ReportViewingActivity.class));
    }

}