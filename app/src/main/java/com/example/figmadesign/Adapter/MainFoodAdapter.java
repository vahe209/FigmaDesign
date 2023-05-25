package com.example.figmadesign.Adapter;

import android.annotation.SuppressLint;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.figmadesign.R;
import com.example.figmadesign.model.MainFood;

import java.util.List;

public class MainFoodAdapter extends RecyclerView.Adapter<MainFoodAdapter.MainFoodViewHolder> {
    private List<MainFood> mainFoodList;
    private Example2 example2;

    public MainFoodAdapter(Example1 context1, Example2 context2, List<MainFood> mainFoodList) {
        this.example2 = (Example2) context2;
        this.mainFoodList = mainFoodList;
    }


    @NonNull
    @Override
    public MainFoodAdapter.MainFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_food_item, parent, false);
        return new MainFoodViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MainFoodAdapter.MainFoodViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.card_image.setImageResource(mainFoodList.get(position).getImageUrl());
        holder.card_food_name.setText(mainFoodList.get(position).getName());
        holder.Card_food_price.setText("$ " + mainFoodList.get(position).getPrice());


        holder.lovely_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.lovely_food.setImageResource(R.drawable.loved);
            }
        });
        holder.lovely_food.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                holder.lovely_food.setImageResource(R.drawable.lovely);
                return true;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                example2.createFragment(mainFoodList.get(position).getName(), Float.parseFloat(mainFoodList.get(position).getPrice()), mainFoodList.get(position).getImageUrl());

            }
        });
    }

    @Override
    public int getItemCount() {
        return mainFoodList.size();
    }

    public static class MainFoodViewHolder extends RecyclerView.ViewHolder {
      private   ImageView card_image, lovely_food;
       private TextView card_food_name, Card_food_price;

        public MainFoodViewHolder(@NonNull View itemView) {
            super(itemView);
            card_food_name = itemView.findViewById(R.id.card_food_name);
            Card_food_price = itemView.findViewById(R.id.card_food_price);
            card_image = itemView.findViewById(R.id.card_image);
            lovely_food = itemView.findViewById(R.id.lovely_icon);
        }
    }

    public interface Example1 {
        void passDataToMainActivity(int counter, float price);

    }

    public interface Example2 {
        void createFragment(String name, float price, int imageUrl);
    }


}

