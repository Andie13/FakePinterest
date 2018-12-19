package com.example.andieperrault.fakepinterest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class PinDetailActivity extends AppCompatActivity {

    private String title;
    private String url;
    private String type;
    private String description;
    private String date;
    private String userId;

    ImageView imageView;
    ImageView imageView2;
    boolean isFullScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_detail);

        Context context = getApplicationContext();
        TextView titleTextView = findViewById(R.id.titleTextview);
        TextView dateTextView = findViewById(R.id.dateTextView);
        TextView descTextView = findViewById(R.id.descTextView);
        TextView userTextView = findViewById(R.id.userId);

        imageView = findViewById(R.id.imageDetails);
        imageView2 = findViewById(R.id.imageView2);
        isFullScreen = true;

        Intent intent = getIntent();
        Glide.with(context).load(intent.getStringExtra("url")).apply(RequestOptions.centerCropTransform()).into(imageView);
        Glide.with(context).load(intent.getStringExtra("url")).apply(RequestOptions.centerCropTransform()).into(imageView2);
        titleTextView.setText(intent.getStringExtra("title"));
        dateTextView.setText(intent.getStringExtra("date"));
        descTextView.setText(intent.getStringExtra("desc"));
        userTextView.setText("+ infos User ");

        userId = intent.getStringExtra("userId");
        userTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(PinDetailActivity.this, ShowUserDetailsActivity.class);
                newIntent.putExtra("userId",userId);
                PinDetailActivity.this.startActivity(newIntent);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!isFullScreen) {
                    imageView2.setVisibility(View.VISIBLE);
                    isFullScreen=true;

                }
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFullScreen){
                    imageView2.setVisibility(View.INVISIBLE);
                    isFullScreen=false;

                }
            }
        });




    }


}
