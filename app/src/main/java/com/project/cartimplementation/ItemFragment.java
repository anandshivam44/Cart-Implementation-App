package com.project.cartimplementation;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class ItemFragment extends Fragment {

    List<CartItems> dishDetails;
//    List<Integer> dishPrice;
    MyHelper helper;
    SQLiteDatabase database;

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    public ItemFragment() {
    }

    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        dishDetails =new ArrayList<>();

        dishDetails.add(new CartItems("Dish 1",1,0));
        dishDetails.add(new CartItems("Dish 2",2,0));
        dishDetails.add(new CartItems("Dish 3",3,0));
        dishDetails.add(new CartItems("Dish 4",4,0));
        dishDetails.add(new CartItems("Dish 5",5,0));
        dishDetails.add(new CartItems("Dish 6",6,0));
        dishDetails.add(new CartItems("Dish 7",7,0));
        dishDetails.add(new CartItems("Dish 8",8,0));
        dishDetails.add(new CartItems("Dish 9",9,0));
        dishDetails.add(new CartItems("Dish 10",10,0));

        helper = new MyHelper(getContext());
        database = helper.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT NAME,  COUNT FROM CART", new String[]{});
        if (cursor != null && cursor.moveToFirst()) {
//            cursor.moveToFirst();
            do {
                String name = cursor.getString(0);
                for (int i=0;i<dishDetails.size();i++){
                    if (name.equals(dishDetails.get(i).getDishName())){
                        int count = cursor.getInt(1);
                        dishDetails.get(i).setItemCount(count);
                    }
                }
            } while (cursor.moveToNext());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(dishDetails,getContext()));
        }
        return view;
    }


}