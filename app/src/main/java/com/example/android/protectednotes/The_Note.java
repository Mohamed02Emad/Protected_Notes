package com.example.android.protectednotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class The_Note extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    AppCompatButton button;
    String title,text;
    TextView Title;
    EditText Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_note);

        mediaPlayer=MediaPlayer.create(this,R.raw.click);

        Title=findViewById(R.id.Note_Title);
        Text=findViewById(R.id.Edit_Text_Note);

        title=getIntent().getStringExtra("Title");
        text=getIntent().getStringExtra("Content");

        Title.setText(title);
        Text.setText(text);

        button=findViewById(R.id.Save_Note_Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.SoundON)mediaPlayer.start();
                //TODO:update data base


                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}