package com.example.superwallpaper;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyHolder> {

    List<Category> categoryList;
    Context context;
    private static boolean isFristClick = true;


    CategoryAdapter(List<Category> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.category_item, parent, false);
        MyHolder myHolder = new MyHolder(view);
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //发送事件
                EventBus.getDefault().post(new MessageEvent("HelloEveryone"));

                if(isFristClick){
                    Log.d("isFrist","Click1");
                    HomeFragment homeFragment = new HomeFragment();
                    homeFragment.callback();
                    Log.d("isFrist","Click2");
                    isFristClick = false;
                    Log.d("isFrist","Click3");
                }
                //将数据传输过去
                Bundle bundle = new Bundle();
                String category = myHolder.textView.getText().toString();
                Log.d("cate", category);
                bundle.putString("category",category);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_categoryFragment_to_homeFragment,bundle);
            }
        });

        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.textView.setText(categoryList.get(position).getName());
        holder.textView.setBackground(context.getDrawable(categoryList.get(position).getCover()));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView_category);

        }
    }

}
