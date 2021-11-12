package com.example.mechon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class userFeed extends AppCompatActivity {


    private RecyclerView recyclerView;
    private DatabaseReference database;
    private userOrderAdapter adapter;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed);
        recyclerView=findViewById(R.id.userFeedRec);
        auth=FirebaseAuth.getInstance();
        database=  FirebaseDatabase.getInstance().getReference("orders").child("userPend").child(auth.getUid());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<orders> options=
                new FirebaseRecyclerOptions.Builder<orders>()
                        .setQuery(database,orders.class)
                        .build();
        adapter=new userOrderAdapter(options,this,"feedOrd");
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