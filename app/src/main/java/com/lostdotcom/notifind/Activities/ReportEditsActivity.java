package com.lostdotcom.notifind.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lostdotcom.notifind.Databases.DatabaseHelper;
import com.lostdotcom.notifind.Details.ReportDetails;
import com.lostdotcom.notifind.R;

import java.util.List;

public class ReportEditsActivity extends AppCompatActivity {

    private EditText txtNameEdit;
    private EditText txtSurnameEdit;
    private EditText txtAgeEdit;
    private EditText txtEyeColorEdit;
    private EditText txtWeightEdit;
    private EditText txtHeightEdit;
    private EditText txtLastSeenLocationEdit;
    private EditText txtDescriptionEdit;
    private Button btnUpdateReportEdit;
    private Button btnDeleteReportEdit;

    private String key;
    private String txtName;
    private String txtSurname;
    private String txtAge;
    private String txtEyeColor;
    private String txtWeight;
    private String txtHeight;
    private String txtLastSeenLocation;
    private String txtDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_edits);

        key = getIntent().getStringExtra("key");

        txtName = getIntent().getStringExtra("name");
        txtSurname = getIntent().getStringExtra("surname");
        txtAge = getIntent().getStringExtra("age");
        txtEyeColor = getIntent().getStringExtra("eyeColor");
        txtWeight = getIntent().getStringExtra("weight");
        txtHeight = getIntent().getStringExtra("height");
        txtLastSeenLocation = getIntent().getStringExtra("lastSeenLocation");
        txtDescription = getIntent().getStringExtra("description");

        txtNameEdit = findViewById(R.id.name);
        txtNameEdit.setText(txtName);

        txtSurnameEdit = findViewById(R.id.surname);
        txtSurnameEdit.setText(txtSurname);

        txtAgeEdit = findViewById(R.id.age);
        txtAgeEdit.setText(txtAge);

        txtEyeColorEdit = findViewById(R.id.eyecolor);
        txtEyeColorEdit.setText(txtEyeColor);

        txtWeightEdit = findViewById(R.id.weight);
        txtWeightEdit.setText(txtWeight);

        txtHeightEdit = findViewById(R.id.height);
        txtHeightEdit.setText(txtHeight);

        txtLastSeenLocationEdit = findViewById(R.id.lastLocation);
        txtLastSeenLocationEdit.setText(txtLastSeenLocation);

        txtDescriptionEdit = findViewById(R.id.description);
        txtDescriptionEdit.setText(txtDescription);

        btnUpdateReportEdit = findViewById(R.id.updateReport);
        btnDeleteReportEdit = findViewById(R.id.deleteReport);

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
                report.setLastSeenLocation(txtLastSeenLocationEdit.getText().toString());
                report.setDescription(txtDescriptionEdit.getText().toString());

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
                        return; // Stops executions of anything else
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
                Intent i = new Intent (this, ReportCreationActivity.class); // Instance of intent class
                startActivity(i);
                return true;
            case R.id.report_view:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                Intent intent = new Intent (this, ReportEditsActivity.class); // Instance of intent class
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);// Just default functionality that makes sure everything doest break
        }

    }
}