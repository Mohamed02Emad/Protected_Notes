package com.example.android.protectednotes;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Settings extends Fragment {
    MediaPlayer ClickSound;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Settings() {
        // Required empty public constructor
    }

    public static Settings newInstance(String param1, String param2) {
        Settings fragment = new Settings();
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

        return inflater.inflate(R.layout.fragment_settings, container, false);
    }



    SharedPreferences Settings_Modes;
  public   Switch SoundMode,sw,screenOn,RemoveAds,Notifications;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ClickSound = MediaPlayer.create(getActivity(),R.raw.click);

        sw =view.findViewById(R.id.DarkMode);
        screenOn = view.findViewById(R.id.ScreenOn_Switch);
        SoundMode=view.findViewById(R.id.Sound_Switch);
        RemoveAds=view.findViewById(R.id.RemoveAds_Switch);
        Notifications=view.findViewById(R.id.Notifications_Switch);


        SettingsSetUp();

        Settings_Modes=getActivity().getSharedPreferences("SettingsModes", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=Settings_Modes.edit();



        //Todo: remove ads and notifications are empty

        //Remove Ads
        RemoveAds.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(MainActivity.SoundON) ClickSound.start();

                if(b){
                    editor.putBoolean("REMOVEads",true);

                }else{
                    editor.putBoolean("REMOVEads",false);

                }
                editor.commit();
            }
        });




        //notifications
        Notifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(MainActivity.SoundON) ClickSound.start();

                if(b){
                    editor.putBoolean("Notification",true);

                }else{
                    editor.putBoolean("Notification",false);

                }
                editor.commit();
            }
        });



        //Dark Mode
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(MainActivity.SoundON) ClickSound.start();

                if(b){
                    editor.putBoolean("Dark",true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else{
                    editor.putBoolean("Dark",false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                editor.commit();
            }
        });

        //Keep screen on
        screenOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(MainActivity.SoundON)ClickSound.start();

                if(b){
                    editor.putBoolean("ScreenOn",true);
                    getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);}
                else {
                    editor.putBoolean("ScreenOn",false);
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                }
                  editor.commit();
            }
        });


        //Sound On/Off
       SoundMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if (SoundMode.isChecked()) {
                   editor.putBoolean("Sounds",true);
                   ClickSound.start();
                   SoundMode.setChecked(true);
                   MainActivity.SoundON=true;
               } else {
                   editor.putBoolean("Sounds",false);
                   SoundMode.setChecked(false);
                   MainActivity.SoundON=false;
               }
               editor.commit();
           }
       });


    }


   public  void SettingsSetUp(){
        if(MainActivity.DarkMode) {
            sw.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            sw.setChecked(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        if(MainActivity.Sounds) {
            SoundMode.setChecked(true);
            MainActivity.SoundON=true;
        } else{
            SoundMode.setChecked(false);
            MainActivity.SoundON=false;
        }

        if(MainActivity.Screen){
            screenOn.setChecked(true);
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        } else{
            screenOn.setChecked(false);
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }

        if(MainActivity.removeAds) {
            RemoveAds.setChecked(true);
        }
        else{
            RemoveAds.setChecked(false);
        }

        if(MainActivity.notifications) {
            Notifications.setChecked(true);
        }else{
            Notifications.setChecked(false);
        }

    }

}