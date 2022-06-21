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

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

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
    private String[] choiceAttractions = new String[4];
    private final static int KEELUNG = 0;
    private final static int TAICHUNG = 1;
    private int choicePlace = 1;


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
        mmapPlace1 = v.findViewById(R.id.mapPlace0);
        mmapPlace2 = v.findViewById(R.id.mapPlace1);
        mmapPlace3 = v.findViewById(R.id.mapPlace2);
        mmapPlace4 = v.findViewById(R.id.mapPlace3);

        for(int i = 0;i < 4;i++){
            choiceAttractions[i] = "未選景點";
        }

        choiceAttractions = getArguments().getStringArray("choiceAttractions");
        choicePlace = getArguments().getInt("choice");
        mmapPlace1.setText(choiceAttractions[0]);
        mmapPlace2.setText(choiceAttractions[1]);
        mmapPlace3.setText(choiceAttractions[2]);
        mmapPlace4.setText(choiceAttractions[3]);

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

    private double getLa(String s){
        if(choicePlace == KEELUNG){
            switch (s){
                case "潮境公園":
                    return 25.143626350220263;
                case "廟口夜市":
                    return 25.12846724178702;
                case "外木山情人湖濱海大道":
                    return 25.16404289618748;
                case "忘憂谷":
                    return 25.146003495522976;
                case "東岸廣場":
                    return 25.13038267637243;
                case "和平島公園":
                    return 25.161960931868325;
            }
        }
        if(choicePlace == TAICHUNG){
            switch (s){
                case "高美濕地":
                    return 24.312087867496718;
                case "審計新村":
                    return 24.145193719789017;
                case "麗寶樂園":
                    return 24.32325011454076;
                case "異想新樂園":
                    return 24.084651955195017;
                case "大坑步道":
                    return 24.181173220879657;
                case "彩虹眷村":
                    return 24.13397471620115;
            }
        }
        return 25.150758340702478;
    }

    private double getLo(String s){
        if(choicePlace == KEELUNG){
            switch (s){
                case "潮境公園":
                    return 121.80336313818306;
                case "廟口夜市":
                    return 121.74361702493464;
                case "外木山情人湖濱海大道":
                    return 121.72039518447382;
                case "忘憂谷":
                    return 121.798222245856;
                case "東岸廣場":
                    return 121.74323784214616;
                case "和平島公園":
                    return 121.76440202494629;
            }
        }
        if(choicePlace == TAICHUNG){
            switch (s){
                case "高美濕地":
                    return 120.55077559322919;
                case "審計新村":
                    return 120.66262246144214;
                case "麗寶樂園":
                    return 120.695502540281;
                case "異想新樂園":
                    return 120.695673170969;
                case "大坑步道":
                    return 120.73372948248137;
                case "彩虹眷村":
                    return 120.60981054027847;
            }
        }
        return 121.77596871701977;
    }

    private void showLocation(Location location){
        mmapPlace1.setOnClickListener(view -> {
            String url = "https://www.google.com/maps/@" + getLa(choiceAttractions[0]) + "," + getLo(choiceAttractions[0]) + ",15z";
            Log.d("Fk choicePlace",String.valueOf(choicePlace));
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });
        mmapPlace2.setOnClickListener(view -> {
            String url = "https://www.google.com/maps/@"+ getLa(choiceAttractions[1]) + "," + getLo(choiceAttractions[1]) +",15z";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });
        mmapPlace3.setOnClickListener(view -> {
            String url = "https://www.google.com/maps/@"+ getLa(choiceAttractions[2]) + "," + getLo(choiceAttractions[2]) +",15z";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });
        mmapPlace4.setOnClickListener(view -> {
            String url = "https://www.google.com/maps/@"+ getLa(choiceAttractions[3]) + "," + getLo(choiceAttractions[3]) +",15z";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });
    }
}