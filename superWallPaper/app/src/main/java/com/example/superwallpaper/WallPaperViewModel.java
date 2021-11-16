package com.example.superwallpaper;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.List;

public class WallPaperViewModel extends AndroidViewModel {
    MutableLiveData<List<WallPaperItem>> data;

    public WallPaperViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<WallPaperItem>> getData() {
        if (data == null) {
            data = new MutableLiveData<>();
            data.setValue(null);
        }
        return data;
    }


    public void fetchData(String url) {
        MyVolley myVolley = MyVolley.newMyVolley();
        myVolley.requestGet(getApplication(), url, new MyVolley.CallBack() {
            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                Wallpaper wallpaper = gson.fromJson(response, Wallpaper.class);
                List<WallPaperItem> hits = wallpaper.getHits();
                getData().setValue(hits);
            }

            @Override
            public void onFailure(VolleyError error) {
                Log.d("MyAPP", "netWorkErro!!!");
            }
        });
    }

}
