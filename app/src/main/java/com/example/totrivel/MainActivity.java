package com.example.totrivel;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.totrivel.ui.gallery.GalleryFragment;
import com.example.totrivel.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.totrivel.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private NavController navController;
    private Spinner sp;
    private HomeFragment mhomeFragment;
    private GalleryFragment mgalleryFragment;
    private Location local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow ,R.id.nav_firstday)
                .setOpenableLayout(drawer)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);

//---------------------------程式從這往下寫-----------------------------
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }
//        local = getLocal();
//        Log.d("FKLOCAL",String.valueOf(local));

        findViewById(R.id.homeBtn).setOnClickListener(new View.OnClickListener() {//選單主頁按鈕監聽功能
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "homeBtn Click!", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.nav_home);
            }
        });

        findViewById(R.id.attractionsBtn).setOnClickListener(new View.OnClickListener() {//選單景點選擇按鈕監聽功能
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "attractionsBtn Click!", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.nav_gallery);
            }
        });

        findViewById(R.id.routeBtn).setOnClickListener(new View.OnClickListener() {//選單路線按鈕監聽功能
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "routeBtn Click!", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.nav_slideshow);
            }
        });

        findViewById(R.id.firstDayBtn).setOnClickListener(new View.OnClickListener() {//選單第一天按鈕監聽功能
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "firstDayBtn Click!", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.nav_firstday);
            }
        });

        findViewById(R.id.checkBtn).setOnClickListener(new View.OnClickListener() {//HOME景點選擇按鈕監聽功能
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "checkBtn Click!", Toast.LENGTH_SHORT).show();
//                showGalleryFragment();
                navController.navigate(R.id.action_nav_home_to_nav_gallery);
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        local = getLocal();
//        Log.d("FKLOCAL",String.valueOf(local));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**監聽位置變化*/
    LocationListener mListener = new LocationListener() {
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
        @Override
        public void onProviderEnabled(String provider) {
        }
        @Override
        public void onProviderDisabled(String provider) {
        }
        @Override
        public void onLocationChanged(Location location) {
            local = location;
        }
    };

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public NavController getNavController(){
        return navController;
    }

}