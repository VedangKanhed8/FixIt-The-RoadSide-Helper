package com.example.mechon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class userMechView extends AppCompatActivity
{
    RecyclerView recyclerView;
    DatabaseReference database;
    mechAdapter adapter;
    ArrayList<mechClass> list;
    String curLat,curLong;
    FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_mech_view);
        Intent i = getIntent();
        userClass u1 = (userClass) i.getSerializableExtra("myObj");
        Toast.makeText(getApplicationContext(),u1.getName()+" "+u1.getAddress(),Toast.LENGTH_SHORT).show();
        recyclerView=findViewById(R.id.mechListView);
        database= FirebaseDatabase.getInstance().getReference("mech");
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list =new ArrayList<>();
        adapter=new mechAdapter(this,list,u1);
        recyclerView.setAdapter(adapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    mechClass m=dataSnapshot.getValue(mechClass.class);
                    list.add(m);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==100 && grantResults.length>0 && (grantResults[0]+grantResults[1]==PackageManager.PERMISSION_GRANTED))
        {
            getCurrentLocation();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"permission denied",Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation()
    {
        LocationManager locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
        {
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location=task.getResult();
                    if(location!=null)
                    {
                        curLat=String.valueOf(location.getLatitude());
                        curLong=String.valueOf(location.getLongitude());
                    }
                    else
                    {
                        LocationRequest locationRequest=new LocationRequest()
                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(10000)
                                .setFastestInterval(1000)
                                .setNumUpdates(1);
                        LocationCallback locationCallback=new LocationCallback() {
                            @Override
                            public void onLocationResult(@NonNull LocationResult locationResult) {
                                Location location1=locationResult.getLastLocation();
                                curLat=String.valueOf(location1.getLatitude());
                                curLong=String.valueOf(location1.getLongitude());
                            }
                        };

                        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper());
                    }
                }
            });
        }
        else
        {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }
}