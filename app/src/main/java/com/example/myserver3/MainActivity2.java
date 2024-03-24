package com.example.myserver3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    // Your other variables here

    private ViewPager2 viewPager;
    private ImageAdapter adapter;
    private TextView textViewImageCount;
    private ScaleGestureDetector scaleGestureDetector;
    private float scaleFactor = 1.0f;

    private FloatingActionButton fabArrow, fabArrow2;

    TextView title, price, discount, brand, description, rating, stock;
    ImageView  thumbnailView, localImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Your other code here

        title = findViewById(R.id.nameId);
        price = findViewById(R.id.priceId);
        discount = findViewById(R.id.discountId);
        brand = findViewById(R.id.brand);
        description = findViewById(R.id.descriptionId);
        rating = findViewById(R.id.ratingTextId);
        stock = findViewById(R.id.stockId);
        viewPager = findViewById(R.id.viewPager);

        Intent intent = getIntent();
        String itemTitle = intent.getStringExtra("item_title");
        String thumbnail = intent.getStringExtra("item_image_url");
        String itemPrice = intent.getStringExtra("item_price");
        String itemDescription = intent.getStringExtra("item_description");
        String itemStock = intent.getStringExtra("item_stock");
        String itemRating = intent.getStringExtra("item_rating");
        String itemBrand = intent.getStringExtra("item_brand");
        String itemDiscount = intent.getStringExtra("item_discount");
        ArrayList<String> images = intent.getStringArrayListExtra("images");

        title.setText(itemTitle);
        price.setText(itemPrice);
        discount.setText(itemDiscount);
//        brand.append(itemBrand);
        description.append(itemDescription);
        rating.setText(itemRating);
        stock.append(itemStock);
//        Picasso.get().load(thumbnail).placeholder(R.drawable.malaysia).into(viewPager);

        viewPager = findViewById(R.id.viewPager);
        textViewImageCount = findViewById(R.id.textViewImageCount);
//        fabArrow = findViewById(R.id.fabArrow);
//        fabArrow2 = findViewById(R.id.fabArrow2);

        adapter = new ImageAdapter(this, images, viewPager);
        viewPager.setAdapter(adapter);

        updateImageCount(1); // Display initial count

//        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                updateImageCount(position + 1); // Update count when page changes
                updateFabVisibility(position);
                updateFab2Visibility(position);
            }
        });

        fabArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            }
        });

        fabArrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
            }
        });
    }

    // Your other methods here

    private void updateImageCount(int currentPosition) {
        int totalImages = adapter.getItemCount();
        textViewImageCount.setText(currentPosition + "/" + totalImages);
    }

    private void updateFabVisibility(int currentPosition) {
        int totalImages = adapter.getItemCount();
        if (currentPosition < totalImages - 1) {
            fabArrow.setVisibility(View.VISIBLE);
        } else {
            fabArrow.setVisibility(View.GONE);
        }
    }

    private void updateFab2Visibility(int currentPosition) {
        int totalImages = adapter.getItemCount();
        if (currentPosition >= 1) {
            fabArrow2.setVisibility(View.VISIBLE);
        } else {
            fabArrow2.setVisibility(View.GONE);
        }
    }

//    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
//        @Override
//        public boolean onScale(ScaleGestureDetector detector) {
//            scaleFactor *= detector.getScaleFactor();
//            // Restrict the minimum and maximum zoom scale factor if needed
//            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));
////            viewPager.setScaleX(scaleFactor);
////            viewPager.setScaleY(scaleFactor);
//
//            matrix.setScale(scale, scale);
//            imageView.setImageMatrix(matrix);
//            return true;
//        }
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        return true;
    }

}
