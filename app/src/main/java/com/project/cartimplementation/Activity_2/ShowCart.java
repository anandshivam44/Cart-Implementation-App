package com.project.cartimplementation.Activity_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.project.cartimplementation.Activity_1.MainActivity;
import com.project.cartimplementation.MyHelper;
import com.project.cartimplementation.R;

import java.util.ArrayList;
import java.util.List;

public class ShowCart extends AppCompatActivity implements Activity2Adapter.ButtonClickedInterface {

    RecyclerView recyclerView;
    TextView gTot;
    Activity2Adapter adapter;
    List<Activity2Adapter.CartItems> cartItemsOnRAM = new ArrayList<>();
    MyHelper helper;
    SQLiteDatabase database;
    Button Pay;
    int grandTotal=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cart);

        helper = new MyHelper(this);
        database = helper.getWritableDatabase();



            Cursor cursor = database.rawQuery("SELECT NAME, PRICE, COUNT FROM CART", new String[]{});
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(0);
                    int price = cursor.getInt(1);
                    int count = cursor.getInt(2);
                    grandTotal+=price*count;
                    cartItemsOnRAM.add(new Activity2Adapter.CartItems(name, price, count));

                    Log.d("MyTag", "Activity 2"+name + " " + price + " " + count);
                } while (cursor.moveToNext());
            }


        recyclerView=findViewById(R.id.cartItemList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter=new Activity2Adapter(cartItemsOnRAM,this);
        recyclerView.setAdapter(adapter);
        Pay=findViewById(R.id.pay);

//        gTot=findViewById(R.id.grandTotal);
        Pay.setText("Pay $"+String.valueOf(grandTotal));


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(ShowCart.this, MainActivity.class);
//                        intent.putExtra("count",sharedPref.getInt("count",0));
        startActivity(intent);
        finishActivity(0);
    }

    @Override
    public void onItemClicked(String dishName, int price,int count, boolean add) {

        if (add){
            updatetData(dishName,price,count+1,database);
            grandTotal+=price;
            Pay.setText("Pay $"+String.valueOf(grandTotal));
        }
        else {
            grandTotal-=price;
            Pay.setText("Pay $"+String.valueOf(grandTotal));
            if (count==1){
                deleteData(dishName,database);
            }
            else {
                updatetData(dishName, price, count - 1, database);
            }
        }

//        Cursor cursor = database.rawQuery("SELECT NAME, PRICE, COUNT FROM CART", new String[]{});
//        if (cursor != null && cursor.moveToFirst()) {
//
//            do {
//                String name = cursor.getString(0);
//                int pp = cursor.getInt(1);
//                int cc = cursor.getInt(2);
//                Log.d("MyTag", "clicked "+name + " " + pp + " " + cc);
//            } while (cursor.moveToNext());
//        }

    }

    public static void updatetData(String name,int price,int count,SQLiteDatabase database){
        ContentValues values=new ContentValues();
        values.put("NAME",name);
        values.put("COUNT",count);
        database.update("CART",values,"NAME = ?",new String[]{name});
    }
    public static void deleteData(String name,SQLiteDatabase database){
        database.delete("CART","NAME = ?",new String[]{name});
    }
}