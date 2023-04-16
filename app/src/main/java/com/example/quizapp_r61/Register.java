package com.example.quizapp_r61;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    EditText etMail, etPassword;
    Button bRegister;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mUser = mAuth.getCurrentUser();
        if(mUser != null){
            Intent intent=new Intent(Register.this,MainActivity.class);

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etMail=(EditText) findViewById(R.id.etMail);
        etPassword=(EditText) findViewById(R.id.etPassword);
        bRegister=(Button)findViewById(R.id.bRegister);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();


        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail=etMail.getText().toString();
                String password=etPassword.getText().toString();
                //String password1=etPassword1.getText().toString();
                if(mail.isEmpty()){
                    Toast.makeText(Register.this,"Please fill in the required fields",Toast.LENGTH_SHORT).show();

                }
                else if(password.isEmpty()){
                    Toast.makeText(Register.this,"Please fill in the required fields",Toast.LENGTH_SHORT).show();

                }else if(password.length()<6){
                    Toast.makeText(Register.this,"Password must be at least 6 characters",Toast.LENGTH_SHORT).show();

                }else {
                    //progressDialog.setMessage("Please wait ....");
                    mAuth.createUserWithEmailAndPassword(mail, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(),"Registration Successful!  inscription réussi! التسجيل ناجح! 註冊成功  ",Toast.LENGTH_LONG).show();

                                        Intent intent=new Intent(Register.this,Quiz1.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        //startActivity(new Intent(Register.this,MainActivity.class));
                                        //finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Register.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }



            }
               /* if(TextUtils.isEmpty(password1)){
                    Toast.makeText(getApplicationContext(),"Please confirm your password",Toast.LENGTH_SHORT).show();
                    return;
                }*/

               /* if(!password.equals(password1)){
                    Toast.makeText(getApplicationContext(),"Please enter correct password",Toast.LENGTH_SHORT).show();
                    return;
                }*/

                //Commen.login=Mail;
                //Commen.password=password;

        });
    }
}

