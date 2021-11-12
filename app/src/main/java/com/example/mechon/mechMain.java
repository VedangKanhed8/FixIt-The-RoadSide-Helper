package com.example.mechon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mechMain extends AppCompatActivity {

    Button ordBtn,feedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mech_main);
        ordBtn=findViewById(R.id.ordBtn);
        feedBtn=findViewById(R.id.feedBackbtn);
        ordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(getApplicationContext(),showMechOrd.class);
                startActivity(j);
            }
        });
        feedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(getApplicationContext(),feedbackShow.class);
                startActivity(j);
            }
        });
    }
}