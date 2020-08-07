package com.lostdotcom.notifind.Viewing;



import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.lostdotcom.notifind.R;

public class ReportViewingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_viewing);

        String[] names = {"Chad","Ulrich","Mrs o", "Haarith", "Americo"};
        ListAdapter adapter = new CustomAdapter(this, names);
        ListView list = findViewById(R.id.missingList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        // Whenever you tap a item in the list each item has a position (0,1,2,3,4 etc)
                        // This says get that position which is a integer and it gets the value and converts it to a string
                        String name = String.valueOf(adapterView.getItemAtPosition(i));
                        Toast.makeText(ReportViewingActivity.this, name, Toast.LENGTH_LONG).show();
                    }
                }

        );
    }
}