package com.kono_protected2.android.protectednotes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;



import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.Collections;

import DoneDB.DoneDB;
import DoneDB.DoneData;
import Todo_RV.ListInterface;
import Todo_RV.ToDoAdapter;
import Todo_RV.ToDoData;
import todoDB.todoDB;
public class to_do extends Fragment implements ListInterface {

      private AdView mAdView;


    ArrayList<ToDoData> arrayList=new ArrayList<>();
    RecyclerView recyclerView;
    ToDoAdapter adapter;
    todoDB todoDB;
    DoneDB doneDB;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do, container, false);
    }

    private void setUpRv() {
        adapter=new ToDoAdapter(arrayList,this);
        LinearLayoutManager L=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(L);
        recyclerView.setAdapter(adapter);
    }

    public void resetdb() {
        arrayList.clear();
        arrayList.addAll(todoDB.todoDao().getAll());
        adapter.notifyDataSetChanged();
    }

    private void AddDialogue(){
        //TODO: insert
        AlertDialog.Builder dialogName = new AlertDialog.Builder(getActivity());
        dialogName.setTitle(R.string.addToList);

        final EditText EditTxtName = new EditText(getActivity());

        dialogName.setView(EditTxtName);

        dialogName.setPositiveButton(R.string.Add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

               String stringToReturn = EditTxtName.getText().toString();
                todoDB.todoDao().Insert(new ToDoData(stringToReturn,false));
                resetdb();
                dialogInterface.cancel();
            }
        });

        dialogName.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        dialogName.show();

    }



    @Override
    public void OnItemClick(int position) {
       int id=arrayList.get(position).getId();
       boolean b =arrayList.get(position).isCheckBoxState();
       String s=arrayList.get(position).getText();
       if(b){
           ToDoData todo=new ToDoData(s,false);
           todo.setId(id);
                   todoDB.todoDao().Update(todo);

       }else{
           ToDoData todo=new ToDoData(s,true);
           todo.setId(id);
           todoDB.todoDao().Update(todo);
       }
       resetdb();
    }

    private void Drag() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN | ItemTouchHelper.UP, ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();

                Collections.swap(arrayList, fromPosition, toPosition);
                recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);

                int id1=arrayList.get(fromPosition).getId();
                int id2=arrayList.get(toPosition).getId();

                ToDoData d1= arrayList.get(fromPosition);
                ToDoData d2= arrayList.get(toPosition);

                d1.setId(id2);
                d2.setId(id1);

                todoDB.todoDao().Update(d1);
                todoDB.todoDao().Update(d2);
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position =viewHolder.getAdapterPosition();
                if (arrayList.get(position).isCheckBoxState()){
                   doneDB.doneDao().Insert(new DoneData(arrayList.get(position).getText(),true));
                }


                todoDB.todoDao().Delete(adapter.ReturnData(viewHolder.getAdapterPosition()));
                resetdb();
            }
        }).attachToRecyclerView(recyclerView);
        resetdb();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);






        recyclerView=view.findViewById(R.id.TodoRv);
        setUpRv();

        todoDB = Room.databaseBuilder(getActivity().getApplicationContext(), todoDB.class,"list")
                .allowMainThreadQueries()
                .build();


        doneDB = Room.databaseBuilder(getActivity().getApplicationContext(), DoneDB.class,"list2")
                .allowMainThreadQueries()
                .build();

        Button button=view.findViewById(R.id.plusbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddDialogue();
            }
        });

        resetdb();
        Drag();
    }


}