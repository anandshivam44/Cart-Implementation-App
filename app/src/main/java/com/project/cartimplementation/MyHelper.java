package com.project.cartimplementation;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyHelper extends SQLiteOpenHelper {

    private static final String dbname="mydb";
    private static final int version=1;

    public MyHelper(@Nullable Context context) {
        super(context, dbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql="CREATE TABLE CART (_id INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,PRICE INTEGER,COUNT INTEGER)";
        sqLiteDatabase.execSQL(sql);


    }

    public static void insertData(String name,int price,int count,SQLiteDatabase database){
        ContentValues values=new ContentValues();
        values.put("NAME",name);
        values.put("PRICE",price);
        values.put("COUNT",count);
        database.insert("CART",null,values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}


//        insertData("Item A",36,sqLiteDatabase);
//        insertData("Item B",66,sqLiteDatabase);
//        insertData("Item C",636,sqLiteDatabase);

//        insertData("Dish 1",26,sqLiteDatabase);
//        insertData("Dish 2",300,sqLiteDatabase);
//        insertData("Dish 3",100,sqLiteDatabase);
//        insertData("Dish 4",120,sqLiteDatabase);
//        insertData("Dish 5",50,sqLiteDatabase);
//        insertData("Dish 6",250,sqLiteDatabase);
//        insertData("Dish 7",200,sqLiteDatabase);
//        insertData("Dish 8",350,sqLiteDatabase);
//        insertData("Dish 9",10,sqLiteDatabase);
//        insertData("Dish 10",60,sqLiteDatabase);
//        insertData("Dish 11",99,sqLiteDatabase);
//        insertData("Dish 12",199,sqLiteDatabase);
//        insertData("Dish 13",299,sqLiteDatabase);
//        insertData("Dish 14",399,sqLiteDatabase);
//        insertData("Dish 15",499,sqLiteDatabase);
//        insertData("Dish 16",500,sqLiteDatabase);
//        insertData("Dish 17",555,sqLiteDatabase);
//        insertData("Dish 18",55,sqLiteDatabase);
//        insertData("Dish 19",111,sqLiteDatabase);
//        insertData("Dish 20",222,sqLiteDatabase);
