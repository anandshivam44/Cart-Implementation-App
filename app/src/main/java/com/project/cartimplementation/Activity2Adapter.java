package com.project.cartimplementation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Activity2Adapter extends RecyclerView.Adapter<Activity2Adapter.InnerClass>{// first we need a constructor

    List<CartItems> cartItemsOnRAM;

    public Activity2Adapter(List<CartItems> cartItemsOnRAM) {
        this.cartItemsOnRAM=cartItemsOnRAM;
    }





    @NonNull
    @Override
    public InnerClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerClass(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InnerClass holder, int position) {
        holder.tv_name.setText(cartItemsOnRAM.get(position).getDishName());
        holder.tv_price.setText(String.valueOf(cartItemsOnRAM.get(position).getDishPrice()));
        holder.tv_count.setText(String.valueOf(cartItemsOnRAM.get(position).getItemCount()));
        holder.tv_cart_total.setText(String.valueOf(cartItemsOnRAM.get(position).getDishPrice()*cartItemsOnRAM.get(position).getItemCount()));
    }

    @Override
    public int getItemCount() {
        return cartItemsOnRAM.size();
    }


    public class InnerClass extends RecyclerView.ViewHolder{

        TextView tv_name;
        TextView tv_price;
        TextView tv_count;
        TextView tv_cart_total;

        public InnerClass(@NonNull View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.cart_name);
            tv_price=itemView.findViewById(R.id.cart_price);
            tv_count=itemView.findViewById(R.id.cart_count);
            tv_cart_total=itemView.findViewById(R.id.cart_total);

        }

    }
}
