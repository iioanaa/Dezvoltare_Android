package com.example.flowershop;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.flowershop.model.ShopModel;


public class OrderSucceessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_succeess);


        ShopModel shopmodel = getIntent().getParcelableExtra("ShopModel");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(shopmodel.getName());
        actionBar.setSubtitle(shopmodel.getAddress());
        actionBar.setDisplayHomeAsUpEnabled(false);


        TextView buttonDone = findViewById(R.id.buttonDone);
        buttonDone.setOnClickListener(v -> finish());
    }
}