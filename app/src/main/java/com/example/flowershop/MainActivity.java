package com.example.flowershop;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flowershop.adapters.ShopListAdapter;
import com.example.flowershop.model.ShopModel;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ShopListAdapter.ShopListClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Flower List");

        List<ShopModel> shopmodellist =  getShopData();

        initRecyclerView(shopmodellist);
    }

    private void initRecyclerView(List<ShopModel> shopmodellist ) {
        RecyclerView recyclerView =  findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ShopListAdapter adapter = new ShopListAdapter(shopmodellist, this);
        recyclerView.setAdapter(adapter);
    }

    private List<ShopModel>getShopData() {
        InputStream is = getResources().openRawResource(R.raw.shop);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try{
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while(( n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0,n);
            }
        }catch (Exception e) {

        }

        String jsonStr = writer.toString();
        Gson gson = new Gson();
        ShopModel[] shopmodels =  gson.fromJson(jsonStr,  ShopModel[].class);
        List<ShopModel> restList = Arrays.asList(shopmodels);

        return  restList;

    }

    @Override
    public void onItemClick(ShopModel shopmodel) {
        Intent intent = new Intent(MainActivity.this, ShopMenuActivity.class);
        intent.putExtra("ShopModel", shopmodel);
        startActivity(intent);

    }


}
