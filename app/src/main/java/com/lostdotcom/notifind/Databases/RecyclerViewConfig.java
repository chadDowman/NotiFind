package com.lostdotcom.notifind.Databases;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lostdotcom.notifind.Activities.ReportEditsActivity;
import com.lostdotcom.notifind.Details.AdminDetails;
import com.lostdotcom.notifind.Details.ReportDetails;
import com.lostdotcom.notifind.LoginSystem.LoginActivity;
import com.lostdotcom.notifind.R;
import com.lostdotcom.notifind.Viewing.ReportProfile;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.EventListener;
import java.util.List;

public class RecyclerViewConfig {

    private Context myContext;
    private boolean testAdmin = false;
    private boolean confirmedAdmin = false;
    private DatabaseReference myRef;


    public void setReportConfig(RecyclerView recyclerView, Context context, List<ReportDetails> reports, List<String> keys){
        myContext = context;
        ReportAdapter myReportAdapter = new ReportAdapter(reports, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(myReportAdapter);
    }

    public RecyclerViewConfig() {
    }

    // Responsible for inflating the layout ReportListItem and populating the view items
    class ReportItemView extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView surname;
        private TextView age;
        private TextView eyeColor;
        private TextView weight;
        private TextView height;
        private TextView lastSeenLocation;
        private TextView description;
        private boolean isAdminText;
        private String key;

        public ReportItemView(@NonNull View itemView) {
            super(itemView);
        }

        public ReportItemView(ViewGroup parent){
            super(LayoutInflater.from(myContext).inflate(R.layout.report_list_item, parent, false));
            // Initialise Everything Here
            name = itemView.findViewById(R.id.report_name);
            surname = itemView.findViewById(R.id.report_surname);
            age = itemView.findViewById(R.id.report_age);
            eyeColor = itemView.findViewById(R.id.report_eye_color);
            weight = itemView.findViewById(R.id.report_weight);
            height = itemView.findViewById(R.id.report_height);
            lastSeenLocation = itemView.findViewById(R.id.report_last_location);
            description = itemView.findViewById(R.id.report_description);

            FirebaseDatabase myDatabase = FirebaseDatabase.getInstance();
            myRef = myDatabase.getReference("AdminDetails");

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (LoginActivity.isAdminOrNot()){
                        Intent intent = new Intent(myContext, ReportEditsActivity.class);
                        intent.putExtra("key", key);
                        intent.putExtra("name", name.getText().toString());
                        intent.putExtra("surname", surname.getText().toString());
                        intent.putExtra("age", age.getText().toString());
                        intent.putExtra("eyeColor", eyeColor.getText().toString());
                        intent.putExtra("weight", weight.getText().toString());
                        intent.putExtra("height", height.getText().toString());
                        intent.putExtra("lastSeenLocation", lastSeenLocation.getText().toString());
                        intent.putExtra("description", description.getText().toString());

                        myContext.startActivity(intent);

                    }else{
                        Intent intent = new Intent(myContext, ReportProfile.class);
                        intent.putExtra("key", key);
                        intent.putExtra("name", name.getText().toString());
                        intent.putExtra("surname", surname.getText().toString());
                        intent.putExtra("age", age.getText().toString());
                        intent.putExtra("eyeColor", eyeColor.getText().toString());
                        intent.putExtra("weight", weight.getText().toString());
                        intent.putExtra("height", height.getText().toString());
                        intent.putExtra("lastSeenLocation", lastSeenLocation.getText().toString());
                        intent.putExtra("description", description.getText().toString());

                        myContext.startActivity(intent);
                    }

                }
            });

        }
        //Will Recieve report object and key and populate the textviews
        public void bind(ReportDetails report, String key){
            name.setText(report.getName());
            surname.setText(report.getSurname());
            age.setText(report.getAge());
            eyeColor.setText(report.getEyeColor());
            weight.setText(report.getWeight());
            height.setText(report.getHeight());
            lastSeenLocation.setText(report.getLastSeenLocation());
            description.setText(report.getDescription());
            this.key = key; // Sets the current key
        }
    }

    //Responsible for creating the Report Item View and passes both the report object and key to the bind method
    class ReportAdapter extends RecyclerView.Adapter<ReportItemView> {
        private List<ReportDetails> myReport;
        private List<String> myKeys;

        public ReportAdapter(List<ReportDetails> myReport, List<String> myKeys) {
            this.myReport = myReport;
            this.myKeys = myKeys;
        }

        public ReportAdapter() {
            super();
        }

        @NonNull
        @Override
        public ReportItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ReportItemView(parent); // Passes the view groups object
        }

        @Override
        public void onBindViewHolder(@NonNull ReportItemView holder, int position) {
            holder.bind(myReport.get(position), myKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return myReport.size();
        }
    }

}
