package com.lostdotcom.notifind.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.lostdotcom.notifind.R;


public class listViewText extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_viewing);

        String[] list = {"Name", "Surname", "Age", "Last Location"};
           ListAdapter adapt = new DetailsFragment(this, list);
           ListView missingList = (ListView) findViewById(R.id.missingList);
        missingList.setAdapter(adapt);

        missingList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                        String listView = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(listViewText.this, listView, Toast.LENGTH_LONG).show();


                    }
                }
        );
    }
}
