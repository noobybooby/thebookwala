package com.example.saarc1.bookwala;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class cartList extends ArrayAdapter<cartAdapter> {

   private Activity context;
   private List<cartAdapter> cartList;

   public cartList(Activity context, List<cartAdapter> cartList){

        super(context,R.layout.cart_item,cartList);
        this.context=context;
        this.cartList = cartList;
        }

     @NonNull
     @Override
     public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.cart_item,null,true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.cart_item_name);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.cart_item_price);
        TextView textViewAuthor = (TextView) listViewItem.findViewById(R.id.cart_item_author);
         TextView textViewQuantity = (TextView) listViewItem.findViewById(R.id.qty);
        TextView textViewTprice = (TextView) listViewItem.findViewById(R.id.Tprice);


        cartAdapter cartadapter = cartList.get(position);
        textViewName.setText(cartadapter.getBookName());
        textViewPrice.setText(cartadapter.getBookPrice());
        textViewAuthor.setText(cartadapter.getBookAuthor());
         textViewQuantity.setText(cartadapter.getQuantity());
         textViewTprice.setText(cartadapter.getTotalPrice());


        return listViewItem;
        }

}
