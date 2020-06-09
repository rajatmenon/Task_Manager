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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class EditTask extends AppCompatActivity {

    Button updatebtn, deletebtn;
    EditText taskTitle, taskDate, taskDesc;
    DatabaseReference db;
   // Integer taskNum = new Random().nextInt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        taskTitle = findViewById(R.id.tasktitle);
        taskDate = findViewById(R.id.taskdate);
        taskDesc = findViewById(R.id.taskdesc);

        Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/ML.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/MM.ttf");

        // customize font




        taskTitle.setTypeface(MMedium);
        taskDesc.setTypeface(MMedium);
        taskDate.setTypeface(MMedium);

        deletebtn.setTypeface(MMedium);
        updatebtn.setTypeface(MLight);


        updatebtn = findViewById(R.id.updatebtn);
        deletebtn = findViewById(R.id.deletebtn);

        //get Value from TaskAdapter
        taskTitle.setText(getIntent().getStringExtra("tasktitle"));
        taskDesc.setText(getIntent().getStringExtra("taskdesc"));
        taskDate.setText(getIntent().getStringExtra("taskdate"));

        final String Taskkey=getIntent().getStringExtra("key");
        db= FirebaseDatabase.getInstance().getReference().child("TaskManager").
                child("Task"+Taskkey);



        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("tasktitle").setValue(taskTitle.getText().toString());
                        dataSnapshot.getRef().child("taskdesc").setValue(taskDesc.getText().toString());
                        dataSnapshot.getRef().child("taskdate").setValue(taskDate.getText().toString());
                       // dataSnapshot.getRef().child("key").setValue(Taskkey);

                        Toast.makeText(EditTask.this, "Task Updated", Toast.LENGTH_SHORT).show();

                        Intent update = new Intent(EditTask.this,MainActivity.class);
                        startActivity(update);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
         });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(EditTask.this, "Deletion Successful", Toast.LENGTH_SHORT).show();

                            Intent del=new Intent(EditTask.this,MainActivity.class);
                            startActivity(del);
                        }
                        else{
                            Toast.makeText(EditTask.this, "Cannot delete!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });


    }
}
