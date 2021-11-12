package com.example.mechon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class feedbackShow extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference database;
    private FeedBackAdp adapter;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_show);
        recyclerView=findViewById(R.id.showFeedMech);
        auth=FirebaseAuth.getInstance();
        database=  FirebaseDatabase.getInstance().getReference("feedback").child(auth.getUid());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<feedClass> options=
                new FirebaseRecyclerOptions.Builder<feedClass>()
                        .setQuery(database,feedClass.class)
                        .build();
        adapter=new FeedBackAdp(options,this);
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