package com.example.noti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.HttpCookie;
import android.os.Bundle;

public class HomeSctActivity2 extends AppCompatActivity {

    TextView temp,humid,pirst,reedst,flamest,mq2st;
    FirebaseDatabase database;
    DatabaseReference fbtemp, fbhumid, fbpir, fbreed,fbflame,fbmq2;
    Button btnext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_sct2);
        mapping();

        database = FirebaseDatabase.getInstance();
        fbtemp = database.getReference("Temperature");
        fbhumid = database.getReference("Humidity");
        fbpir = database.getReference("PIR");
        fbreed = database.getReference("REED");
        fbflame= database.getReference("FLAME");
        fbmq2= database.getReference("MQ2");

        // Read from the database
        fbflame.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue().toString();
                flamest.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        fbmq2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue().toString();
                mq2st.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        fbtemp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue().toString();
                temp.setText(value + " °C");
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        fbhumid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue().toString();
                humid.setText(value + " %");
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        fbpir.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue().toString();
                pirst.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        fbreed.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue().toString();
                reedst.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

    }

    private void mapping() {
        temp=findViewById(R.id.fbtemp);
        humid=findViewById(R.id.fbhumid);
        pirst= findViewById(R.id.pirsttfb);
        reedst= findViewById(R.id.reedsttfb);
        flamest= findViewById(R.id.flamesttfb);
        mq2st=findViewById(R.id.gassttfb);
    }

}