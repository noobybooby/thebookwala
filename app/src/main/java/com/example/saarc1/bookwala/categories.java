package com.example.saarc1.bookwala;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class categories extends AppCompatActivity {

    ImageView eng, med;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        eng = findViewById(R.id.cat_eng);

        eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(categories.this, displayallbooks.class);
                startActivity(intent);
            }
        });
    }
}
