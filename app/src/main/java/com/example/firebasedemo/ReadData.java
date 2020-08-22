package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReadData extends AppCompatActivity {
    private Spinner spinners;
    private Button showButton,buttonBack;
    private ListView listView;
    private ArrayList<String> home;
    private ArrayList<String> mobile;
    private ArrayList<String> electronics;
    private String[] categr={"Home","Mobile","Electronics"};
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data);
        home=new ArrayList<>();
        mobile=new ArrayList<>();
        electronics=new ArrayList<>();
        database=FirebaseDatabase.getInstance();
        spinners=findViewById(R.id.spinnerShow);
        showButton=findViewById(R.id.show);
        buttonBack=findViewById(R.id.buttonBack);
        listView=findViewById(R.id.listt);
        ArrayAdapter<String> adapteras=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,categr);
        spinners.setAdapter(adapteras);

        database.getReference().child("Home").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                home.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    home.add(ds.getValue(String.class));
                }
                showButton.callOnClick();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ReadData.this, "Some Error Occured.", Toast.LENGTH_SHORT).show();
            }
        });

        database.getReference().child("Mobile").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mobile.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    mobile.add(ds.getValue(String.class));
                }
                showButton.callOnClick();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ReadData.this, "Some Error Occured.", Toast.LENGTH_SHORT).show();

            }
        });
        database.getReference().child("Electronics").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                electronics.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    electronics.add(ds.getValue(String.class));
                }
                showButton.callOnClick();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ReadData.this, "Some Error Occured.", Toast.LENGTH_SHORT).show();

            }
        });


        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=spinners.getSelectedItemPosition();
                ArrayList<String> selected=new ArrayList<>();
                switch (pos)
                {
                    case 0:
                        selected=home;
                        break;
                    case 1:
                        selected=mobile;
                        break;
                    case 2:
                        selected=electronics;
                        break;

                }
                ArrayAdapter<String> adapter=new ArrayAdapter<>(ReadData.this,android.R.layout.simple_list_item_1,selected);
                listView.setAdapter(adapter);


            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ReadData.this,WriteData.class);
                ReadData.this.finish();
                startActivity(intent);
            }
        });
    }
}
