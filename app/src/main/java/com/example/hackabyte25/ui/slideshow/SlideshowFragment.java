package com.example.hackabyte25.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.hackabyte25.R;
import com.example.hackabyte25.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button editMenuButton = binding.editMenuButton;
        editMenuButton.setOnClickListener(v -> {
            EditMenuFragment editMenuFragment = new EditMenuFragment();
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            // Set transition animation
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

            // Replace current fragment and add to back stack
            transaction.replace(R.id.nav_host_fragment_content_main, editMenuFragment);
            transaction.addToBackStack(null);

            // Hide current fragment's view
            binding.getRoot().setVisibility(View.GONE);

            transaction.commit();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}