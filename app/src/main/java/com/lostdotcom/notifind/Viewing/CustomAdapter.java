package com.lostdotcom.notifind.Viewing;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.lostdotcom.notifind.R;

class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(Context context, String[] name) {
        super(context, R.layout.activity_custom_rows,name);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
