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

import java.io.Serializable;

public class mechProfile extends AppCompatActivity {

    TextInputEditText name,addr,phone,email,pass;
    FirebaseAuth firebaseAuth;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mech_profile);
        name=findViewById(R.id.MechName);
        addr=findViewById(R.id.mechAdd);
        phone=findViewById(R.id.mechPhone);
        next=findViewById(R.id.next);
        email=findViewById(R.id.mechEmail);
        pass=findViewById(R.id.mechPass);
        firebaseAuth=FirebaseAuth.getInstance();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String mName=name.getText().toString();
                String mAddr=addr.getText().toString();
                String mPhone=phone.getText().toString();
                String mEmail=email.getText().toString();
                String mPass=pass.getText().toString();

                firebaseAuth.createUserWithEmailAndPassword(mEmail,mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_SHORT).show();
                            mechClass m1=new mechClass();
                            m1.setName(mName);
                            m1.setEmail(mEmail);
                            m1.setPhone(mPhone);
                            m1.setAddress(mAddr);
                            Intent i=new Intent(getApplicationContext(),mechAuth.class);
                            i.putExtra("myObj",(Serializable) m1);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}