package com.example.android.protectednotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.room.Database;
import androidx.room.Room;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.android.protectednotes.ui.home.HomeFragment;

import org.w3c.dom.Text;

import home_DB.NoteDao;
import home_DB.NotesDataBase;
import home_RV.home_Rv_Data;

public class The_Note extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    AppCompatButton button;
    String title,text;
    int id=1,position;
    TextView Title;
    EditText Text;
    NotesDataBase notesDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_note);

        notesDataBase = Room.databaseBuilder(getApplicationContext().getApplicationContext(),NotesDataBase.class,"notes")
                .allowMainThreadQueries()
                .build();

        mediaPlayer=MediaPlayer.create(this,R.raw.click);

        Title=findViewById(R.id.Note_Title);
        Text=findViewById(R.id.Edit_Text_Note);

        title=getIntent().getStringExtra("Title");
        text=getIntent().getStringExtra("Content");
        position=getIntent().getIntExtra("id",0);



        Title.setText(title);
        Text.setText(text);

        button=findViewById(R.id.Save_Note_Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.SoundON)mediaPlayer.start();
                String newContent = Text.getText().toString();
                Replace(id,newContent,title);

          //      Toast.makeText(The_Note.this, ""+position, Toast.LENGTH_SHORT).show();
                onBackPressed();

            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void Replace(int id, String Content , String title) {
        // title ,text, position
       home_Rv_Data home_rv_dat= new home_Rv_Data(Content,title);
       home_rv_dat.setId(position);
        notesDataBase.noteDao().Update(home_rv_dat);
    }

}