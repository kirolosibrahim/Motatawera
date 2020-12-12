package com.kmk.motatawera;

import android.os.Bundle;

import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //FindView
        TextView title = findViewById(R.id.titlefragment);


        BottomNavigationView navView = findViewById(R.id.nav_BottomNavigationView);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        if (savedInstanceState == null) {
            navView.setSelectedItemId(R.id.nav_post);// change to whichever id should be default
            title.setText(R.string.Postfragment); //defult title
        }else {
            navView.setOnNavigationItemSelectedListener(v -> {


                switch (v.getItemId()) {
                    case R.id.nav_post:
                        navController.navigate(R.id.nav_post);
                        title.setText(R.string.Postfragment);
                        return true;

                    case R.id.nav_chat:
                        navController.navigate(R.id.nav_chat);
                        title.setText(R.string.Chatfragment);
                        return true;
                    case R.id.nav_notification:
                        navController.navigate(R.id.nav_notification);
                        title.setText(R.string.Notificationfragment);
                        return true;

                    case R.id.nav_profile:
                        navController.navigate(R.id.nav_profile);
                        title.setText(R.string.Profilefragment);
                        return true;

                    case R.id.nav_setting:
                        navController.navigate(R.id.nav_setting);
                        title.setText(R.string.Settingfragment);
                        return true;

                }
                return false;

            });
        }
    }
}