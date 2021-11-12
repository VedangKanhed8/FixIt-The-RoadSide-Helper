package com.example.mechon;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class userOrderAdapter extends FirebaseRecyclerAdapter<orders,userOrderAdapter.MyviewHolder>
{
    Context context;
    String cur;


    public userOrderAdapter(@NonNull FirebaseRecyclerOptions<orders> options, Context context, String cur) {
        super(options);
        this.context = context;
        this.cur = cur;
    }


    @Override
    protected void onBindViewHolder(@NonNull userOrderAdapter.MyviewHolder holder, int position, @NonNull orders model)
    {
        orders ord=model;
        holder.mname.setText(" Name : "+ord.getMechanical().getName());
        holder.mphone.setText(" Phone number:"+ord.getMechanical().getPhone());
        if(cur=="showOrd")
        {
            ViewGroup layout = (ViewGroup) holder.giveFedd.getParent();
            if(null!=layout)
                layout.removeView(holder.giveFedd);
        }
        else if(cur=="feedOrd")
        {
            holder.giveFedd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    DialogPlus dialog=DialogPlus.newDialog(context)
                            .setGravity(Gravity.CENTER)
                            .setMargin(50,0,50,0)
                            .setContentHolder(new ViewHolder(R.layout.send_feedback))
                            .setExpanded(false)
                            .create();
                    View feedView=dialog.getHolderView();
                    TextInputEditText feedTxt=feedView.findViewById(R.id.FeedT);
                    RatingBar rating=feedView.findViewById(R.id.ratingBar);
                    Button sendF=feedView.findViewById(R.id.feedSendBtn);


                    sendF.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String val=String.valueOf(rating.getRating());
                            feedClass f=new feedClass(val,feedTxt.getText().toString(),ord.getUser(),ord.getMechanical());
                            DatabaseReference root = FirebaseDatabase.getInstance().getReference("feedback").child(ord.getMechanical().getMuid());
                            DatabaseReference ref2=FirebaseDatabase.getInstance().getReference("orders").child("userPend").child(ord.getUser().getUuid()).child(ord.getOid());
                            String md=root.push().getKey();
                            root.child(md).setValue(f);
                            ref2.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    dialog.dismiss();
                                    Toast.makeText(context, "Thanks for your valuable feedback", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    dialog.show();
                }
            });
        }
    }

    @NonNull
    @Override
    public userOrderAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.userorderitem,parent,false);
        return new MyviewHolder(v);
    }


    public static class MyviewHolder extends RecyclerView.ViewHolder
    {
        TextView mname,mphone;
        ImageButton giveFedd;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            mname=itemView.findViewById(R.id.ordMechname);
            mphone=itemView.findViewById(R.id.ordMechPhone);
            giveFedd=itemView.findViewById(R.id.ordFeedRat);
        }
    }
}
