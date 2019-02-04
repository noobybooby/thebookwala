package com.example.saarc1.bookwala;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BookList extends ArrayAdapter<Book> {

    private Activity context;
    private List<Book> bookList;

    public BookList(Activity context, List<Book> bookList){

        super(context,R.layout.book_item, bookList);
        this.context = context;
        this.bookList = bookList;

    }

    @NonNull
     @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.book_item,null,true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.book_name);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.book_price);
        TextView textViewAuthor = (TextView) listViewItem.findViewById(R.id.book_author);

        Book book = bookList.get(position);
        textViewName.setText(book.getBookName());
        textViewPrice.setText(book.getBookPrice());
        textViewAuthor.setText(book.getBookAuthor());

        return listViewItem;
    }
}
