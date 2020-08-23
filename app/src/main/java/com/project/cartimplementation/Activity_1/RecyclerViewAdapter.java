package com.project.cartimplementation.Activity_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.cartimplementation.Activity_2.ShowCartRecyclerviewAdapter;
import com.project.cartimplementation.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<ShowCartRecyclerviewAdapter.CartItems> dishDetails;
    itemClickedInterface itemClickedInterface;
    int cc;


    public RecyclerViewAdapter(List<ShowCartRecyclerviewAdapter.CartItems> dishDetails, Context context) {
        this.dishDetails = dishDetails;
        this.itemClickedInterface = (RecyclerViewAdapter.itemClickedInterface) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
//        holder.mItem = dishes.get(position);
        holder.dishName.setText(dishDetails.get(position).getDishName());
        holder.dishPrice.setText(" ₹ " + dishDetails.get(position).getDishPrice());
        cc = dishDetails.get(position).getItemCount();
        if (cc > 0) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
                    holder.count.getLayoutParams();
            params.weight = 1.0f;
            holder.count.setLayoutParams(params);

            holder.count.setText(String.valueOf(dishDetails.get(position).getItemCount()));

            LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) holder.removeDish.getLayoutParams();
            params2.weight = 1.0f;
            holder.removeDish.setLayoutParams(params2);

            holder.addDish.setText("+");
        }
    }

    @Override
    public int getItemCount() {
        return dishDetails.size();
    }

    public interface itemClickedInterface {
        void onItemClicked(String dishName, int price, boolean add);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //        public final View mView;
        TextView dishName;
        TextView dishPrice;
        Button addDish;
        Button removeDish;
        TextView count;
//        int cc=0;


        public ViewHolder(@NonNull View view) {
            super(view);

            dishName = (TextView) view.findViewById(R.id.dish_name);
            dishPrice = (TextView) view.findViewById(R.id.dish_price);
            addDish = view.findViewById(R.id.add_item);
            removeDish = view.findViewById(R.id.remove_item);
            count = view.findViewById(R.id.count);
//            cc=dishDetails.get(getAdapterPosition()).getItemCount();

            addDish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cc = dishDetails.get(getAdapterPosition()).getItemCount();

                    if (cc == 0) {
                        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams)
                                count.getLayoutParams();
                        params1.weight = 1.0f;
                        count.setLayoutParams(params1);

                        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) removeDish.getLayoutParams();
                        params2.weight = 1.0f;
                        removeDish.setLayoutParams(params2);

                        addDish.setText("+");


                        ++cc;
                        dishDetails.get(getAdapterPosition()).setItemCount(cc);
                        count.setText(String.valueOf(cc));

                    } else {
                        ++cc;
                        count.setText(String.valueOf(cc));
                        dishDetails.get(getAdapterPosition()).setItemCount(cc);
                    }
                    //sent old values no update till now
                    itemClickedInterface.onItemClicked(dishDetails.get(getAdapterPosition()).getDishName(), dishDetails.get(getAdapterPosition()).getDishPrice(), true);
                }
            });

            removeDish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cc = dishDetails.get(getAdapterPosition()).getItemCount();
//                    --cc;

                    if (cc == 1) {
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
                                count.getLayoutParams();
                        params.weight = 0.0f;
                        count.setLayoutParams(params);

                        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) removeDish.getLayoutParams();
                        params2.weight = 0.0f;
                        removeDish.setLayoutParams(params2);

                        addDish.setText("ADD");

                        count.setText("");
                        itemClickedInterface.onItemClicked(dishDetails.get(getAdapterPosition()).getDishName(), dishDetails.get(getAdapterPosition()).getDishPrice(), false);
                        --cc;
                        dishDetails.get(getAdapterPosition()).setItemCount(cc);
                    } else if (cc > 1) {
                        cc = cc - 1;
                        dishDetails.get(getAdapterPosition()).setItemCount(cc);

                        count.setText(String.valueOf(cc));
                        itemClickedInterface.onItemClicked(dishDetails.get(getAdapterPosition()).getDishName(), dishDetails.get(getAdapterPosition()).getDishPrice(), false);

                    }
                }
            });
        }


    }
}