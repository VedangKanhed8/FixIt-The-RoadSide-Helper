package com.example.mechon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    Button userModeButton;
    Button mechModeButton;
    Button adminModeButton;

    public void setUserModeButton(View view) {
        Intent userLoginIntent = new Intent(getApplicationContext(),user2profile.class);
        startActivity(userLoginIntent);
    }

    public void setMechModeButton(View view) {
        Intent mechLoginIntent =new Intent(getApplicationContext(),mechProfile.class);
        startActivity(mechLoginIntent);
    }

    public void setAdminModeButton(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userModeButton = (Button) findViewById(R.id.userModeBtn);
        mechModeButton = (Button) findViewById(R.id.mechModeBtn);
        //adminModeButton = (Button) findViewById(R.id.adminModeBtn);


//        Intent mechLoginIntent =new Intent(getApplicationContext(),showMechOrd.class);
//        startActivity(mechLoginIntent);


    }
}