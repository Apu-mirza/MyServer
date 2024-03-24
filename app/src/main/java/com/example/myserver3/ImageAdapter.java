package com.example.myserver3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context context;
    private ArrayList<String> images;
    ViewPager2 viewPager;
    public ImageAdapter(Context context, ArrayList<String> images, ViewPager2 viewPager2) {
        this.context = context;
        this.images = images;
        this.viewPager = viewPager2;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        String image = images.get(position);
        Picasso.get().load(image).placeholder(R.drawable.malaysia).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        FloatingActionButton fabArrow, fabArrow2;


        ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            fabArrow = itemView.findViewById(R.id.fabArrow);
            fabArrow2 = itemView.findViewById(R.id.fabArrow2);
        }
    }
}
