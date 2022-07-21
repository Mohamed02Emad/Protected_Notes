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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.room.Database;
import androidx.room.Room;

import com.example.android.protectednotes.MainActivity;
import com.example.android.protectednotes.R;
import com.example.android.protectednotes.The_Note;
import com.example.android.protectednotes.databinding.FragmentHomeBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Todo_RV.ToDoData;
import home_DB.NoteDao;
import home_DB.NotesDataBase;
import home_RV.home_Rv_Adapter;
import home_RV.home_Rv_Data;
import home_RV.home_Rv_VH;
import home_RV.home_Rv_interface;
import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends Fragment implements home_Rv_interface {

    ConstraintLayout constraintLayout;
    ArrayList<home_Rv_Data> home_rv_dataArrayList = new ArrayList<>();
    private FragmentHomeBinding binding;
    private String NoteName;
    private Button button;
    NotesDataBase notesDataBase;
    RecyclerView home_RV;
    static home_Rv_Adapter Adapter;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notesDataBase = Room.databaseBuilder(getActivity().getApplicationContext(), NotesDataBase.class, "notes")
                .allowMainThreadQueries()
                .build();

        constraintLayout = view.findViewById(R.id.constraintid);
        // RV
        home_RV = view.findViewById(R.id.RV);
        Adapter = new home_Rv_Adapter(home_rv_dataArrayList, this);
        home_RV.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        home_RV.setAdapter(Adapter);
        home_rv_dataArrayList.clear();
        home_rv_dataArrayList.addAll(notesDataBase.noteDao().getAll());
        Adapter.notifyDataSetChanged();
        //button
        button = view.findViewById(R.id.AddNoteButtonHome);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogName = new AlertDialog.Builder(getActivity());
                dialogName.setTitle(R.string.Note_Title);

                final EditText EditTxtName = new EditText(getActivity());
                dialogName.setView(EditTxtName);
                dialogName.setPositiveButton(R.string.Add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NoteName = EditTxtName.getText().toString();

                        notesDataBase.noteDao().Insert(new home_Rv_Data("",NoteName));
                        resetdb();
                        //         Toast.makeText(getActivity(), NoteName + " Was added", Toast.LENGTH_SHORT).show();
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
        });

        if (MainActivity.resetDB) resetdb();
       // Drag();
        MainActivity.resetDB = false;
    }

    public void setNoteName(String noteName) {
        NoteName = noteName;
    }

    public String getNoteName() {
        return NoteName;
    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(getActivity(), The_Note.class);
        intent.putExtra("Title", home_rv_dataArrayList.get(position).getTitle());
        intent.putExtra("Content", home_rv_dataArrayList.get(position).getContent());
        intent.putExtra("id",home_rv_dataArrayList.get(position).getId());
        startActivity(intent);

     //   Toast.makeText(getActivity(),home_rv_dataArrayList.get(position).getId(), Toast.LENGTH_SHORT).show();
     //    getActivity().finish();
    }

    @Override
    public void OnItemLongClick(int position) {
        Snackbar.make(constraintLayout,getString( R.string.Delete)+" "+home_rv_dataArrayList.get(position).getTitle()+getString(R.string.question), Snackbar.LENGTH_SHORT)
                .setAction(R.string.yes, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        notesDataBase.noteDao().DeleteNote(Adapter.ReturnData(position));
                        resetdb();
                    }
                })
                .setActionTextColor(getResources().getColor(R.color.white))
                .setBackgroundTint(getResources().getColor(R.color.Golden_Theme))
                .show();
    }

    private void Drag() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN | ItemTouchHelper.UP, 0) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();

                Collections.swap(home_rv_dataArrayList, fromPosition, toPosition);
                recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);

                int id1=home_rv_dataArrayList.get(fromPosition).getId();
                int id2=home_rv_dataArrayList.get(toPosition).getId();

                home_Rv_Data d1= home_rv_dataArrayList.get(fromPosition);
                home_Rv_Data d2= home_rv_dataArrayList.get(toPosition);

                d1.setId(id2);
                d2.setId(id1);

                notesDataBase.noteDao().Update(d1);
                notesDataBase.noteDao().Update(d2);
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            }
        }).attachToRecyclerView(home_RV);
        resetdb();
    }

    @Override
    public void onResume() {
        resetdb();
        super.onResume();
    }

    public void resetdb() {
        home_rv_dataArrayList.clear();
        home_rv_dataArrayList.addAll(notesDataBase.noteDao().getAll());
        Adapter.notifyDataSetChanged();
    }
}