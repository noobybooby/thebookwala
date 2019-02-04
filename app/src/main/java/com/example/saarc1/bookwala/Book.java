package com.example.saarc1.bookwala;

import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;

public class Book {

    private String userId;
    private String bookId;
    private String bookName;
    private String bookPrice;
    private String bookAuthor;
    private String totalPrice;
    private String quantity;


    public Book(){

    }

    public Book(String userId ,String bookName, String bookPrice, String bookAuthor, String bookId,String totalPrice, String quantity) {
        this.userId = userId;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.bookAuthor = bookAuthor;
        this.bookId = bookId;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
    }

    public String getUserId() { return userId; }

    public String getBookId() { return bookId; }

    public String getBookName() { return bookName; }

    public String getBookPrice() { return bookPrice; }

    public String getBookAuthor() {
        return bookAuthor;
    }


}
