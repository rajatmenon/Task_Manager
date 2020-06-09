package com.phineasnferb.taskmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Register extends AppCompatActivity {
    Button signupbtn ;
    EditText signupname,signuppass,signupemail;
    FirebaseAuth fAuth;
    TextView logintext;
    ProgressBar progressBarSignup ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signupbtn =findViewById(R.id.signupbtn);
        signupname=findViewById(R.id.signupname);
        signupemail=findViewById(R.id.signupemail);
        signuppass=findViewById(R.id.signuppass);
        logintext=findViewById(R.id.logintext);
        progressBarSignup=findViewById(R.id.progressBarSignup);

        fAuth= FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();


        }

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String name = signupname.getText().toString().trim();
                String email = signupemail.getText().toString().trim();
                String pass = signuppass.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    signupname.setError("Username is required");
                    return;
                }


                if(TextUtils.isEmpty(email)){
                    signupemail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    signuppass.setError("Password is required");
                    return;
                }
                if(pass.length()<6){
                    signuppass.setError("Password must be atleast 6 character long ");
                    return;
                }


                      progressBarSignup.setVisibility(view.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else{
                            Toast.makeText(Register.this, "Unknown Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBarSignup.setVisibility(view.GONE);
                        }

                    }
                });
            }
        });

        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));

            }
        });

    }


}