package com.example.mechon;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.io.Serializable;
import java.util.ArrayList;

public class mechAdapter extends RecyclerView.Adapter<mechAdapter.MyviewHolder>
{
    Context context;
    ArrayList<mechClass> list;
    userClass user1;

    public mechAdapter(Context context, ArrayList<mechClass> list,userClass user1) {
        this.context = context;
        this.list = list;
        this.user1=user1;
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
        holder.mphone.setText("Mobile :"+m.getPhone());
        Uri ur;
        ur=Uri.parse(m.getProlink());
        FirebaseAuth firebaseAuth;
        firebaseAuth= FirebaseAuth.getInstance();
        DatabaseReference root = FirebaseDatabase.getInstance().getReference("orders").child("mechOrd").child(m.getMuid());
        DatabaseReference root2 = FirebaseDatabase.getInstance().getReference("orders").child("userOrd").child(firebaseAuth.getUid());
        Glide.with(context).load(ur).into(holder.mphoto);

        holder.bookSer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialog=DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                        .setContentHolder(new ViewHolder(R.layout.add_address_item))
                        .setExpanded(false)
                        .create();
                View cartView=dialog.getHolderView();
                TextInputEditText addAddress=cartView.findViewById(R.id.addAdresst);
                Button addBtn=cartView.findViewById(R.id.addAdressBtn);
                addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userClass us=new userClass();
                        orders ord=new orders();
                        //
                        us.setAddress(addAddress.getText().toString());
                        us.setEmail(user1.getEmail());
                        us.setPhone(user1.getPhone());
                        us.setName(user1.getName());
                        us.setUuid(firebaseAuth.getUid());
                        //
                        ord.setFlag("1");
                        ord.setLati("100");
                        ord.setLongi("100");
                        ord.setUser(us);
                        ord.setMechanical(m);
                        String md=root.push().getKey();
                        String md1=root2.push().getKey();
                        ord.setOid(md);
                        ord.setOid2(md1);
                        root.child(md).setValue(ord);
                        root2.child(md1).setValue(ord);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        holder.showFeedMech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(context,showuserfed.class);
                j.putExtra("mechUid",m.getMuid());
                context.startActivity(j);
            }
        });

    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder{

        TextView mname,madd,mphone;
        ImageView mphoto;
        Button bookSer,showFeedMech;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            mname=itemView.findViewById(R.id.mechNameId);
            madd=itemView.findViewById(R.id.mechAdd);
            mphone=itemView.findViewById(R.id.mechphone);
            mphoto=itemView.findViewById(R.id.profilId);
            bookSer=itemView.findViewById(R.id.bookApp);
            showFeedMech=itemView.findViewById(R.id.ShowUserMechrat);
        }
    }
}
