package com.lostdotcom.notifind.Activities;

// This is the screen that will post the missing persons reports.

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.lostdotcom.notifind.Databases.DatabaseHelper;
import com.lostdotcom.notifind.Details.ReportDetails;
import com.lostdotcom.notifind.LoginSystem.LoginActivity;
import com.lostdotcom.notifind.R;
import com.lostdotcom.notifind.Viewing.ReportViewingActivity;

import java.util.HashMap;
import java.util.List;


public class ReportCreationActivity extends AppCompatActivity {

    ReportDetails report = new ReportDetails();
    private static final int REQUEST_CODE_IMAGE = 101;
    //---------------------------------------------------
    private EditText txtName;
    private EditText txtSurname;
    private EditText txtAge;
    private EditText txtEyeColor;
    private EditText txtWeight;
    private EditText txtHeight;
    private Button uploadBtn;


    private EditText txtDescription;
    private ImageView profilepic;

    private Switch notificationSwitch;
    private Spinner mySpinner;

    Uri imageUri;
    boolean isImageAdded = false;

    //Firebase
    DatabaseReference myRef;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_creation); // Inflates the layout


        txtName = findViewById(R.id.name);
        txtSurname = findViewById(R.id.surname);
        txtAge = findViewById(R.id.age);
        txtEyeColor = findViewById(R.id.eyecolor);
        txtWeight = findViewById(R.id.weight);
        txtHeight = findViewById(R.id.height);
        txtDescription = findViewById(R.id.description);
        uploadBtn = findViewById(R.id.uploadbtn);
        profilepic = findViewById(R.id.reportCreationPFP);

        myRef = FirebaseDatabase.getInstance().getReference().child("ReportDetails");
        storageReference = FirebaseStorage.getInstance().getReference().child("MissingPeopleImages");
        //--------------------------------------------------------------------------------------
        mySpinner = findViewById(R.id.lastLocation);
        ArrayAdapter<String> myAdapter =  new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.locations));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        //--------------------------------------------------------------------------------------
        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name = txtName.getText().toString();
                final String surname = txtSurname.getText().toString();
                final String age = txtAge.getText().toString();
                final String eyeColor = txtEyeColor.getText().toString();
                final String weight = txtWeight.getText().toString();
                final String height = txtHeight.getText().toString();
                final String description = txtDescription.getText().toString();
                final String location = mySpinner.getSelectedItem().toString();

                report.setName("Name: " + name);
                report.setSurname("Surname: " + surname);
                report.setAge("Age: " + age);
                report.setEyeColor("Eye Color: " + eyeColor);
                report.setWeight("Weight: " + weight);
                report.setHeight("Height: " + height);
                report.setLastSeenLocation("Last Seen Location: " + location);
                report.setDescription(description);

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

                if (TextUtils.isEmpty(description)){
                    txtDescription.setError("Missing Description Field");
                    return;
                }

                uploadImage();

            }
        });







        //--------------------------------------------------------------------------------------
        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,r.getDisplayMetrics());

        txtAge.setWidth(px);
        txtDescription.setWidth(px);
        txtEyeColor.setWidth(px);
        txtHeight.setWidth(px);
        txtSurname.setWidth(px);
        txtName.setWidth(px);
        txtWeight.setWidth(px);

        //----------------------------------------------------------------------------------------------------------
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_IMAGE && data!= null){
            imageUri = data.getData();
            isImageAdded = true;
            profilepic.setImageURI(imageUri);
        }
    }

    private void uploadImage(){
        final String key = myRef.push().getKey(); // It has to be final because its used inside a internal method
        storageReference.child(key + ".jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.child(key + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        report.setImageUri(uri.toString());

                        new DatabaseHelper().addReport(report, new DatabaseHelper.DataStatus() {
                            @Override
                            public void DataIsLoaded(List<ReportDetails> reports, List<String> keys) {

                            }

                            @Override
                            public void DataInserted() {
                                notification();
                                toaster("The report has been recorded successfully");
                            }

                            @Override
                            public void DataIsUpdated() {

                            }

                            @Override
                            public void DataIsDeleted() {

                            }
                        });

                    }
                });


            }
        });
    }


    public void postReportButtonClicked (View view){

    }

    private void notification (){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("n","n", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"n")
                    .setContentText("Code Sphere").setSmallIcon(R.drawable.logo).setAutoCancel(true).setContentText("A new person has gone missing in your area");

            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
            managerCompat.notify(999, builder.build());
        }
    }

    // Makes toast
    private void toaster (String bread){
        Toast.makeText(ReportCreationActivity.this, bread, Toast.LENGTH_SHORT).show();
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
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                startActivity(new Intent(getApplicationContext(), ReportViewingActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);// Just default functionality that makes sure everything doest break
        }

    }

    public void signOut (View view){
        LoginActivity.setAdminOrNot(false);
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}