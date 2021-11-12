package com.example.mechon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

public class userLoginView extends AppCompatActivity {

    TextInputEditText email,pass;
    Button loginBtn;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login_view);
        email=findViewById(R.id.LoginEmail);
        pass=findViewById(R.id.LoginPassword);
        auth= FirebaseAuth.getInstance();
        loginBtn=findViewById(R.id.loginBtn);
        firebaseDatabase = FirebaseDatabase.getInstance();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String finemail=email.getText().toString();
                String finpass=pass.getText().toString();
                if(TextUtils.isEmpty(finemail) || TextUtils.isEmpty(finpass))
                {
                    Toast.makeText(getApplicationContext(),"Empty",Toast.LENGTH_SHORT).show();
                }
                loginuser(finemail,finpass);
                //Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void loginuser(String finemail, String finpass)
    {
        auth.signInWithEmailAndPassword(finemail,finpass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
//                Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_SHORT).show();
//
//                Intent i=new Intent(getApplicationContext(),userMechView.class);
//                startActivity(i);
                databaseReference = firebaseDatabase.getReference("userById").child(auth.getUid());
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        userClass u=snapshot.getValue(userClass.class);
                        Intent i=new Intent(getApplicationContext(),userMainView.class);
                        i.putExtra("myObj",(Serializable) u);
                        startActivity(i);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}