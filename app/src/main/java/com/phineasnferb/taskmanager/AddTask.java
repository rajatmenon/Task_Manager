package com.phineasnferb.taskmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class AddTask extends AppCompatActivity {

    Button addbtn,cancelbtn;
    TextView titlepage,addtitle,adddesc,adddate;
    EditText tasktitle,taskdate,taskdesc;
    DatabaseReference db;
    Integer taskNum = new Random().nextInt();
    String key=Integer.toString(taskNum);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        tasktitle=findViewById(R.id.tasktitle);
        taskdate=findViewById(R.id.taskdate);
        taskdesc=findViewById(R.id.taskdesc);

        titlepage=findViewById(R.id.titlepage);
        addtitle=findViewById(R.id.addtitle);
        adddate=findViewById(R.id.adddate);
        adddesc=findViewById(R.id.adddesc);

        Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/ML.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/MM.ttf");

        // customize font
        titlepage.setTypeface(MMedium);


        addtitle.setTypeface(MLight);
        tasktitle.setTypeface(MMedium);

        adddesc.setTypeface(MLight);
        taskdesc.setTypeface(MMedium);

        adddate.setTypeface(MLight);
        taskdate.setTypeface(MMedium);

        addbtn.setTypeface(MMedium);
        cancelbtn.setTypeface(MLight);


        addbtn=findViewById(R.id.addbtn);
        cancelbtn=findViewById(R.id.cancelbtn);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db= FirebaseDatabase.getInstance().getReference().child("TaskManager").
                        child("Task"+taskNum);
                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("tasktitle").setValue(tasktitle.getText().toString());
                        dataSnapshot.getRef().child("taskdesc").setValue(taskdesc.getText().toString());
                        dataSnapshot.getRef().child("taskdate").setValue(taskdate.getText().toString());
                        dataSnapshot.getRef().child("key").setValue(key);

                        Toast.makeText(AddTask.this, "Task Added", Toast.LENGTH_SHORT).show();
                        Intent task = new Intent(AddTask.this,MainActivity.class);
                        startActivity(task);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

     cancelbtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent back=new Intent(AddTask.this,MainActivity.class);
             startActivity(back);
         }
     });
    }
}