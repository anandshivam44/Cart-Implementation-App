package com.project.cartimplementation.Activity_1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.cartimplementation.Activity_2.ShowCartRecyclerviewAdapter;
import com.project.cartimplementation.CartItems;
import com.project.cartimplementation.MyHelper;
import com.project.cartimplementation.R;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    public static String Titles[];
    List<CartItems> dishDetails;
    MyHelper helper;
    SQLiteDatabase database;
    static int pos=0;

    public ViewPagerFragment() {
    }

    public static ViewPagerFragment newInstance(int columnCount, String title[]) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        Titles = title;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String name="";

        if (getArguments() != null) {
            name= Titles[getArguments().getInt(ARG_COLUMN_COUNT)];
        }
        dishDetails = new ArrayList<>();

        for (int i=0;i<=20;i++){
            dishDetails.add(new CartItems(name + String.valueOf(i), i, 0));
        }

        dishDetails.add(new CartItems(name + " 1", 1, 0));
        dishDetails.add(new CartItems(name + " 2", 2, 0));
        dishDetails.add(new CartItems(name + " 3", 3, 0));
        dishDetails.add(new CartItems(name + " 4", 4, 0));
        dishDetails.add(new CartItems(name + " 5", 5, 0));
        dishDetails.add(new CartItems(name + " 6", 6, 0));
        dishDetails.add(new CartItems(name + " 7", 7, 0));
        dishDetails.add(new CartItems(name + " 8", 8, 0));
        dishDetails.add(new CartItems(name + " 9", 9, 0));
        dishDetails.add(new CartItems(name + " 10", 10, 0));

        helper = new MyHelper(getContext());
        database = helper.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT NAME,  COUNT FROM CART", new String[]{});
        if (cursor != null && cursor.moveToFirst()) {
//            cursor.moveToFirst();
            do {
                String nn = cursor.getString(0);
                for (int i = 0; i < dishDetails.size(); i++) {
                    if (nn.equals(dishDetails.get(i).getDishName())) {
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
            recyclerView.setAdapter(new RecyclerViewAdapter(dishDetails, getContext()));
        }
        return view;
    }


}