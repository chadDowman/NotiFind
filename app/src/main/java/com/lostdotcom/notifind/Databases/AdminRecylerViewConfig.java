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

import com.lostdotcom.notifind.Activities.AdminCreationActivity;
import com.lostdotcom.notifind.Details.AdminDetails;
import com.lostdotcom.notifind.R;

import java.util.List;

public class AdminRecylerViewConfig {


    private Context myContext;

    public void setAdminConfig(RecyclerView recyclerView, Context context, List<AdminDetails> admins, List<String> keys){

        myContext = context;
        AdminAdapter myAdminAdapter = new AdminAdapter(admins, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(myAdminAdapter);
    }

    class AdminItemView extends RecyclerView.ViewHolder{

        private TextView adminEmail;
        private TextView adminPassword;
        private TextView adminLocation;
        private TextView adminPhoneNo;
        private String key;


        public AdminItemView(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(myContext).inflate(R.layout.admin_list_items, parent, false));

            adminEmail = itemView.findViewById(R.id.admin_email);
            adminPassword = itemView.findViewById(R.id.admin_Password);
            adminLocation = itemView.findViewById(R.id.admin_address);
            adminPhoneNo = itemView.findViewById(R.id.admin_telNum);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(myContext, AdminCreationActivity.class);
                    intent.putExtra("key", key);
                    intent.putExtra("email", adminEmail.getText().toString());
                    intent.putExtra("password", adminPassword.getText().toString());
                    intent.putExtra("location", adminLocation.getText().toString());
                    intent.putExtra("phoneNo", adminPhoneNo.getText().toString());

                    myContext.startActivity(intent);
                }
            });

        }

        //Will Recieve report object and key and populate the textviews
        public void bind(AdminDetails admin, String key){

            adminEmail.setText(admin.getAdminEmail());
            adminPassword.setText(admin.getAdminPassword());
            adminLocation.setText(admin.getAdminLocation());
            adminPhoneNo.setText(admin.getAdminPhoneNumber());
            this.key = key; // Sets the current key
        }


    }

    class AdminAdapter extends RecyclerView.Adapter<AdminItemView> {

        private List<AdminDetails> myAdmins;
        private List<String> myKeys;

        public AdminAdapter(List<AdminDetails> myAdmins, List<String> myKeys) {
            this.myAdmins = myAdmins;
            this.myKeys = myKeys;
        }

        public AdminAdapter() {
            super();
        }

        @NonNull
        @Override
        public AdminItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AdminItemView(parent); // Passes the view groups object
        }

        @Override
        public void onBindViewHolder(@NonNull AdminItemView holder, int position) {
            holder.bind(myAdmins.get(position), myKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return myAdmins.size();
        }
    }
}
