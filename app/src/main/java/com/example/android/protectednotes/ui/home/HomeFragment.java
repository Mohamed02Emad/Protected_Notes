package com.example.android.protectednotes.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.android.protectednotes.MainActivity;
import com.example.android.protectednotes.R;
import com.example.android.protectednotes.databinding.FragmentHomeBinding;

import java.util.ArrayList;

import home_RV.home_Rv_Adapter;
import home_RV.home_Rv_Data;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private String NoteName;
    private Button button;

    public void setNoteName(String noteName) {
        NoteName = noteName;
    }

    public String getNoteName() {
        return NoteName;
    }

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

    //button

        button=view.findViewById(R.id.AddNoteButtonHome);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                AlertDialog.Builder dialogName = new AlertDialog.Builder(getActivity());
                dialogName.setTitle("Note Title");

                final EditText EditTxtName = new EditText(getActivity());

                dialogName.setView(EditTxtName);

                dialogName.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        NoteName = EditTxtName.getText().toString();

                        //TODO: Add Note to the RV that has title of NoteName (title is the variable date)


                        dialogInterface.cancel();
                    }
                });

                dialogName.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                dialogName.show();







            }
        });

    }


}