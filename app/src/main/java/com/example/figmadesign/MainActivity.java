package com.example.figmadesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.figmadesign.Adapter.MainFoodAdapter;


public class MainActivity extends AppCompatActivity implements MainFoodAdapter.Example1, MainFoodAdapter.Example2 {
    private MainFragment mainFragment = new MainFragment();
    private LovelyFoodFragment lovelyFoodFragment = new LovelyFoodFragment();
    private BuyFragment buyFragment = new BuyFragment();
    private DoNotKnowFragment doNotKnowFragment = new DoNotKnowFragment();
    private FoodDetails foodDetails = new FoodDetails();
    private TextView countView, priceView;
    Bundle bundle = new Bundle();

    private ImageView lovelyFragmentButton, mainFragmentButton, doNotKnowFragmentButton, buyFragmentButton, deleteButton;
    private int localCount;
    private float money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countView = findViewById(R.id.countView);
        priceView = findViewById(R.id.priceView);
        mainFragmentButton = findViewById(R.id.mainPageIcon);

        CreateFragment(mainFragment);


        mainFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateFragment(mainFragment);
                mainFragmentButton.setBackgroundResource(R.color.activeFragmentIconBg);
                lovelyFragmentButton.setBackgroundResource(R.color.white);
                doNotKnowFragmentButton.setBackgroundResource(R.color.white);
                buyFragmentButton.setBackgroundResource(R.color.white);

            }
        });

        lovelyFragmentButton = findViewById(R.id.likedItemsPageIcon);
        lovelyFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateFragment(lovelyFoodFragment);
                mainFragmentButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
                lovelyFragmentButton.setBackgroundResource(R.color.activeFragmentIconBg);
                doNotKnowFragmentButton.setBackgroundResource(R.color.white);
                buyFragmentButton.setBackgroundResource(R.color.white);


            }
        });
        doNotKnowFragmentButton = findViewById(R.id.doNotKnowPageIcon);
        doNotKnowFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateFragment(doNotKnowFragment);
                mainFragmentButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
                lovelyFragmentButton.setBackgroundResource(R.color.white);
                doNotKnowFragmentButton.setBackgroundResource(R.color.activeFragmentIconBg);
                buyFragmentButton.setBackgroundResource(R.color.white);

            }
        });
        buyFragmentButton = findViewById(R.id.buyFragmentIcon);
        buyFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateFragment(buyFragment);
                buyFragmentButton.setBackgroundResource(R.color.activeFragmentIconBg);
                mainFragmentButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
                lovelyFragmentButton.setBackgroundResource(R.color.white);
                doNotKnowFragmentButton.setBackgroundResource(R.color.white);

            }
        });
        deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getBaseContext(), "This function will be add ", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("counter", localCount);
        editor.putFloat("price", money);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        localCount = sharedPreferences.getInt("counter", 0);
        money = sharedPreferences.getFloat("price", 1);
    }

    private void CreateFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_fragment_layout, fragment);
        ft.commit();
    }




    @Override
    public void passDataToMainActivity(int counter, float price) {
        loadData();
        money += price;
        if (counter > 0) {
            countView.setText(++localCount + " items");
        } else if (counter <= 0) {
            countView.setText(--localCount + " items");
        }
        priceView.setText("$ " + money);
        saveData();
        Bundle bundle = new Bundle();
        bundle.putInt("counter", counter);
        bundle.putFloat("price", price);
    }

    @Override
    public void createFragment(String name, float price, int imageUrl) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        bundle.putString("name", name);
        bundle.putFloat("priceOfFood", price);
        bundle.putInt("imageUrl", imageUrl);
        foodDetails.setArguments(bundle);
        ft.replace(R.id.main_fragment_layout, foodDetails).commit();

    }
    @Override
    protected void onResume() {
        super.onResume();
        saveData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        loadData();

    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("counter");
        editor.remove("price");
        editor.apply();
    }

}




