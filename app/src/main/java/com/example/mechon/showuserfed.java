package com.example.mechon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class showuserfed extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference database;
    private FeedBackAdp adapter;
    private FirebaseAuth auth;
    private TextView noItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showuserfed);
        recyclerView=findViewById(R.id.usersidefeed);
        noItem=findViewById(R.id.nofeed);
        auth=FirebaseAuth.getInstance();
        Intent i = getIntent();
        String curUid=i.getStringExtra("mechUid");
        database=  FirebaseDatabase.getInstance().getReference("feedback").child(curUid);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<feedClass> options=
                new FirebaseRecyclerOptions.Builder<feedClass>()
                        .setQuery(database,feedClass.class)
                        .build();
        adapter=new FeedBackAdp(options,this);
        recyclerView.setAdapter(adapter);
        Query queries = database;
        queries.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ViewGroup layout1 = (ViewGroup) noItem.getParent();
                    if(null!=layout1)
                    {
                        layout1.removeView(noItem);
                    }
                }
                else{
                    ViewGroup layout = (ViewGroup) recyclerView.getParent();
                    ViewGroup layout1 = (ViewGroup) noItem.getParent();
                    if(null!=layout)
                    {
                        layout.removeView(recyclerView);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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