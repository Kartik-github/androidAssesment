package com.example.firebasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        database.getReference().child("Hello").setValue("Kartik");
        database.getReference("Hello").child("llo").setValue("Kartasdasdik");
        database.getReference().child("Mello").setValue("Taltik");
    }
}
