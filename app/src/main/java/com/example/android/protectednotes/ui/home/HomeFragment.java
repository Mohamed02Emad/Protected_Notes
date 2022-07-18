package com.example.android.protectednotes.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.android.protectednotes.MainActivity;
import com.example.android.protectednotes.R;
import com.example.android.protectednotes.The_Note;
import com.example.android.protectednotes.databinding.FragmentHomeBinding;

import java.util.ArrayList;

import home_RV.home_Rv_Adapter;
import home_RV.home_Rv_Data;
import home_RV.home_Rv_interface;

public class HomeFragment extends Fragment implements home_Rv_interface {

    ArrayList<home_Rv_Data> home_rv_dataArrayList=new ArrayList<>();
    private FragmentHomeBinding binding;
    private String NoteName;
    private Button button;
//TODO: make a snack bar that deletes a note on long Click
//TODO: make notes dragable
//TODO: make database to hold 3 strings (title , content , outer content)
//TODO: design a method to creat the outer content
//TODO: Link add button to your database and rv
//TODO: Make a notification for anything
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

        home_rv_dataArrayList.add(new home_Rv_Data("Hello my friend","12/4"));
        home_rv_dataArrayList.add(new home_Rv_Data("Testing Note Nothing important bla bla bla ooooooooo bla","testing title"));
        home_rv_dataArrayList.add(new home_Rv_Data("Winner Winner Chicken dinner","winners"));
        home_rv_dataArrayList.add(new home_Rv_Data("house of memories","5/4"));
        home_rv_dataArrayList.add(new home_Rv_Data(" Babe we built this house On memories Take my picture now Shake till you see it and when your fantasies become your legacy Promise me a place in your house of memories","20/10"));
        home_rv_dataArrayList.add(new home_Rv_Data("in the end it does't matter ","1/8"));
        home_rv_dataArrayList.add(new home_Rv_Data("i'm booooooooooooooooooooooooooooooooored","2/4"));
        home_rv_dataArrayList.add(new home_Rv_Data("what was my password","12/4"));

    // RV
    home_RV=view.findViewById(R.id.RV);
    home_Rv_Adapter Adapter=new home_Rv_Adapter(home_rv_dataArrayList,this);
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

                        Toast.makeText(getActivity(),  NoteName+" Was added", Toast.LENGTH_SHORT).show();

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



    public void setNoteName(String noteName) {
        NoteName = noteName;
    }

    public String getNoteName() {
        return NoteName;
    }



    @Override
    public void OnItemClick(int position) {
        Intent intent=new Intent(getActivity(), The_Note.class);
        intent.putExtra("Title",home_rv_dataArrayList.get(position).getTitle());
        intent.putExtra("Content",home_rv_dataArrayList.get(position).getContent());

        startActivity(intent);
    }

    @Override
    public void OnItemLongClick(int position) {
        Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
    }
}