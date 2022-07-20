package com.example.android.protectednotes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import Todo_RV.ListInterface;
import Todo_RV.ToDoAdapter;
import Todo_RV.ToDoData;

public class to_do extends Fragment implements ListInterface {

    ArrayList<ToDoData> arrayList=new ArrayList<>();
    RecyclerView recyclerView;
    ToDoAdapter adapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public to_do() {
        // Required empty public constructor
    }

    public static to_do newInstance(String param1, String param2) {
        to_do fragment = new to_do();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addingtoarrayList();
        recyclerView=view.findViewById(R.id.TodoRv);
        setUpRv();

    }

    private void setUpRv() {
        adapter=new ToDoAdapter(arrayList,this);
        LinearLayoutManager L=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(L);
        recyclerView.setAdapter(adapter);
    }

    private void addingtoarrayList() {
        arrayList.add(new ToDoData("DataBase",false));
        arrayList.add(new ToDoData("DataBase1",false));
        arrayList.add(new ToDoData("DataBase2",true));
        arrayList.add(new ToDoData("DataBase3",false));
        arrayList.add(new ToDoData("DataBase4",true));
    }

    @Override
    public void OnItemClick(int position) {
        Toast.makeText(getActivity(), arrayList.get(position).getText(), Toast.LENGTH_SHORT).show();
    }
}

















