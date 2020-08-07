package com.lostdotcom.notifind.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.TextView;

import com.lostdotcom.notifind.R;

class DetailsFragment extends ArrayAdapter<String> {

    public DetailsFragment( Context context, String[] name) {
        super(context, R.layout.details ,name);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater listInflat = LayoutInflater.from(getContext());
       View custom = listInflat.inflate(R.layout.details, parent, false);

       String newList = getItem(position);
        TextView nameView = (TextView) custom.findViewById(R.id.nameView);
        TextView surnameView = (TextView) custom.findViewById(R.id.surnameView);
        TextView ageView = (TextView) custom.findViewById(R.id.ageView);
        TextView lastLocation = (TextView) custom.findViewById(R.id.lastLocation);
        ImageView profile = (ImageView) custom.findViewById(R.id.profile);

        nameView.setText(newList);
        surnameView.setText(newList);
        ageView.setText(newList);
        lastLocation.setText(newList);
        profile.setImageResource(R.drawable.logo);
        return custom;
    }
}
