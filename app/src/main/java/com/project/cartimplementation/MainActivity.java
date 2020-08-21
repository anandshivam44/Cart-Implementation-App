package com.project.cartimplementation;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.project.cartimplementation.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyItemRecyclerViewAdapter.itemClickedInterface {

    TextView tv_price;
    TextView tv_itemCount;
    int intprice = 0;
    int noOfItems=0;
    MyHelper helper;
    SQLiteDatabase database;
    List<CartItems> cartItemsOnRAM = new ArrayList<>();
    Button goToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        tv_price = findViewById(R.id.cartPrice);
        goToCart=findViewById(R.id.go_to_cart);
        goToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ShowCart.class);
//                        intent.putExtra("count",sharedPref.getInt("count",0));
                startActivity(intent);

            }
        });

        helper = new MyHelper(this);
        database = helper.getWritableDatabase();

            Cursor cursor = database.rawQuery("SELECT NAME, PRICE, COUNT FROM CART", new String[]{});
            if (cursor != null && cursor.moveToFirst()) {

                do {
                    String name = cursor.getString(0);
                    int price = cursor.getInt(1);
                    int count = cursor.getInt(2);
                    cartItemsOnRAM.add(new CartItems(name, price, count));
                    intprice+=count*price;
                    noOfItems++;

                    Log.d("MyTag", "Already on databse"+name + " " + price + " " + count);
                } while (cursor.moveToNext());
            }
            tv_price.setText("₹"+String.valueOf(intprice));
            tv_itemCount=findViewById(R.id.item_count);
            tv_itemCount.setText(String.valueOf(noOfItems)+" Items");

//        }




    }

    @Override
    public void onItemClicked(String dishName, int price, boolean add) {
        if (add) {// add item
            intprice += price;
            tv_price.setText("₹"+String.valueOf(intprice));
            boolean found=false;

            for (int i = 0; i < cartItemsOnRAM.size(); i++) {
//                Log.d("MyTag", cartItemsOnRAM.get(i).dishName +" "+ dishName);
                if (dishName.equals(cartItemsOnRAM.get(i).getDishName())) {//item already available in database
//                    Log.d("MyTag","compare found "+ cartItemsOnRAM.get(i).dishName +" "+ dishName);
                    cartItemsOnRAM.get(i).setItemCount(cartItemsOnRAM.get(i).getItemCount() + 1);// increase it
                    updatetData(dishName,price,cartItemsOnRAM.get(i).getItemCount(),database);
                    found=true;
                }

            }
            if (found==false){//item not found in database so add it to database
                noOfItems++;
                cartItemsOnRAM.add(new CartItems(dishName,price,1));
                insertData(dishName,price,1,database);
                tv_itemCount.setText(String.valueOf(noOfItems)+" Items");
            }
        }
        else {
            intprice -= price;
            tv_price.setText("₹"+String.valueOf(intprice));
            boolean found=false;
            for (int i = 0; i < cartItemsOnRAM.size(); i++) {
                if (dishName.equals(cartItemsOnRAM.get(i).getDishName())) {// item found
                    if (cartItemsOnRAM.get(i).getItemCount() == 1) {// check if its count is 1 then delete it because it will become 0
                        cartItemsOnRAM.remove(i);
                        deleteData(dishName,database);
                        noOfItems--;
                        tv_itemCount.setText(String.valueOf(noOfItems)+" Items");
                    } else if(cartItemsOnRAM.get(i).getItemCount()>1) { // if item count is greater than 1 then reduce it by 1
                        cartItemsOnRAM.get(i).setItemCount(cartItemsOnRAM.get(i).getItemCount() -1);//update
                        updatetData(dishName,price,cartItemsOnRAM.get(i).getItemCount(),database);

                    }

                }

            }
        }

//        Cursor cursor = database.rawQuery("SELECT NAME, PRICE, COUNT FROM CART", new String[]{});
//        if (cursor != null) {
//            cursor.moveToFirst();
//            do {
//                String name = cursor.getString(0);
//                int pp = cursor.getInt(1);
//                int count = cursor.getInt(2);
////                Log.d("MyTag", "clicked "+name + " " + pp + " " + count);
//            } while (cursor.moveToNext());
//        }

    }

    public static void insertData(String name,int price,int count,SQLiteDatabase database){
        ContentValues values=new ContentValues();
        values.put("NAME",name);
        values.put("PRICE",price);
        values.put("COUNT",count);
        database.insert("CART",null,values);
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