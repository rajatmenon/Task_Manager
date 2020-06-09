package com.phineasnferb.taskmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView titlepage,subtitlepage,endpage;

    DatabaseReference db;
    RecyclerView ourtask;
    ArrayList<myTask> list;
    taskAdapter taskAdapter;
    Button addbtn,logoutbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titlepage=findViewById(R.id.titlepage);
        subtitlepage=findViewById(R.id.subtitlepage);
        endpage=findViewById(R.id.endpage);
        addbtn=findViewById(R.id.addbtn);



        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Successfully Logged out", Toast.LENGTH_SHORT).show();
                Intent logout=new Intent(MainActivity.this,Login.class);
                startActivity(logout);
            }
        });



        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add= new Intent(MainActivity.this,AddTask.class);
                startActivity(add);
            }
        });


        //data
        ourtask=findViewById(R.id.ourtask);
        ourtask.setLayoutManager(new LinearLayoutManager(this));
         list=new ArrayList<myTask>();

        //database

        db= FirebaseDatabase.getInstance().getReference().child("TaskManager");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //to retrieve n replace data in layout

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                    myTask p =dataSnapshot1.getValue(myTask.class);
                    list.add(p);
                }

                taskAdapter=new taskAdapter(MainActivity.this,list);
                ourtask.setAdapter(taskAdapter);
                taskAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "No Task Available !", Toast.LENGTH_SHORT).show();

            }
        });




                    }

}
