package com.project.cartimplementation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShowCart extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView gTot;
    Activity2Adapter adapter;
    List<CartItems> cartItemsOnRAM = new ArrayList<>();
    MyHelper helper;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cart);

        helper = new MyHelper(this);
        database = helper.getWritableDatabase();
        int grandTotal=0;


            Cursor cursor = database.rawQuery("SELECT NAME, PRICE, COUNT FROM CART", new String[]{});
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(0);
                    int price = cursor.getInt(1);
                    int count = cursor.getInt(2);
                    grandTotal+=price*count;
                    cartItemsOnRAM.add(new CartItems(name, price, count));

                    Log.d("MyTag", "Activity 2"+name + " " + price + " " + count);
                } while (cursor.moveToNext());
            }


        recyclerView=findViewById(R.id.cartItemList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter=new Activity2Adapter(cartItemsOnRAM);
        recyclerView.setAdapter(adapter);

        gTot=findViewById(R.id.grandTotal);
        gTot.setText("Grand Total ₹"+String.valueOf(grandTotal));


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}