package com.example.mechon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class mechCompOrders extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference database;
    private orderAdapter adapter;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mech_comp_orders);
        recyclerView=findViewById(R.id.compMechOrdRec);
        auth=FirebaseAuth.getInstance();
        database=  FirebaseDatabase.getInstance().getReference("orders").child("doneMechOrd").child(auth.getUid());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<orders> options=
                new FirebaseRecyclerOptions.Builder<orders>()
                        .setQuery(database,orders.class)
                        .build();
        adapter=new orderAdapter(options,this,"mechDone");
        recyclerView.setAdapter(adapter);
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