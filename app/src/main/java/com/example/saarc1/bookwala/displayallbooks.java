package com.example.saarc1.bookwala;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class displayallbooks extends AppCompatActivity implements View.OnClickListener {

    DatabaseReference databaseReference;

    public static final String USER_ID = "userid";

    public static final String BOOK_NAME = "bookname";

    public static final String BOOK_AUTHOR = "bookauthor";

    public static final String BOOK_PRICE = "bookprice";

    public static final String BOOK_ID = "bookid";

    private ImageView cart_nav;


    ListView listViewBook;

    ImageView imageView;

    private List<Book> bookList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayallbooks);

        cart_nav = findViewById(R.id.cart_nav);
        listViewBook = findViewById(R.id.listBooks);

        listViewBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             Book book = bookList.get(i);
                Intent intent = new Intent(getApplicationContext(),bookdescription.class);

                intent.putExtra(USER_ID, book.getUserId());
                intent.putExtra(BOOK_NAME, book.getBookName());
                intent.putExtra(BOOK_AUTHOR, book.getBookAuthor());
                intent.putExtra(BOOK_PRICE, book.getBookPrice());
                intent.putExtra(BOOK_ID, book.getBookId());

                startActivity(intent);
            }
        });

        cart_nav.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == cart_nav){
           startActivity(new Intent(this,Cart.class));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();


        databaseReference = FirebaseDatabase.getInstance().getReference().child("book").child("Engineering").child("branch").child("Ccycle");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                bookList.clear();
                for (DataSnapshot bookSnapshot : dataSnapshot.getChildren()){
                    Book book = bookSnapshot.getValue(Book.class);

                    bookList.add(book);
                }

                BookList adapter = new BookList(displayallbooks.this, bookList);
                listViewBook.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
