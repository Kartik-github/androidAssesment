package com.example.firebasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

public class WriteData extends AppCompatActivity {
    private Spinner spinner;
    private EditText message;
    private Button submit,buttonBack2;
    private String[] categr={"Home","Mobile","Electronics"};
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_data);
        database=FirebaseDatabase.getInstance();
        spinner=findViewById(R.id.spinner);
        message=findViewById(R.id.msg);
        submit=findViewById(R.id.submit);
        buttonBack2=findViewById(R.id.buttonBack2);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,categr);
        spinner.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WriteData.this,"Adding data..",Toast.LENGTH_SHORT).show();
                int selected_pos=spinner.getSelectedItemPosition();
                String categoryName=categr[selected_pos];
                if(!message.getText().toString().isEmpty()){
                    database.getReference(categoryName).push().setValue(message.getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(WriteData.this,"Data added Succesfully.",Toast.LENGTH_SHORT).show();
                            message.setText("");
                        }
                    })
                    ;
                }else{
                    message.setError("Please enter a message!");
                }
            }
        });
        buttonBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WriteData.this,ReadData.class);
               WriteData.this.finish();
                startActivity(intent);
            }
        });
    }
}
