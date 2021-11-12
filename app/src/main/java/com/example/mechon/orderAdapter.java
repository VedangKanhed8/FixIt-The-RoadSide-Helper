package com.example.mechon;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class orderAdapter extends FirebaseRecyclerAdapter<orders,orderAdapter.MyviewHolder>
{
    Context context;
    String cur;


    public orderAdapter(@NonNull FirebaseRecyclerOptions<orders> options, Context context, String cur) {
        super(options);
        this.context = context;
        this.cur = cur;
    }

    @Override
    protected void onBindViewHolder(@NonNull MyviewHolder holder, int position, @NonNull orders model)
    {
        if(cur == "mechPend")
        {
            orders ord=model;
            holder.oname.setText(" Name : "+ord.getUser().getName());
            holder.oadd.setText(" Address: "+ord.getUser().getAddress());
            FirebaseDatabase ref,useref1;
            DatabaseReference ref2,userref2,userCan;
            useref1=FirebaseDatabase.getInstance();
            ref=FirebaseDatabase.getInstance();
            ref2=FirebaseDatabase.getInstance().getReference("orders").child("doneMechOrd").child(ord.getMechanical().getMuid());
            userref2=FirebaseDatabase.getInstance().getReference("orders").child("userPend").child(ord.getUser().getUuid());
            userCan=FirebaseDatabase.getInstance().getReference("orders").child("userCancel").child(ord.getUser().getUuid());
            holder.okbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ref.getReference("orders").child("mechOrd").child(ord.getMechanical().getMuid()).child(ord.getOid()).removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    String md=ref2.push().getKey();
                                    ref2.child(md).setValue(ord);
                                }
                            });
                    useref1.getReference("orders").child("userOrd").child(ord.getUser().getUuid()).child(ord.getOid2()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            String md=userref2.push().getKey();
                            ord.setOid(md);
                            userref2.child(md).setValue(ord);
                            Uri uri=Uri.parse("https://www.google.co.in/maps/dir/"+ord.getMechanical().getAddress()+"/"+ord.getUser().getAddress());
                            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                            intent.setPackage("com.google.android.apps.maps");
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                    });
                    //Toast.makeText(context,ord.getUser().getUuid()+" "+ord.getOid2(),Toast.LENGTH_SHORT).show();
                }
            });
            holder.canbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ref.getReference("orders").child("mechOrd").child(ord.getMechanical().getMuid()).child(ord.getOid()).removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    String md=userCan.push().getKey();
                                    userCan.child(md).setValue(ord);
                                }
                            });
                    useref1.getReference("orders").child("userOrd").child(ord.getUser().getUuid()).child(ord.getOid2()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                }
            });
        }
        else if(cur=="mechDone")
        {
           ViewGroup layout = (ViewGroup) holder.okbtn.getParent();
           if(null!=layout)
               layout.removeView(holder.okbtn);
           layout=(ViewGroup) holder.canbtn.getParent();
            if(null!=layout)
                layout.removeView(holder.canbtn);
            orders ord=model;
            holder.oname.setText(" Name : "+ord.getUser().getName());
            holder.oadd.setText(" Address: "+ord.getUser().getAddress());
        }
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(context).inflate(R.layout.orderitem, parent, false);
            return new MyviewHolder(v);


    }

    public static class MyviewHolder extends RecyclerView.ViewHolder
    {
        TextView oname,oadd;
        ImageButton okbtn,canbtn;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            oname=itemView.findViewById(R.id.ordname);
            oadd=itemView.findViewById(R.id.ordAdd);
            okbtn=itemView.findViewById(R.id.ordOk);
            canbtn=itemView.findViewById(R.id.ordCan);
        }
    }
}
