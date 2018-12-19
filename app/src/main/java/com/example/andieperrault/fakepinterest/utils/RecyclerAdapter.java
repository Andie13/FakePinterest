package com.example.andieperrault.fakepinterest.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.andieperrault.fakepinterest.PinDetailActivity;
import com.example.andieperrault.fakepinterest.R;
import com.example.andieperrault.fakepinterest.pojo.PinResp;
import com.example.andieperrault.fakepinterest.pojo.ResultPins;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by andieperrault on 15/12/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder> {

    Context context;
    List<ResultPins> pinList;
    RelativeLayout parentLayout;


    public RecyclerAdapter(Context context, List<ResultPins> pinList) {
        this.context = (Context) context;
        this.pinList = pinList;
    }
    public void setPinList(List<ResultPins> pinList) {
        this.pinList = pinList;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_layout,parent,false);
        return new MyviewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerAdapter.MyviewHolder holder, final int position) {
        String mediaType;
        mediaType = pinList.get(position).getType();
        holder.pinName.setText(pinList.get(position).getTitle());
        Glide.with(context).load(pinList.get(position).getContentUrl()).apply(RequestOptions.centerCropTransform()).into(holder.image);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PinDetailActivity.class);

                intent.putExtra("url",pinList.get(position).getContentUrl());
                intent.putExtra("title",pinList.get(position).getTitle());
                intent.putExtra("desc",pinList.get(position).getDescription());
                intent.putExtra("date",pinList.get(position).getDate());
                intent.putExtra("userId",pinList.get(position).getUserId());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(pinList != null){
            return pinList.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView pinName;
        ImageView image;
        VideoView videoView;
        RelativeLayout parentLayout;

        public MyviewHolder(View itemView) {
            super(itemView);
           pinName = itemView.findViewById(R.id.textView);
           image = itemView.findViewById(R.id.imageView);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }

         public void onClick(View view){
             Intent intent = new Intent(context, PinDetailActivity.class);

            intent.putExtra("id",pinList.get(getAdapterPosition()).getId());


            context.startActivity(intent);
         }

    }
}