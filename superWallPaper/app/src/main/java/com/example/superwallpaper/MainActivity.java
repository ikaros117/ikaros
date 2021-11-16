package com.example.superwallpaper;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    NavController navController = null;
    BottomNavigationView bottomNavigationView;
    public static boolean isFrist = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // 底部视图联动
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        navController = navHostFragment.getNavController();

        //appbar的联动
        AppBarConfiguration configuration = new AppBarConfiguration.Builder(navController.getGraph()).build();

        NavigationUI.setupActionBarWithNavController(this, navController, configuration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp() || navController.navigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getSupportFragmentManager().getBackStackEntryCount() <= 0) {
            //finish();
        }else {
           // getSupportFragmentManager().popBackStack();
        }
    }
}