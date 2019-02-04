package com.example.saarc1.bookwala;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button buttonLogin;
    private EditText loginEmail;
    private EditText loginPassword;
    private TextView nonExisting;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null) {
            //start profile
            finish();
            startActivity(new Intent(getApplicationContext(), categories.class));
        }
        progressDialog = new ProgressDialog(this);
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPasssword);
        buttonLogin = findViewById(R.id.buttonLogin);
        nonExisting = findViewById(R.id.nonExisting);
        buttonLogin.setOnClickListener(this);
        nonExisting.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if(view == buttonLogin){
            login();
        }
        if(view == nonExisting){
        startActivity(new Intent(this,Registration.class));
        }
    }
    private void login(){
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //email not entered by user
            Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show();
            //stop further execution
            return;
        }
        if (TextUtils.isEmpty(password)) {
            //password not entered by user
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            //stop further execution
            return;
        }
        //after validations
        //show progress bar
        progressDialog.setMessage("Please wait........");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            //start profile
                            finish();
                            startActivity(new Intent(getApplicationContext(),categories.class));

                        }

                    }
                });
    }
}
