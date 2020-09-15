package com.lostdotcom.notifind.Viewing;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lostdotcom.notifind.Activities.SettingsActivity;
import com.lostdotcom.notifind.Databases.DatabaseHelper;
import com.lostdotcom.notifind.Databases.RecyclerViewConfig;
import com.lostdotcom.notifind.Details.ReportDetails;
import com.lostdotcom.notifind.LoginSystem.LoginActivity;

import com.lostdotcom.notifind.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ReportViewingActivity extends AppCompatActivity {


    private RecyclerView myRecyclerView;
    FirebaseRecyclerOptions<ReportDetails> options;
    FirebaseRecyclerAdapter <ReportDetails, MyViewHolder>adapter;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_viewing);
        myRecyclerView = findViewById(R.id.missingPersonsDisplay);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        myRecyclerView.setHasFixedSize(true);

        myRef = FirebaseDatabase.getInstance().getReference().child("ReportDetails");


        loadData();

    }

    private void loadData(){
        options = new FirebaseRecyclerOptions.Builder<ReportDetails>().setQuery(myRef, ReportDetails.class).build();
        adapter = new FirebaseRecyclerAdapter<ReportDetails, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull ReportDetails model) {
                holder.name.setText(model.getName());
                holder.surname.setText(model.getSurname());
                holder.age.setText(model.getAge());
                holder.eyeColor.setText(model.getEyeColor());
                holder.weight.setText(model.getWeight());
                holder.height.setText(model.getHeight());
                holder.lastSeenLocation.setText(model.getLastSeenLocation());
                holder.description.setText(model.getDescription());
                Picasso.get().load(model.getImageUri()).into(holder.profilePic);

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_list_item, parent, false);

                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        myRecyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.settings_menu:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                Intent i = new Intent (this, SettingsActivity.class); // Instance of intent class
                startActivity(i);
                return true;
            case R.id.light_theme:

            case R.id.dark_theme:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                return true;

            default:
                return super.onOptionsItemSelected(item);// Just default functionality that makes sure everything doest break
        }

    }

    public void signOut (View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    private void toaster (String bread){
        Toast.makeText(ReportViewingActivity.this, bread, Toast.LENGTH_SHORT).show();
    }

}