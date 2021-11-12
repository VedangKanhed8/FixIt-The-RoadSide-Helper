package com.example.mechon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class userOrders extends AppCompatActivity {

    private Button penU,copU,feedU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_orders);
        penU=findViewById(R.id.userPend);
        copU=findViewById(R.id.userComple);
        feedU=findViewById(R.id.giveFeedBack);
        penU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j =new Intent(getApplicationContext(),userPend.class);
                startActivity(j);
            }
        });
        copU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent j =new Intent(getApplicationContext(),userComp.class);
                startActivity(j);
            }
        });
        feedU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent j =new Intent(getApplicationContext(),userFeed.class);
                startActivity(j);
            }
        });
    }
}