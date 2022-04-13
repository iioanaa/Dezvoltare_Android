package com.example.flowershop.adapters;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flowershop.R;
import com.example.flowershop.model.ShopModel;

import java.util.List;

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.MyViewHolder> {

    private final List<ShopModel> shopmodellist;
    private final ShopListClickListener clickListener;

    public ShopListAdapter(List<ShopModel> shopmodellist, ShopListClickListener clickListener) {
        this.shopmodellist = shopmodellist;
        this.clickListener = clickListener;
    }



    @NonNull
    @Override
    public ShopListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return  new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ShopListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.ShopName.setText(shopmodellist.get(position).getName());
        holder.ShopAddress.setText("Address: "+shopmodellist.get(position).getAddress());
        holder.ShopHours.setText("Today's hours: " + shopmodellist.get(position).getHours().getTodaysHours());

        holder.itemView.setOnClickListener(v -> clickListener.onItemClick(shopmodellist.get(position)));
        Glide.with(holder.thumbImage)
                .load(shopmodellist.get(position).getImage())
                .into(holder.thumbImage);

    }

    @Override
    public int getItemCount() {
        return shopmodellist.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  ShopName;
        TextView  ShopAddress;
        TextView  ShopHours;
        ImageView thumbImage;

        public MyViewHolder(View view) {
            super(view);
            ShopName = view.findViewById(R.id.ShopName);
            ShopAddress = view.findViewById(R.id.ShopAddress);
            ShopHours = view.findViewById(R.id.ShopHours);
            thumbImage = view.findViewById(R.id.thumbImage);

        }
    }

    public interface ShopListClickListener {
        void onItemClick(ShopModel shopmodel);
    }
}

