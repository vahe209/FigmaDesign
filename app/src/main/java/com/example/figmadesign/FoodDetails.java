package com.example.figmadesign;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.figmadesign.Adapter.MainFoodAdapter;

public class FoodDetails extends Fragment implements MainFoodAdapter.Example1 {
    private MainFoodAdapter.Example1 example1;
    private MainFoodAdapter.Example2 example2;
    private int count, img;
    private float priceOfFood;
    private String nameOfFood;
    private ImageView imageView;
    private TextView name, price, countOfFood;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_details, container, false);
        count = 0;
        ImageView plus, minus;


        name = view.findViewById(R.id.details_name);
        price = view.findViewById(R.id.details_price);
        imageView = view.findViewById(R.id.details_image);
        updateData();
        name.setText(nameOfFood);
        price.setText(String.valueOf("$ " + priceOfFood));
        imageView.setImageResource(img);


        plus = view.findViewById(R.id.plus);
        minus = view.findViewById(R.id.minus);
        countOfFood = view.findViewById(R.id.countOfFood);


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count < 10) {
                    count++;
                    countOfFood.setText(String.valueOf(count));

                    example1.passDataToMainActivity(count, priceOfFood);


                }
                if (count == 10) {
                    Toast toast = Toast.makeText(getContext(), "You can order only less than 10 items", Toast.LENGTH_SHORT);
                    toast.show();
                }
                countOfFood.setText(String.valueOf(count));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 0) {
                    count--;
                    example1.passDataToMainActivity(-count, -priceOfFood);
                    countOfFood.setText(String.valueOf(count));


                }
                //countOfFood.setText(String.valueOf(count));
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        example1 = (MainFoodAdapter.Example1) context;
        example2 = (MainFoodAdapter.Example2) context;
    }


    @Override
    public void passDataToMainActivity(int counter, float price) {
    }


    public void updateData() {
        Bundle bundle = getArguments();
        nameOfFood = bundle.getString("name");
        priceOfFood = bundle.getFloat("priceOfFood");
        img = bundle.getInt("imageUrl");
    }


}