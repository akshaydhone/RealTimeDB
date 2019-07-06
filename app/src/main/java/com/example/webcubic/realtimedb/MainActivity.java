package com.example.webcubic.realtimedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button b1;
    EditText e1;
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.dataField);
        b1=(Button)findViewById(R.id.sendBtn);
        databaseReference=db.getReference("Data");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });
    }


    public void sendData(){
        String e1Text=e1.getText().toString();
        String id=databaseReference.push().getKey();

        if(!TextUtils.isEmpty(e1Text)){
            Data data=new Data(id,e1Text);
            databaseReference.child(id).setValue(data);
            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();

        }

        else{
            Toast.makeText(this, "Please Enter Data", Toast.LENGTH_SHORT).show();
        }
    }
}
