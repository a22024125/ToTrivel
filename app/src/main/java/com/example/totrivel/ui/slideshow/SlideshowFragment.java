package com.example.totrivel.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.example.totrivel.MainActivity;
import com.example.totrivel.R;
import com.example.totrivel.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private Button mrouteCheckBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        mrouteCheckBtn = root.findViewById(R.id.routeCheckBtn);
        mrouteCheckBtn.setOnClickListener(routeCheckBtnOnClick);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private View.OnClickListener routeCheckBtnOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view){
            NavController navController = ((MainActivity) getActivity()).getNavController();
            navController.navigate(R.id.action_nav_slideshow_to_nav_firstday);
        }
    };
}