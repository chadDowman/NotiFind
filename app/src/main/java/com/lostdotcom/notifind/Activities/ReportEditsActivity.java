package com.lostdotcom.notifind.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lostdotcom.notifind.Databases.DatabaseHelper;
import com.lostdotcom.notifind.Details.ReportDetails;
import com.lostdotcom.notifind.R;
import com.lostdotcom.notifind.Viewing.ReportViewingActivity;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class ReportEditsActivity extends AppCompatActivity {

    private EditText txtNameEdit;
    private EditText txtSurnameEdit;
    private EditText txtAgeEdit;
    private EditText txtEyeColorEdit;
    private EditText txtWeightEdit;
    private EditText txtHeightEdit;
    private ImageView pic;
    String imageUriCheck;

    private EditText txtDescriptionEdit;
    private Spinner mySpinner;

    private String key;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_edits);

        txtNameEdit = findViewById(R.id.name);
        txtSurnameEdit = findViewById(R.id.surname);
        txtAgeEdit = findViewById(R.id.age);
        txtEyeColorEdit = findViewById(R.id.eyecolor);
        txtWeightEdit = findViewById(R.id.weight);
        txtHeightEdit = findViewById(R.id.height);
        mySpinner = findViewById(R.id.edits_lastLocation);
        txtDescriptionEdit = findViewById(R.id.description);
        pic = findViewById(R.id.imageView);

        key = getIntent().getStringExtra("key");
        ref = FirebaseDatabase.getInstance().getReference().child("ReportDetails");

        final ArrayAdapter<String> myAdapter =  new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.locations));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);



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
                    imageUriCheck = imageUri;
                    int spinnerPosition = myAdapter.getPosition(lastSeenLocation);

                    Picasso.get().load(imageUri).into(pic);
                    txtNameEdit.setText(name);
                    txtSurnameEdit.setText(surname);
                    txtAgeEdit.setText(age);
                    txtEyeColorEdit.setText(eyeColor);
                    txtWeightEdit.setText(weight);
                    txtHeightEdit.setText(height);
                    mySpinner.setSelection(spinnerPosition);
                    txtDescriptionEdit.setText(description);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Button btnUpdateReportEdit = findViewById(R.id.updateReport);
        Button btnDeleteReportEdit = findViewById(R.id.deleteReport);

        btnUpdateReportEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReportDetails report = new ReportDetails();
                report.setName(txtNameEdit.getText().toString());
                report.setSurname(txtSurnameEdit.getText().toString());
                report.setAge(txtAgeEdit.getText().toString());
                report.setEyeColor(txtEyeColorEdit.getText().toString());
                report.setWeight(txtWeightEdit.getText().toString());
                report.setHeight(txtHeightEdit.getText().toString());
                report.setLastSeenLocation(mySpinner.getSelectedItem().toString());
                report.setDescription(txtDescriptionEdit.getText().toString());
                report.setImageUri(imageUriCheck);

                new DatabaseHelper().updateReport(key, report, new DatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<ReportDetails> reports, List<String> keys) {

                    }

                    @Override
                    public void DataInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        toaster("Record Updated Sucessfully");
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });

            }
        });

        btnDeleteReportEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatabaseHelper().deleteReport(key, new DatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<ReportDetails> reports, List<String> keys) {

                    }

                    @Override
                    public void DataInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        toaster("The report has been deleted successfully");
                        finish();
                        // Stops executions of anything else return here
                    }
                });
            }
        });


    }

    private void toaster (String bread){
        Toast.makeText(ReportEditsActivity.this, bread, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.report_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.report_creation:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                startActivity(new Intent(getApplicationContext(), ReportCreationActivity.class));
                return true;
            case R.id.report_view:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                startActivity(new Intent(getApplicationContext(), ReportViewingActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);// Just default functionality that makes sure everything doest break
        }

    }

    public void backButtonClicked (View view){
        startActivity(new Intent(getApplicationContext(), ReportViewingActivity.class));
    }
}