package com.example.budget;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class profile extends AppCompatActivity {

    TextView profile_picture;
    TextView PUsername, PAddress, PContact_number, PEmail;
    String username, address, contact_number, email, userid;

    Intent planner,setting;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        BottomNavigationView bottom_nav = findViewById(R.id.navigation);
        bottom_nav.setSelectedItemId(R.id.profile);
        profile_picture = findViewById(R.id.title2);
        DB = new DBHelper(this);


        planner = new Intent(getApplicationContext(), planning.class);
        setting = new Intent(getApplicationContext(), setting.class);

        Log.d("jet","ano1");
        Intent intent = getIntent();
        String login = intent.getStringExtra("Login");
        Log.d("jet","ano2");

        String[] userDetails = DB.getUserDetailsByEmail(login);
        username = userDetails[0];
        address = userDetails[1];
        email = userDetails[2];
        contact_number = userDetails[3];
        userid = userDetails[4];
        Log.d("jet","ano3");

        char frst = username.charAt(0);
        String s = ""+frst;
        profile_picture.setText(s.toUpperCase());
        PUsername = findViewById(R.id.user_display);
        PAddress = findViewById(R.id.address_display);
        PContact_number = findViewById(R.id.contact_display);
        PEmail = findViewById(R.id.email_display);
        Log.d("jet","ano5");

        PUsername.append("\n" + username);
        PAddress.append("\n" + address);
        PContact_number.append("\n" + contact_number);
        PEmail.append("\n" + email);



        bottom_nav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.profile) {

                return  true;
            } else if (item.getItemId() ==R.id.planner) {
                planner.putExtra("Login", login);
                startActivity(planner);
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (item.getItemId() ==R.id.setting) {
                setting.putExtra("Login", login);
                startActivity(setting);
                overridePendingTransition(0, 0);
                finish();
                return true;
            }

            return false;
        });




    }



}