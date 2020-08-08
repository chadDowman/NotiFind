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

    private String[] names = {"Chad", "Curtis", "Haarith", "Americo", "Mrs O"};
    private String[] surnames = {"Dowman", "Life", "Thing", "Person", "Lol"};
    private String[] age = {"21", "22", "23", "24", "25"};
    private String[] location = {"Marsh", "Cresent","Place", "Thing" ,"The place"};



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

    /*
    public void initialArrays {

    }
     */

    //Getters and Setters
    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public String[] getSurnames() {
        return surnames;
    }

    public void setSurnames(String[] surnames) {
        this.surnames = surnames;
    }

    public String[] getAge() {
        return age;
    }

    public void setAge(String[] age) {
        this.age = age;
    }

    public String[] getLocation() {
        return location;
    }

    public void setLocation(String[] location) {
        this.location = location;
    }
}