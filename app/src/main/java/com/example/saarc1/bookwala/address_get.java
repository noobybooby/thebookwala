package com.example.saarc1.bookwala;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class address_get extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private EditText address;
    private EditText landmark;
    private FirebaseAuth firebaseAuth;
    private Button btn_proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_get);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        address = findViewById(R.id.editTextAddress);
        landmark = findViewById(R.id.editTextLandmark);
        btn_proceed = findViewById(R.id.btn_proceed);
        btn_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();
                Intent intent = new Intent(address_get.this, displayallbooks.class);
                startActivity(intent);
            }
        });


    }

    private void saveUserInformation(){
        String add = address.getText().toString().trim();
        String lmark = landmark.getText().toString().trim();

        addtn_info userInfo = new addtn_info(add,lmark);
        String users = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference.child(users).child("address").setValue(userInfo);
    }
}
