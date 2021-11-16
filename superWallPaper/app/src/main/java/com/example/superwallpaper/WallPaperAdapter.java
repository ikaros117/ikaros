package com.example.superwallpaper;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class WallPaperAdapter extends ListAdapter<WallPaperItem, WallPaperAdapter.MyHolder> {

    int index;

    protected WallPaperAdapter(@NonNull DiffUtil.ItemCallback<WallPaperItem> diffCallback) {
        super(diffCallback);
    }



    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item, parent, false);

        MyHolder myHolder = new MyHolder(view);
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //将数据传输过去
                WallPaperItem item = getItem(myHolder.getAdapterPosition());
                String largeImageURL = item.getLargeImageURL();
                Bundle bundle = new Bundle();
                bundle.putString("largeImageURL", largeImageURL);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_homeFragment_to_detailFragment, bundle);
            }
        });

        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        WallPaperItem item = getItem(position);
        String url = item.getWebformatURL();

        Glide.with(holder.imageView).load(url).apply(new RequestOptions().placeholder(R.drawable.ic_baseline_insert_photo_24)).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Log.d("MyAPP", "onLoadFailed");
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Log.d("MyAPP", "onLoadSucc");
                return false;
            }
        }).into(holder.imageView);
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
