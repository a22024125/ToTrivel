package com.example.totrivel.ui.home;

import static android.os.Build.VERSION_CODES.R;

import android.app.Activity;
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
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.totrivel.MainActivity;
import com.example.totrivel.R;
import com.example.totrivel.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private Spinner sp;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button checkBtn = root.findViewById(com.example.totrivel.R.id.checkBtn);
        checkBtn.setOnClickListener(checkBtnOnClick);

        //下拉選單設置
        sp = (Spinner) root.findViewById(com.example.totrivel.R.id.sp);
        sp.setOnItemSelectedListener(spnOnItemSelected);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource((MainActivity)getActivity(), com.example.totrivel.R.array.place,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public AdapterView.OnItemSelectedListener spnOnItemSelected = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { //地點被選取後的事件
            String result = parent.getItemAtPosition(position).toString();
            Toast.makeText((MainActivity)getActivity(), result, Toast.LENGTH_LONG).show();

        }
        public void onNothingSelected(AdapterView<?> parent) {//若未被選取後的事件
            //
        }

    };

    private View.OnClickListener checkBtnOnClick = new View.OnClickListener() {//景點選擇按鈕監聽功能
        @Override
        public void onClick(View v) {
            Toast.makeText((MainActivity)getActivity(), "checkBtn Click!", Toast.LENGTH_SHORT).show();
//                showGalleryFragment();
            NavController navController = ((MainActivity) getActivity()).getNavController();
            navController.navigate(com.example.totrivel.R.id.action_nav_home_to_nav_gallery);
        }
    };
}