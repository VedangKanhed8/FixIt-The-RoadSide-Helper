package com.example.mechon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class FeedBackAdp extends FirebaseRecyclerAdapter<feedClass,FeedBackAdp.MyviewHolder> {
    Context context;
    public FeedBackAdp(@NonNull FirebaseRecyclerOptions<feedClass> options,Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull FeedBackAdp.MyviewHolder holder, int position, @NonNull feedClass model) {

        feedClass fed=model;
        holder.ufeedname.setText(" User name : "+fed.getUser().getName());
        holder.ufeedRev.setText(fed.getCaption());
        holder.ufeedRat.setText(fed.getRating());
    }

    @NonNull
    @Override
    public FeedBackAdp.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.feedback_view_item,parent,false);
        return new MyviewHolder(v);
    }
    public static class MyviewHolder extends RecyclerView.ViewHolder
    {
        TextView ufeedname,ufeedRev,ufeedRat;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            ufeedname=itemView.findViewById(R.id.userFeedName);
            ufeedRev=itemView.findViewById(R.id.userReview);
            ufeedRat=itemView.findViewById(R.id.ordFeedRat);
        }
    }
}
