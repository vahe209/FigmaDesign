package com.example.figmadesign;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.figmadesign.Adapter.MainFoodAdapter;
import com.example.figmadesign.model.MainFood;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    List<MainFood> mainFoods;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainFoods = new ArrayList<>();
        mainFoods.add(new MainFood("Pizza", "15.00", R.drawable.card1));
        mainFoods.add(new MainFood("Vegetable Salad", "20.00", R.drawable.card2));
        mainFoods.add(new MainFood("Hamburger", "5.00", R.drawable.card3));
        mainFoods.add(new MainFood("Spaghetti Gabonese", "10.00", R.drawable.card4));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        initRecView(v);

        return v;
    }

    private void initRecView(View v) {
        RecyclerView recyclerView = v.findViewById(R.id.rv);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        MainFoodAdapter adapter = new MainFoodAdapter((MainFoodAdapter.Example1) getContext(), (MainFoodAdapter.Example2) getContext(), mainFoods);
        recyclerView.setAdapter(adapter);
    }

}




