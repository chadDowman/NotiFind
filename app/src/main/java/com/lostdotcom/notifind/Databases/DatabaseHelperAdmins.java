package com.lostdotcom.notifind.Databases;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lostdotcom.notifind.Details.AdminDetails;


import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperAdmins {

    private DatabaseReference myRef;
    private List<AdminDetails> adminCreations = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<AdminDetails> adminCreations, List<String> keys);
        void DataInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public DatabaseHelperAdmins() {
        FirebaseDatabase myDatabase = FirebaseDatabase.getInstance();
        myRef = myDatabase.getReference("AdminDetails");
    }

    public void readAdmins(final DatabaseHelperAdmins.DataStatus dataStatus){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { // Asynchronous method
                adminCreations.clear(); // Clears old report data
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    AdminDetails admin = keyNode.getValue(AdminDetails.class);
                    adminCreations.add(admin); // Adds report to the report list
                }
                dataStatus.DataIsLoaded(adminCreations, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    // This method adds a new report to the Firebase Real Time Database
    public void addAdmin (AdminDetails admin, final DatabaseHelperAdmins.DataStatus dataStatus){
        String key = myRef.push().getKey(); // generates a new child in the database and gives us the unique id that child hence the key
        myRef.child(key).setValue(admin).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataInserted();
            }
        });
    }

    public void updateAdmin (String key, AdminDetails admin, final DatabaseHelperAdmins.DataStatus dataStatus){
        // The listener just checks if the operation worked successfully
        myRef.child(key).setValue(admin).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdated();
            }
        });
    }

    public void deleteAdmin (String key, final DatabaseHelperAdmins.DataStatus dataStatus){
        // If something does not have a value or a null value it automatically deletes it from the database
        myRef.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsDeleted();
            }
        });
    }
}
