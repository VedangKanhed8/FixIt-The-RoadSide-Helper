package com.example.mechon;

import static com.example.mechon.R.id.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class mechPendOrders extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private DatabaseReference database;
    private orderAdapter adapter;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mech_pend_orders);

        recyclerView=findViewById(pendOrd);
        auth=FirebaseAuth.getInstance();
        database=  FirebaseDatabase.getInstance().getReference("orders").child("mechOrd").child(auth.getUid());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<orders> options=
                new FirebaseRecyclerOptions.Builder<orders>()
                        .setQuery(database,orders.class)
                        .build();
        adapter=new orderAdapter(options,this,"mechPend");
        recyclerView.setAdapter(adapter);

        BottomNavigationView bottomNavigationView=findViewById(R.id.ordnevid);
        bottomNavigationView.setSelectedItemId(R.id.pendOrdNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.pendOrdNav:
                        return true;
                    case R.id.compOrdNav:
                        startActivity(new Intent(getApplicationContext(),mechCompOrders.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.startListening();
    }
}