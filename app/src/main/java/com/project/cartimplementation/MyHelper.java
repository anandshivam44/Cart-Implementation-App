package com.project.cartimplementation;

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


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
