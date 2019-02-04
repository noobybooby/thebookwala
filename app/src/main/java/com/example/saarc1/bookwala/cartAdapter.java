package com.example.saarc1.bookwala;

public class cartAdapter {
    private String bookId;
    private String bookName;
    private String bookPrice;
    private String bookAuthor;
    private String quantity;
    private String totalPrice;



    public cartAdapter() {

    }

    public cartAdapter(String bookId, String bookName, String bookPrice, String bookAuthor,String Quantity,String totalPrice) {

        this.bookId = bookId;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.bookAuthor = bookAuthor;
        this.quantity =  Quantity;
        this.totalPrice = totalPrice;



    }


    public String getBookId(){ return bookId;}

    public String getBookName() {
        return bookName;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getQuantity() { return quantity; }

    public String getTotalPrice() {
        return totalPrice;
    }


}
