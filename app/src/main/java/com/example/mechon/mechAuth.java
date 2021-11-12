package com.example.mechon;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

@SuppressWarnings("ALL")
public class mechAuth extends AppCompatActivity {

    private Button proBtn,licBtn,gobtn;
    private ImageView proView,licView;
    private Uri prouri,liuri;
    private StorageReference stoRef= FirebaseStorage.getInstance().getReference();
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("mech");
    private String proDown,liDown;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mech_auth);
        proBtn=findViewById(R.id.proBtnId);
        proView=findViewById(R.id.proId);
        licView=findViewById(R.id.licProId);
        licBtn=findViewById(R.id.licBtn);

        firebaseAuth=FirebaseAuth.getInstance();
        gobtn=findViewById(R.id.nextBtn);
        proView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,2);
            }
        });
        licView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,3);
            }
        });
        proBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(prouri!=null)
                {
                    uploadTofirebase(prouri,"pro");
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"add photo",Toast.LENGTH_SHORT).show();
                }
            }
        });
        licBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(liuri!=null)
                {
                    uploadTofirebase(liuri,"lic");
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"add photo",Toast.LENGTH_SHORT).show();
                }

            }
        });
        gobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),proDown+" "+liDown,Toast.LENGTH_SHORT).show();
                Intent i = getIntent();
                mechClass u1 = (mechClass) i.getSerializableExtra("myObj");

                u1.setProlink(proDown);
                u1.setLiclink(liDown);
                u1.setLati("100");
                u1.setLongi("100");
                u1.setMuid(firebaseAuth.getUid());
                String md=root.push().getKey();
                root.child(md).setValue(u1);
                Intent j=new Intent(getApplicationContext(),mechMain.class);
                startActivity(j);
            }
        });



    }

    private void uploadTofirebase(Uri uri,String typ)
    {
        StorageReference fileRef=stoRef.child(System.currentTimeMillis()+"."+getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(),"Succesful",Toast.LENGTH_SHORT).show();
                        if(typ=="pro")
                        {
                            proDown=uri.toString();
                        }
                        if(typ=="lic")
                        {
                            liDown=uri.toString();
                        }

                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
               // progressBar.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private String getFileExtension(Uri muri)
    {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(muri));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==2 && resultCode==RESULT_OK && data!=null)
        {
            prouri=data.getData();
            proView.setImageURI(prouri);
        }
        if(requestCode==3 && resultCode==RESULT_OK && data!=null)
        {
            liuri=data.getData();
            licView.setImageURI(liuri);
        }
    }
}