package com.example.mechon;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class mechAdapter extends RecyclerView.Adapter<mechAdapter.MyviewHolder>
{
    Context context;
    ArrayList<mechClass> list;

    public mechAdapter(Context context, ArrayList<mechClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.mechitem,parent,false);
        return new MyviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position)
    {
        mechClass m=list.get(position);
        holder.mname.setText("Name : "+m.getName());
        holder.madd.setText("Address : "+m.getAddress());
        Uri ur;
        ur=Uri.parse(m.getProlink());
        Glide.with(context).load(ur).into(holder.mphoto);
        holder.bookSer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"booked",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder{

        TextView mname,madd;
        ImageView mphoto;
        Button bookSer;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            mname=itemView.findViewById(R.id.mechNameId);
            madd=itemView.findViewById(R.id.mechAdd);
            mphoto=itemView.findViewById(R.id.profilId);
            bookSer=itemView.findViewById(R.id.bookApp);
        }
    }
}
