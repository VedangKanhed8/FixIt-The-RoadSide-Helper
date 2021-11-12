package com.example.mechon;

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

import java.io.Serializable;

public class mechLogin extends AppCompatActivity {

    private TextInputEditText memail,mpass;
    private Button loginBtnm;
    private FirebaseAuth authm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mech_login);
        memail=findViewById(R.id.LoginEmailMech);
        mpass=findViewById(R.id.LoginPasswordMech);
        loginBtnm=findViewById(R.id.loginBtnMech);
        authm=FirebaseAuth.getInstance();
        loginBtnm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String finemail=memail.getText().toString();
                String finpass=mpass.getText().toString();
                if(TextUtils.isEmpty(finemail) || TextUtils.isEmpty(finpass))
                {
                    Toast.makeText(getApplicationContext(),"Empty",Toast.LENGTH_SHORT).show();
                }
                loginuser(finemail,finpass);
            }
        });

    }

    private void loginuser(String finemail, String finpass)
    {
        authm.signInWithEmailAndPassword(finemail,finpass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Intent i=new Intent(getApplicationContext(),mechMain.class);
                startActivity(i);
            }
        });
    }
}