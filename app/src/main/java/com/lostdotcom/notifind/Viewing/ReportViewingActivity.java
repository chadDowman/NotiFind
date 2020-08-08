package com.lostdotcom.notifind.Viewing;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lostdotcom.notifind.Activities.SettingsActivity;
import com.lostdotcom.notifind.R;

public class ReportViewingActivity extends AppCompatActivity {

     int[] IMAGES = {R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo};
     String[] names = {"Chad", "Curtis", "Haarith", "Americo", "Mrs O"};
     String[] surnames = {"Dowman", "Life", "Thing", "Person", "Lol"};
     String[] age = {"21", "22", "23", "24", "25"};
     String[] location = {"Marsh", "Cresent","Place", "Thing" ,"The place"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_viewing);

        ListView listView = findViewById(R.id.missingList);
        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);

    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @SuppressLint("ViewHolder")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.activity_custom_rows, null);

            ImageView imageView = view.findViewById(R.id.custom_image);
            TextView textView_name = view.findViewById(R.id.custom_name);
            TextView textView_surname = view.findViewById(R.id.custom_surname);
            TextView textView_age = view.findViewById(R.id.custom_age);
            TextView textView_location = view.findViewById(R.id.custom_location);

            imageView.setImageResource(IMAGES[i]);
            textView_name.setText(names[i]);
            textView_surname.setText(surnames[i]);
            textView_age.setText(age[i]);
            textView_location.setText(location[i]);


            return view;
        }
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
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                return true;
                /*
            case R.id.dark_theme:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                return true;
                 */
            default:
                return super.onOptionsItemSelected(item);// Just default functionality that makes sure everything doest break
        }

    }

}