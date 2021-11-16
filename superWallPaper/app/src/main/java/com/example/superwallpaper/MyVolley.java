package com.example.superwallpaper;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public class MyVolley {

    private static MyVolley myVolley;

    public static MyVolley newMyVolley() {
        if (myVolley == null) {
            myVolley = new MyVolley();
        }
        return myVolley;
    }

    private CallBack callBack;
    private StringRequest stringRequest;
    private Context context;

    public void requestGet(Context context, String url, CallBack callBack) {
        RequestQueue queue = Volley.newRequestQueue(context);
        this.callBack = callBack;
        stringRequest = new StringRequest(Request.Method.GET, url, listener, errorListener);
        queue.add(stringRequest);
    }

    public void requestPost(Context context, String url, final Map map, CallBack callBack) {
        RequestQueue queue = Volley.newRequestQueue(context);
        this.callBack = callBack;
        stringRequest = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
    }

    private Response.Listener<String> listener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.d("netWork", "Succ");
            callBack.onSuccess(response);
        }
    };

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d("netWork", "Fail");
            callBack.onFailure(error);
        }
    };

    interface CallBack {
        public void onSuccess(String response);

        public void onFailure(VolleyError error);
    }
}
