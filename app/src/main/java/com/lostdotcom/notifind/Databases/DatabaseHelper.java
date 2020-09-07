package com.lostdotcom.notifind.Databases;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lostdotcom.notifind.Details.ReportDetails;
import com.lostdotcom.notifind.LoginSystem.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper{

    private FirebaseDatabase myDatabase;
    private DatabaseReference myRef;
    private List<ReportDetails> reports = new ArrayList<>();
    private String user;

    public interface DataStatus{
        void DataIsLoaded(List<ReportDetails> reports, List<String> keys);
        void DataInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public DatabaseHelper() {
        myDatabase = FirebaseDatabase.getInstance();
        myRef = myDatabase.getReference("ReportDetails");
    }

    public void readReports(final DataStatus dataStatus){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { // Asynchronous method
                reports.clear(); // Clears old report data
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    ReportDetails report = keyNode.getValue(ReportDetails.class);
                    reports.add(report); // Adds report to the report list
                }
                dataStatus.DataIsLoaded(reports, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    // This method adds a new report to the Firebase Real Time Database
    public void addReport (ReportDetails report, final DataStatus dataStatus){
        String key = myRef.push().getKey(); // generates a new child in the database and gives us the unique id that child hence the key
        myRef.child(key).setValue(report).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataInserted();
            }
        });
    }

    public void updateReport (String key, ReportDetails report, final DataStatus dataStatus){
        // The listener just checks if the operation worked successfully
        myRef.child(key).setValue(report).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdated();
            }
        });
    }

      public void deleteReport (String key, final DataStatus dataStatus){
        // If something does not have a value or a null value it automatically deletes it from the database
        myRef.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsDeleted();
            }
        });
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
