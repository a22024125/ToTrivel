package com.example.totrivel.ui.slideshow;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.example.totrivel.MainActivity;
import com.example.totrivel.R;
import com.example.totrivel.databinding.FragmentSlideshowBinding;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment {
    private int choicePlace = 1;
    private final static int KEELUNG = 0;
    private final static int TAICHUNG = 1;
    private int[] order = new int[4];
    private String[] choiceAttractions = new String[4];
    private FragmentSlideshowBinding binding;
    private Button mrouteCheckBtn;
    private Spinner spots1to4[];
    private int ids[] = {R.id.sp_spot1, R.id.sp_spot2, R.id.sp_spot3, R.id.sp_spot4};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        //取得選擇地點的參數
        choicePlace = getArguments().getInt("choice");
        spots1to4 = new Spinner[4];
        mrouteCheckBtn = root.findViewById(R.id.routeCheckBtn);

        if(choicePlace == KEELUNG){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource((MainActivity) getActivity(), R.array.keelung,
                    android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //chosenSpots = getArguments().getIntegerArrayList();
            //下拉選單設置
            for(int i = 0; i < 4; i++) {
                spots1to4[i] = (Spinner) root.findViewById(ids[i]);
                spots1to4[i].setOnItemSelectedListener(spotSelected);
                order[i] = 1;
                spots1to4[i].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                spots1to4[i].setAdapter(adapter);
            }
        }
        if(choicePlace == TAICHUNG){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource((MainActivity) getActivity(), R.array.taichung,
                    android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //chosenSpots = getArguments().getIntegerArrayList();
            //下拉選單設置
            for(int i = 0; i < 4; i++) {
                spots1to4[i] = (Spinner) root.findViewById(ids[i]);
                spots1to4[i].setOnItemSelectedListener(spotSelected);
                spots1to4[i].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                spots1to4[i].setAdapter(adapter);
            }
        }

        mrouteCheckBtn = root.findViewById(R.id.routeCheckBtn);
        mrouteCheckBtn.setOnClickListener(routeCheckBtnOnClick);

        return root;
    }

    public AdapterView.OnItemSelectedListener spotSelected = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { //地點被選取後的事件
            String result = parent.getItemAtPosition(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private View.OnClickListener routeCheckBtnOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view){
            for(int i = 0; i < 4; i++){
                choiceAttractions[i] = spots1to4[i].getSelectedItem().toString();
                Log.d("fk att",choiceAttractions[i]);
            }

            NavController navController = ((MainActivity) getActivity()).getNavController();
            Bundle bundle = new Bundle();
            bundle.putInt("choice", choicePlace);//傳遞choicePlace
            bundle.putStringArray("choiceAttractions", choiceAttractions);//傳遞choiceAttractions

            navController.navigate(R.id.action_nav_slideshow_to_nav_firstday, bundle);
        }
    };
}