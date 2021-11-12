package com.example.mechon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class pendMechorders extends Fragment {

    View view;
    RecyclerView recyclerView;
    DatabaseReference database;
    orderAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView=view.findViewById(R.id.pendOrdersAdp);
        database=  FirebaseDatabase.getInstance().getReference("orders").child("doneMechOrd").child("lFyGUx7sj5UUh05W1EuwIXKIdKe2");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<orders> options=
                new FirebaseRecyclerOptions.Builder<orders>()
                        .setQuery(database,orders.class)
                        .build();
        adapter=new orderAdapter(options,getContext(),"mechDone");
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_pend_mechorders, container, false);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.startListening();
    }
}