package com.lostdotcom.notifind.Databases;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lostdotcom.notifind.Details.ReportDetails;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper{

    private FirebaseDatabase myDatabase;
    private DatabaseReference myRef;
    private List<ReportDetails> reports = new ArrayList<>();

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
}
