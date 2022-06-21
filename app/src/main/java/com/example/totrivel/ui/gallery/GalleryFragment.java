package com.example.totrivel.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
    private CheckBox[] mCheckBox;
    private int lengthBox = 6;
    private int lengthCount = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        mplaceCheckBtn = root.findViewById(R.id.placeCheckBtn);
        mCheckBox = new CheckBox[lengthBox];

        //取得選擇地點的參數
        choicePlace = getArguments().getInt("choice");
//        Log.d("FK",String.valueOf(choicePlace));

        for(int i = 0; i < lengthBox; i++) {
            int id = getResources().getIdentifier("placeCheckBox"+i, "id", getActivity().getPackageName());
            mCheckBox[i] = (CheckBox) root.findViewById(id);
            if(choicePlace == KEELUNG) {//根據上一頁選擇狀況來決定景點內容
                String s[] = getResources().getStringArray(R.array.keelung);
                mCheckBox[i].setText(s[i]);
                Log.d("FK si",s[i]);
            }
            if(choicePlace == TAICHUNG) {
                String s[] = getResources().getStringArray(R.array.taichung);
                mCheckBox[i].setText(s[i]);
            }
            mCheckBox[i].setOnCheckedChangeListener(checker);
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
            NavController navController = ((MainActivity) getActivity()).getNavController();
            navController.navigate(R.id.action_nav_gallery_to_nav_slideshow);
        }
    };

    CompoundButton.OnCheckedChangeListener checker = new CompoundButton.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton cb, boolean b) {
            if(lengthCount == 4){//限制最多4個
                cb.setChecked(false);
            }else if(b){
                lengthCount++;
            }else if(!b){
                lengthCount--;
            }
        }
    };

}