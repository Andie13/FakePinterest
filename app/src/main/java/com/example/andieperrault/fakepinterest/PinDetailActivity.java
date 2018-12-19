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

    boolean isImageFitToScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_detail);

        Context context = getApplicationContext();
        TextView titleTextView = findViewById(R.id.titleTextview);
        TextView dateTextView = findViewById(R.id.dateTextView);
        TextView descTextView = findViewById(R.id.descTextView);
        TextView userTextView = findViewById(R.id.userId);
        final ImageView imageView = findViewById(R.id.imageDetails);


        Intent intent = getIntent();
        Glide.with(context).load(intent.getStringExtra("url")).apply(RequestOptions.centerCropTransform()).into(imageView);
        titleTextView.setText(intent.getStringExtra("title"));
        dateTextView.setText(intent.getStringExtra("date"));
        descTextView.setText(intent.getStringExtra("desc"));
        userTextView.setText(intent.getStringExtra("userId"));

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
                if(isImageFitToScreen) {
                    isImageFitToScreen=false;
                    imageView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    imageView.setAdjustViewBounds(true);
                }else{
                    isImageFitToScreen=true;
                    imageView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });

    }


}
