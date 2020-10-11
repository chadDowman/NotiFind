package com.lostdotcom.notifind.Activities;

/*
This class controls the settings screen and is how users access the account settings, notification settings, privacy settings and about settings screens
respectively. It is also where users are able to request closure of their accounts.
 */

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lostdotcom.notifind.LoginSystem.LoginActivity;
import com.lostdotcom.notifind.R;
import com.lostdotcom.notifind.Viewing.ReportViewingActivity;
import com.lostdotcom.notifind.how_to_use;

public class SettingsActivity extends AppCompatActivity {

    private Button btnPrevious;
    private Button btnHowToUseButt;
    private Button btnAbout;
    private Button btnCloseAccount;

    FirebaseAuth myAuth;
    FirebaseUser myUser;

    private FirebaseDatabase myDatabase;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnPrevious =  findViewById(R.id.btnPrevious);
        btnAbout = findViewById(R.id.btnAbout);
        btnCloseAccount = findViewById(R.id.btnCloseAccount);
        btnHowToUseButt = findViewById(R.id.howToUseButt);

        myDatabase = FirebaseDatabase.getInstance();
        myRef = myDatabase.getReference("UserDetails");

        myAuth = FirebaseAuth.getInstance();
        myUser = myAuth.getCurrentUser();

        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,r.getDisplayMetrics());

        btnPrevious.setWidth(px);
        btnAbout.setWidth(px);
        btnCloseAccount.setWidth(px);

    }

    public void btnPreviousClicked (View view){
        Intent i = new Intent (this, ReportViewingActivity.class); // Instance of intent class
        startActivity(i);
    }

    public void btnAboutClicked (View view){
        Intent i = new Intent (this, AboutActivity.class); // Instance of intent class
        startActivity(i);
    }

    public void btnHowToUseButtClicked (View view){
        Intent i = new Intent (this, how_to_use.class); // Instance of intent class

        startActivity(i);
    }

    public void closeAccountClicked (View view){
        AlertDialog.Builder dialog = new AlertDialog.Builder(SettingsActivity.this);
        dialog.setTitle("Are you sure?");
        dialog.setMessage("Deleting this account will completed remove it from our system and you won't be able to access the app.");

        dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    myUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                toaster("Account Deleted");
                                LoginActivity.setAdminOrNot(false);
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            }else{
                                toaster(task.getException().getMessage());
                            }
                        }
                    });
            }
        });

        dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    private void toaster (String bread){
        Toast.makeText(SettingsActivity.this, bread, Toast.LENGTH_LONG).show();
    }

}