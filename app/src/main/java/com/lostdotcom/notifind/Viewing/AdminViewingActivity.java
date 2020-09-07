package com.lostdotcom.notifind.Viewing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lostdotcom.notifind.Databases.AdminRecylerViewConfig;
import com.lostdotcom.notifind.Databases.DatabaseHelperAdmins;
import com.lostdotcom.notifind.Databases.RecyclerViewConfig;
import com.lostdotcom.notifind.Details.AdminDetails;
import com.lostdotcom.notifind.R;

import java.util.List;

public class AdminViewingActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_viewing);

        myRecyclerView = findViewById(R.id.adminDisplay);

        new DatabaseHelperAdmins().readAdmins(new DatabaseHelperAdmins.DataStatus() {
            @Override
            public void DataIsLoaded(List<AdminDetails> adminCreations, List<String> keys) {
                new AdminRecylerViewConfig().setAdminConfig(myRecyclerView, AdminViewingActivity.this, adminCreations, keys);
            }

            @Override
            public void DataInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

    }
}