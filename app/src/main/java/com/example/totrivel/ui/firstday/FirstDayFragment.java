package com.example.totrivel.ui.firstday;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.totrivel.MainActivity;
import com.example.totrivel.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstDayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstDayFragment extends Fragment {
    Button mmapPlace1, mmapPlace2, mmapPlace3, mmapPlace4;
    private Location local = null;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstDayFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FirstDayFragment newInstance(String param1, String param2) {
        FirstDayFragment fragment = new FirstDayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_first_day, container, false);
        mmapPlace1 = v.findViewById(R.id.mapPlace1);
        mmapPlace2 = v.findViewById(R.id.mapPlace2);
        mmapPlace3 = v.findViewById(R.id.mapPlace3);
        mmapPlace4 = v.findViewById(R.id.mapPlace4);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions( getActivity(), new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }

        getLocal();
        return v;
    }

    private void getLocal() {
        /**沒有權限則返回*/
        if (ActivityCompat.checkSelfPermission(getContext(),android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(getContext(),android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("FKK",String.valueOf(ActivityCompat.checkSelfPermission(getContext(),android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED));
            return;
        }
        LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        /**知道位置後..*/
        Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Log.d("FKK So WTF IS LOCATION",String.valueOf(location));
        if (location != null){
            Log.d("FKK local","FUCK");
            showLocation(location);
        }else{
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, mListener);
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, mListener);
        }
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
//            showLocation(location);
        }
    };


    private void showLocation(Location location){
        String address = "緯度："+location.getLatitude()+"經度："+location.getLongitude();
        mmapPlace1.setOnClickListener(view -> {
            String url = "https://www.google.com/maps/@"+location.getLatitude()+","+location.getLongitude()+",15z";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
            Log.d("FKK2", address);
        });
        mmapPlace2.setOnClickListener(view -> {
            String url = "https://www.google.com/maps/@"+25.14627688486+","+121.78606855290997+",15z";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });
    }
}