package com.example.android.protectednotes.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.android.protectednotes.R;
import com.example.android.protectednotes.databinding.FragmentHomeBinding;

import java.util.ArrayList;

import home_RV.home_Rv_Adapter;
import home_RV.home_Rv_Data;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    RecyclerView home_RV;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    //Rv arraylist
        ArrayList<home_Rv_Data> home_rv_dataArrayList=new ArrayList<>();
        home_rv_dataArrayList.add(new home_Rv_Data("Hello my friend","12/4"));
        home_rv_dataArrayList.add(new home_Rv_Data("house of memories","5/4"));
        home_rv_dataArrayList.add(new home_Rv_Data(" Babe we built this house On memories Take my picture now Shake till you see it and when your fantasies become your legacy Promise me a place in your house of memories","20/10"));
        home_rv_dataArrayList.add(new home_Rv_Data("in the end it does't matter ","1/8"));
        home_rv_dataArrayList.add(new home_Rv_Data("i'm booooooooooooooooooooooooooooooooored","2/4"));
        home_rv_dataArrayList.add(new home_Rv_Data("what was my password","12/4"));

    // RV
    home_RV=view.findViewById(R.id.RV);
    home_Rv_Adapter Adapter=new home_Rv_Adapter(home_rv_dataArrayList);
    home_RV.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
    home_RV.setAdapter(Adapter);
    }
}