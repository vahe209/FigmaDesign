package com.example.figmadesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;

public class WelcomePage extends AppCompatActivity {
    AppCompatButton firstPageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page_activity);
        firstPageBtn = findViewById(R.id.firstPageBtn);
        firstPageBtn.setOnClickListener(v -> loadMainActivity());
    }

    private void loadMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


}