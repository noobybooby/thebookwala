package com.example.saarc1.bookwala;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    private EditText Email;
    private EditText pass;
    private Button registerButton;
    private TextView existing;
    private EditText userName;
    private EditText phoneNo;
    private DatabaseReference databaseReference;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        Email = findViewById(R.id.Email);
        pass = findViewById(R.id.pass);
        registerButton = findViewById(R.id.registerButton);
        existing = findViewById(R.id.existing);
        userName = findViewById(R.id.userName);
        phoneNo = findViewById(R.id.phoneNo);
        registerButton.setOnClickListener(this);
        existing.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(),address_get.class));
        }

    }
    private void saveUserInformation(){
        String email = Email.getText().toString().trim();
        String User = userName.getText().toString().trim();
        String phoneno = phoneNo.getText().toString().trim();

        userinfo userInfo = new userinfo(User,phoneno,email);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userInfo);
    }




    @Override
    public void onClick(View v) {

        if (v == registerButton){
            registerUser();
        }
        if (v == existing){
            startActivity(new Intent(this, Login.class));
        }


    }


    private void registerUser() {
        String email = Email.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String username = userName.getText().toString().trim();
        String phone_number = phoneNo.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter your email",Toast.LENGTH_SHORT);
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter your pasword",Toast.LENGTH_SHORT);
            return;
        }
        if (TextUtils.isEmpty(phone_number)) {
            Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT);
            return;
        }
        if (TextUtils.isEmpty(username)){
            Toast.makeText(this,"Please enter your email",Toast.LENGTH_SHORT);
            return;
        }
        progressDialog.setMessage("Registering user......");
        progressDialog.show();
               firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Registration.this,"You are now registered",Toast.LENGTH_SHORT);
                                startActivity(new Intent(getApplicationContext(),address_get.class));

                            }else
                                {
                                Toast.makeText(Registration.this,"Could not register",Toast.LENGTH_SHORT);
                            }
                            saveUserInformation();
                            progressDialog.dismiss();
                    }
                });

    }
}