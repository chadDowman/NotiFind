package com.lostdotcom.notifind.Viewing;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lostdotcom.notifind.R;

public class MyViewHolder extends RecyclerView.ViewHolder{

     TextView name;
     TextView surname;
     TextView age;
     TextView eyeColor;
     TextView weight;
     TextView height;
     TextView lastSeenLocation;
     TextView description;
     ImageView profilePic;
     View v;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.report_name);
        surname = itemView.findViewById(R.id.report_surname);
        age = itemView.findViewById(R.id.report_age);
        eyeColor = itemView.findViewById(R.id.report_eye_color);
        weight = itemView.findViewById(R.id.report_weight);
        height = itemView.findViewById(R.id.report_height);
        lastSeenLocation = itemView.findViewById(R.id.report_last_location);
        description = itemView.findViewById(R.id.report_description);
        profilePic = itemView.findViewById(R.id.profilepic);

        v = itemView;

    }
}
