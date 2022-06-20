package com.example.totrivel.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.example.totrivel.MainActivity;
import com.example.totrivel.R;
import com.example.totrivel.databinding.FragmentGalleryBinding;
import com.example.totrivel.ui.home.HomeFragment;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {
    private final static int KEELUNG = 0;
    private final static int TAICHUNG = 1;
    private FragmentGalleryBinding binding;
    private CheckBox mplaceCheckBox1, mplaceCheckBox2, mplaceCheckBox3, mplaceCheckBox4, mplaceCheckBox5, mplaceCheckBox6;
    private Button mplaceCheckBtn;
    private int choicePlace;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        mplaceCheckBox1 = root.findViewById(R.id.placeCheckBox1);
        mplaceCheckBox2 = root.findViewById(R.id.placeCheckBox2);
        mplaceCheckBox3 = root.findViewById(R.id.placeCheckBox3);
        mplaceCheckBox4 = root.findViewById(R.id.placeCheckBox4);
        mplaceCheckBox5 = root.findViewById(R.id.placeCheckBox5);
        mplaceCheckBox6 = root.findViewById(R.id.placeCheckBox6);
        mplaceCheckBtn = root.findViewById(R.id.placeCheckBtn);

        choicePlace = getArguments().getInt("choice");
        Log.d("FK",String.valueOf(choicePlace));
        
        if(choicePlace == KEELUNG){//根據上一頁選擇狀況來決定景點內容
            String s[] = getResources().getStringArray(R.array.keelung);
            for(int i = 0; i < 6; i++) {
                switch (i){
                    case 0:
                        mplaceCheckBox1.setText(s[i]);
                        break;
                    case 1:
                        mplaceCheckBox2.setText(s[i]);
                        break;
                    case 2:
                        mplaceCheckBox3.setText(s[i]);
                        break;
                    case 3:
                        mplaceCheckBox4.setText(s[i]);
                        break;
                    case 4:
                        mplaceCheckBox5.setText(s[i]);
                        break;
                    case 5:
                        mplaceCheckBox6.setText(s[i]);
                        break;
                }
            }
        }
        if(choicePlace == TAICHUNG){
            String s[] = getResources().getStringArray(R.array.taichung);
            for(int i = 0; i < 6; i++) {
                switch (i){
                    case 0:
                        mplaceCheckBox1.setText(s[i]);
                        break;
                    case 1:
                        mplaceCheckBox2.setText(s[i]);
                        break;
                    case 2:
                        mplaceCheckBox3.setText(s[i]);
                        break;
                    case 3:
                        mplaceCheckBox4.setText(s[i]);
                        break;
                    case 4:
                        mplaceCheckBox5.setText(s[i]);
                        break;
                    case 5:
                        mplaceCheckBox6.setText(s[i]);
                        break;
                }
            }
        }

        mplaceCheckBtn.setOnClickListener(placeCheckBtnOnClick);

        return root;
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }

    private View.OnClickListener placeCheckBtnOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view){
              //TODO:僅測試用，CheckBox尚無功能
              if(mplaceCheckBox1.isChecked()){
                  Toast.makeText((MainActivity)getActivity(), "1", Toast.LENGTH_SHORT).show();
              }
              if(mplaceCheckBox2.isChecked()){
                 Toast.makeText((MainActivity)getActivity(), "2", Toast.LENGTH_SHORT).show();
              }
              if(mplaceCheckBox3.isChecked()){
                Toast.makeText((MainActivity)getActivity(), "3", Toast.LENGTH_SHORT).show();
              }

            NavController navController = ((MainActivity) getActivity()).getNavController();
            navController.navigate(R.id.action_nav_gallery_to_nav_slideshow);
        }
    };

}