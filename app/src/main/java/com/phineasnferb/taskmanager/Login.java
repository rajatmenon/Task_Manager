package com.phineasnferb.taskmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button loginbtn ;
    EditText loginemail,loginpass;
    FirebaseAuth fAuth;
    TextView signuptext,forgot;
    ProgressBar progressBarLogin ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginbtn =findViewById(R.id.loginbtn);
        loginemail=findViewById(R.id.loginemail);
        loginpass=findViewById(R.id.loginpass);
        signuptext=findViewById(R.id.signuptext);
        progressBarLogin=findViewById(R.id.progressBarLogin);
        forgot=findViewById(R.id.forgot);



        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //String name = signupname.getText().toString().trim();
                String email = loginemail.getText().toString().trim();
                String pass = loginpass.getText().toString().trim();


                if (TextUtils.isEmpty(email)) {
                    loginemail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    loginpass.setError("Password is required");
                    return;
                }
                if (pass.length() < 6) {
                    loginpass.setError("Password must be atleast 6 character long ");
                    return;
                }


                progressBarLogin.setVisibility(view.VISIBLE);

                fAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else{
                            Toast.makeText(Login.this, "Invalid Credentials"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBarLogin.setVisibility(view.GONE);
                        }





                    }
                });

            }
        });


        signuptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Register.class));

            }
        });

       forgot.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               final EditText resetMail = new EditText(view.getContext());
               AlertDialog.Builder passresetdialogue = new AlertDialog.Builder(view.getContext());
               passresetdialogue.setTitle("Reset Password?");
               passresetdialogue.setMessage("Enter your Email to receive reset password link");
               passresetdialogue.setView(resetMail);

               passresetdialogue.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                   String mail = resetMail.getText().toString().trim();
                   fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                       @Override
                       public void onSuccess(Void aVoid) {
                           Toast.makeText(Login.this, "link sent successfully", Toast.LENGTH_SHORT).show();
                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           Toast.makeText(Login.this, "oops! link not sent "+e.getMessage(), Toast.LENGTH_SHORT).show();
                       }
                   });
                   }
               });

               passresetdialogue.setNegativeButton("No", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                   }
               }) ;

                passresetdialogue.create().show();


           }
       });


    }

}
