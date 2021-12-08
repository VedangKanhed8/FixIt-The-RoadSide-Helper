package com.example.mechon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class userMainView extends AppCompatActivity {

    private Button showMech,showOrd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_view);
        showMech=findViewById(R.id.BookSerBtn);
        showOrd=findViewById(R.id.ordViewBtnu);
        Intent i = getIntent();
        userClass u1 = (userClass) i.getSerializableExtra("myObj");
        showMech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j =new Intent(getApplicationContext(),userMechView.class);
                j.putExtra("myObj",(Serializable) u1);
                startActivity(j);
            }
        });
        showOrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j =new Intent(getApplicationContext(),userPend.class);
                startActivity(j);
            }
        });

    }
}