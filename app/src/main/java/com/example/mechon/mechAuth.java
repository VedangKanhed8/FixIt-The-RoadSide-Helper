package com.example.mechon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class mechAuth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mech_auth);
        Intent i=getIntent();
        mechClass m1=(mechClass) i.getSerializableExtra("myObj");

        Toast.makeText(getApplicationContext(),m1.getEmail()+" "+m1.getName(),Toast.LENGTH_SHORT).show();
    }
}