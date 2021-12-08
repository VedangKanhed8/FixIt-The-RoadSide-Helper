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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.Objects;

public class user2profile extends AppCompatActivity {

    TextInputEditText name,addr,phone,email,pass;
    FirebaseAuth firebaseAuth;
    Button next,log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user2profile);
        name=findViewById(R.id.userName);
        addr=findViewById(R.id.userAddress);
        phone=findViewById(R.id.userPhone);
        next=findViewById(R.id.unext);
        email=findViewById(R.id.userEmail);
        pass=findViewById(R.id.userPass);
        log=findViewById(R.id.loginBtnSign);
        firebaseAuth=FirebaseAuth.getInstance();
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),userLoginView.class);
                startActivity(i);
                finish();
            }
        });

        DatabaseReference root = FirebaseDatabase.getInstance().getReference("user");
        DatabaseReference root1 = FirebaseDatabase.getInstance().getReference("userById");

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = Objects.requireNonNull(name.getText()).toString();
                String userAddress = Objects.requireNonNull(addr.getText()).toString();
                String userPhone = Objects.requireNonNull(phone.getText()).toString();
                String userEmail = Objects.requireNonNull(email.getText()).toString();
                String userPass = Objects.requireNonNull(pass.getText()).toString();

                if(userName.isEmpty()==true || userAddress.isEmpty()==true || userPhone.isEmpty()==true || userEmail.isEmpty()==true || userPass.isEmpty()==true)
                {
                    Toast.makeText(getApplicationContext(), "Some fileds are empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
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
                                user.setUuid(firebaseAuth.getUid());
                                String md=root.push().getKey();
                                root.child(md).setValue(user);
                                userClass u=user;
                                root1.child(firebaseAuth.getUid()).setValue(user);
                                Intent j=new Intent(getApplicationContext(),userMainView.class);
                                j.putExtra("myObj",(Serializable) u);
                                startActivity(j);
                                finish();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });
    }
}