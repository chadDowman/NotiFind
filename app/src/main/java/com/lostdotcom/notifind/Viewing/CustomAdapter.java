package com.lostdotcom.notifind.Viewing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lostdotcom.notifind.R;

class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(Context context, String[] name) {
        super(context, R.layout.activity_custom_rows, name);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater listInflater = LayoutInflater.from(getContext());
        // Context means get background info
        //Inflate means prepare or get rdy for rendering
        View customView = listInflater.inflate(R.layout.activity_custom_rows, parent, false);

        String singleNameItem = getItem(position); // Position is the position of each item in the list
        TextView listText  = customView.findViewById(R.id.text1);
       // ImageView listImage = customView.findViewById();

        listText.setText(singleNameItem); // Sets the text to whatever item it is referencing // Basically changes text dianamically to each item
       // listImage.setImageResource(R.drawable.logo);

        return customView; // This is what we just created and what we returning. It basically says hey use this
    }
}
