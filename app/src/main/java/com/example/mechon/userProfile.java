package com.example.mechon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.Objects;

public class userProfile extends AppCompatActivity {

    TextInputEditText name,addr,phone,email,pass;
    FirebaseAuth firebaseAuth;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        name=findViewById(R.id.userName);
        addr=findViewById(R.id.userAddress);
        phone=findViewById(R.id.userPhone);
        next=findViewById(R.id.next);
        email=findViewById(R.id.userEmail);
        pass=findViewById(R.id.userPass);
        firebaseAuth=FirebaseAuth.getInstance();

        DatabaseReference root = FirebaseDatabase.getInstance().getReference("user");

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = Objects.requireNonNull(name.getText()).toString();
                String userAddress = Objects.requireNonNull(addr.getText()).toString();
                String userPhone = Objects.requireNonNull(phone.getText()).toString();
                String userEmail = Objects.requireNonNull(email.getText()).toString();
                String userPass = Objects.requireNonNull(pass.getText()).toString();

                firebaseAuth.createUserWithEmailAndPassword(userEmail,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_SHORT).show();
                            userClass user=new userClass();
                            user.setName(userName);
                            user.setEmail(userEmail);
                            user.setPhone(userPhone);
                            user.setAddress(userAddress);

                            String md=root.push().getKey();
                            root.child(md).setValue(user);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}