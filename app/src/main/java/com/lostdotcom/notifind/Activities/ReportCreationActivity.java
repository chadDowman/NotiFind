package com.lostdotcom.notifind.Activities;

// This is the screen that will post the missing persons reports.

import androidx.annotation.NonNull;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.lostdotcom.notifind.Databases.DatabaseHelper;
import com.lostdotcom.notifind.Details.ReportDetails;
import com.lostdotcom.notifind.LoginSystem.LoginActivity;
import com.lostdotcom.notifind.R;

import java.util.List;

public class ReportCreationActivity extends AppCompatActivity {

    //---------------------------------------------------
    private EditText txtName;
    private EditText txtSurname;
    private EditText txtAge;
    private EditText txtEyeColor;
    private EditText txtWeight;
    private EditText txtHeight;
    private EditText txtLastSeenLocation;
    private EditText txtDescription;
    private ImageView profilepic;
    StorageReference mStorageRef;
    public Uri image;
    private StorageTask uploadTask;
    private Switch notificationSwitch;
    private Spinner mySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_creation); // Inflates the layout

        //--------------------------------------------------------------------------------------

        mySpinner = findViewById(R.id.lastLocation);
        ArrayAdapter<String> myAdapter =  new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.locations));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        //--------------------------------------------------------------------------------------
        mStorageRef = FirebaseStorage.getInstance().getReference( "Images");
        //--------------------------------------------------------------------------------------

        txtName = findViewById(R.id.name);
        txtSurname = findViewById(R.id.surname);
        txtAge = findViewById(R.id.age);
        txtEyeColor = findViewById(R.id.eyecolor);
        txtWeight = findViewById(R.id.weight);
        txtHeight = findViewById(R.id.height);
    //    txtLastSeenLocation = findViewById(R.id.lastLocation);
        txtDescription = findViewById(R.id.description);
        //--------------------------------------------------------------------------------------
        Button btnPostReport = findViewById(R.id.postReport);
        profilepic = findViewById(R.id.imageView);
        Button uploadbtn = findViewById(R.id.uploadbtn);
        ImageView PP = findViewById(R.id.profilepic);
        Button choosebtn = findViewById(R.id.choosebtn);
        //--------------------------------------------------------------------------------------
        choosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileChooser();
            }

        });

        uploadbtn.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
                if (uploadTask != null && uploadTask.isInProgress()) {
                    Toast.makeText( ReportCreationActivity.this, "Upload in progress", Toast.LENGTH_LONG).show();

                } else {
                    Fileuploader();
                }
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


    private String getExtension(Uri uri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
    private void Fileuploader(){

        StorageReference Ref= mStorageRef.child(System.currentTimeMillis()+ "." + getExtension(image));


        uploadTask = Ref.putFile(image)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText( ReportCreationActivity.this, "Image Upload Successful", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });

    }


    private void FileChooser(){
        Intent intent = new Intent();
        intent.setType("image/'");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);

}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode == RESULT_OK && data!=null && data.getData()!=null);

        image=data.getData();
        profilepic.setImageURI(image);
     //   PP.setImageURI(image);
    }

    public void postReportButtonClicked (View view){

        ReportDetails report = new ReportDetails();

        String name = txtName.getText().toString();
        String surname = txtSurname.getText().toString();
        String age = txtAge.getText().toString();
        String eyeColor = txtEyeColor.getText().toString();
        String weight = txtWeight.getText().toString();
        String height = txtHeight.getText().toString();
        String location = mySpinner.getSelectedItem().toString();
        String description = txtDescription.getText().toString();

        report.setName(name);
        report.setSurname(surname);
        report.setAge(age);
        report.setEyeColor(eyeColor);
        report.setWeight(weight);
        report.setHeight(height);
        report.setLastSeenLocation(location);
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

        new DatabaseHelper().addReport(report, new DatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<ReportDetails> reports, List<String> keys) {

            }

            @Override
            public void DataInserted() {
                toaster("The report has been recorded successfully");
            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });


        txtName.setText("");
        txtSurname.setText("");
        txtAge.setText("");
        txtEyeColor.setText("");
        txtWeight.setText("");
        txtHeight.setText("");
        txtLastSeenLocation.setText("");
        txtDescription.setText("");

        notification();


    }

    private void notification (){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("n","n", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"n")
                    .setContentText("Code Sphere").setSmallIcon(R.drawable.logo).setAutoCancel(true).setContentText("New Data added");

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

    public void signOut (View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}