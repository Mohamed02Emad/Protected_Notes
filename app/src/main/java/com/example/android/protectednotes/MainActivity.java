package com.example.android.protectednotes;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.WindowManager;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.android.protectednotes.databinding.ActivityMainBinding;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {



    public static boolean resetDB;
    public static boolean usePassword=true;
    public static boolean SoundON =true;
    public static boolean  NotificationsOn=true;
    public static boolean  Remove=false;
    public static boolean DarkMode,Sounds,Screen,notifications,removeAds;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoadLang();


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_Settings, R.id.to_do)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        setUpSettings();
        setUp2();
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

    private void setUp2() {

        if(MainActivity.DarkMode) {

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        if(MainActivity.Sounds) {
            MainActivity.SoundON=true;
        } else{
            MainActivity.SoundON=false;
        }

        if(MainActivity.Screen) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else{
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }

        if(MainActivity.notifications) {
            MainActivity.NotificationsOn=true;
        } else{
            MainActivity.NotificationsOn=false;
        }

        //TODO: remmove ads
        if(MainActivity.  removeAds) {
            MainActivity.Remove=true;
        } else{
            MainActivity.Remove=false;
        }

    }

    public void setUpSettings() {
        SharedPreferences sp=getSharedPreferences("SettingsModes",Context.MODE_PRIVATE);
        DarkMode=sp.getBoolean("Dark",true);
        Screen=sp.getBoolean("ScreenOn",false);
        Sounds=sp.getBoolean("Sounds",true);
        notifications=sp.getBoolean("Notification",true);
        removeAds=sp.getBoolean("REMOVEads",false);
        usePassword=sp.getBoolean("UsePassword",true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}