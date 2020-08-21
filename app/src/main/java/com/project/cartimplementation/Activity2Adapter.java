package com.project.cartimplementation;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Activity2Adapter extends RecyclerView.Adapter<Activity2Adapter.InnerClass>{// first we need a constructor

    List<CartItems> cartItemsOnRAM;
    ButtonClickedInterface buttonClickedInterface;

    public Activity2Adapter(List<CartItems> cartItemsOnRAM, Context context) {
        this.cartItemsOnRAM=cartItemsOnRAM;
        buttonClickedInterface= (ButtonClickedInterface) context;
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
        Button plus;
        Button minus;

        public InnerClass(@NonNull View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.cart_name);
            tv_price=itemView.findViewById(R.id.cart_price);
            tv_count=itemView.findViewById(R.id.cart_count);
            tv_cart_total=itemView.findViewById(R.id.cart_total);
            plus=itemView.findViewById(R.id.cart_plus);
            minus=itemView.findViewById(R.id.cart_minus);

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    int value=cartItemsOnRAM.get(position).getItemCount();

                    buttonClickedInterface.onItemClicked(cartItemsOnRAM.get(position).getDishName(),cartItemsOnRAM.get(position).getDishPrice(),cartItemsOnRAM.get(position).getItemCount(),true);
                    tv_count.setText(String.valueOf(value+1));

                    cartItemsOnRAM.get(position).setItemCount(value+1);
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    int value=cartItemsOnRAM.get(position).getItemCount();
                    if (value>0){

                        tv_count.setText(String.valueOf(value-1));
                        buttonClickedInterface.onItemClicked(cartItemsOnRAM.get(position).getDishName(),cartItemsOnRAM.get(position).getDishPrice(),cartItemsOnRAM.get(position).getItemCount(),false);

                        cartItemsOnRAM.get(position).setItemCount(value-1);
                    }
                }
            });


        }

    }

    public interface ButtonClickedInterface {
        void onItemClicked(String dishName,int price,int count, boolean add);
    }

}
