package com.example.android.protectednotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class login extends AppCompatActivity {

    Button button;
    EditText editText;
    boolean DarkMode;
    SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoadLang();
        setContentView(R.layout.activity_login);
        button=findViewById(R.id.btnLogin);
        editText=findViewById(R.id.password_Txt);
        SharedPreferences sp=getSharedPreferences("SettingsModes",Context.MODE_PRIVATE);
        DarkMode=sp.getBoolean("Dark",true);
        if(DarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
//        shared=getSharedPreferences("LoginData", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor=shared.edit();
//        editor.putString("login","0000");
//        editor.commit();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref=getSharedPreferences("logindatapred",MODE_PRIVATE);
                String password=pref.getString("Password","0000");
                if(editText.getText().toString().equals(password)){
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(login.this, getString(R.string.wrongPass), Toast.LENGTH_SHORT).show();
                    editText.setText("");
                }

            }
        });
    }

    public void LoadLang(){
        SharedPreferences pref=getSharedPreferences("Language",MODE_PRIVATE);
        String lang=pref.getString("AppLang","");
        setLocal(lang);

    }

    private void setLocal(String s){
        Locale locale =new Locale(s);
        Locale.setDefault(locale);

        Configuration configuration=new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,
                getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor=getSharedPreferences("Language",Context.MODE_PRIVATE).edit();
        editor.putString("AppLang",s);
        editor.commit();
    }
}