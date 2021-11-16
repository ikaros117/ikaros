package com.example.superwallpaper;


import static com.example.superwallpaper.MainActivity.isFrist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Random;


public class HomeFragment extends Fragment {

    WallPaperAdapter wallPaperAdapter;
    WallPaperViewModel wallPaperViewModel;
    String url = null;
    private String[] KEYS = {"dog", "cat", "girl", "fruit", "car"};

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);//注册监听
        Log.d("checkreycle","onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("checkreycle","onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    private String getUrl() {
        String url = "https://pixabay.com/api/?key=12472743-874dc01dadd26dc44e0801d61&q=%s&per_page=100";
        Random random = new Random();
        int i = random.nextInt(KEYS.length);
        url = String.format(url, KEYS[i]);
        return url;
    }

    private String getUrl(String category) {
        String url = "https://pixabay.com/api/?key=12472743-874dc01dadd26dc44e0801d61&category=%s&per_page=100";
        url = String.format(url, category);
        return url;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("checkreycle","onViewCreated");
        SwipeRefreshLayout refreshLayout = view.findViewById(R.id.swiperefreshlayout);

//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction homefragment = fragmentManager.beginTransaction();
//        homefragment.addToBackStack(null);

        //适配器
        wallPaperAdapter = new WallPaperAdapter(new DiffUtil.ItemCallback<WallPaperItem>() {
            @Override
            public boolean areItemsTheSame(@NonNull WallPaperItem oldItem, @NonNull WallPaperItem newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull WallPaperItem oldItem, @NonNull WallPaperItem newItem) {
                return oldItem.getId() == newItem.getId();
            }

        });

        wallPaperViewModel = new ViewModelProvider(getActivity()).get(WallPaperViewModel.class);
        //wallPaperViewModel.getData().setValue(null);
        wallPaperViewModel.getData().observe(getViewLifecycleOwner(), new Observer<List<WallPaperItem>>() {
            @Override
            public void onChanged(List<WallPaperItem> wallPaperItems) {
                wallPaperAdapter.submitList(wallPaperItems);
                refreshLayout.setRefreshing(false);

            }
        });

        if (wallPaperViewModel.getData().getValue() == null) {
            if (isFrist) {
                url = getUrl();
            } else {
                String category = getArguments().getString("category");
                url = getUrl(category);
            }
            Log.d("isFrist", isFrist + "");
            wallPaperViewModel.fetchData(url);
        }

        //下拉刷新
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                wallPaperViewModel.fetchData(getUrl());
            }
        });



        RecyclerView recyclerView = view.findViewById(R.id.recylerView);
        recyclerView.setAdapter(wallPaperAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)

    public void onMessageEvent(MessageEvent event) {
        wallPaperViewModel.getData().setValue(null);
        Log.d("message",event.message);
    }


    public void callback() {
        Log.d("isFrist", "callback");
        isFrist = false;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("checkreycle","onPause");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("checkreycle","onAttach");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("checkreycle","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("checkreycle","onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("checkreycle","onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Log.d("checkreycle","onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("checkreycle","onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("checkreycle","onDetach");
    }


}