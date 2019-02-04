package com.example.saarc1.bookwala;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPickerListener;


public class bookdescription extends AppCompatActivity {

    TextView book_desc_name;
    TextView book_desc_author;
    TextView book_desc_price;

    int Quantity;
    int TPrice;
    int pRice;



    Button add_to_cart;
    DatabaseReference databaseCart;
    FirebaseAuth user;

    ScrollableNumberPicker scrollableNumberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookdescription);

        scrollableNumberPicker = findViewById(R.id.number_picker_horizontal);



        add_to_cart = findViewById(R.id.add_to_cart);

        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtocart();
            }
        });

        book_desc_name = findViewById(R.id.book_desc_name);
        book_desc_author = findViewById(R.id.book_desc_author);
        book_desc_price = findViewById(R.id.book_desc_Price);


        Intent intent = getIntent();

        String name = intent.getStringExtra(displayallbooks.BOOK_NAME);
        String author = intent.getStringExtra(displayallbooks.BOOK_AUTHOR);
        String price = intent.getStringExtra(displayallbooks.BOOK_PRICE);


        book_desc_name.setText(name);
        book_desc_author.setText(author);
        book_desc_price.setText(price);

        pRice = Integer.parseInt(price);

        scrollableNumberPicker.setListener(new ScrollableNumberPickerListener() {
            @Override
            public void onNumberPicked(int value) {
                Quantity = scrollableNumberPicker.getValue() ;
                TPrice = Quantity * pRice;
            }
        });





        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String item_id = intent.getStringExtra(displayallbooks.BOOK_ID);

        databaseCart  = FirebaseDatabase.getInstance().getReference("shoppingCart").child(user).child(item_id);


    }

    private void addtocart() {
        String bookName = book_desc_name.getText().toString().trim();
        String bookPrice = book_desc_price.getText().toString().trim();
        String bookAuthor = book_desc_author.getText().toString().trim();
        String quantity = Integer.toString(Quantity).trim();
        String totalPrice =  Integer.toString(TPrice).trim();




        String id = databaseCart.push().getKey();

        cartAdapter cart = new cartAdapter(id,bookName,bookPrice,bookAuthor,quantity,totalPrice);

        databaseCart.setValue(cart);

        Toast.makeText(this,"Added to cart",Toast.LENGTH_LONG).show();

    }

}
