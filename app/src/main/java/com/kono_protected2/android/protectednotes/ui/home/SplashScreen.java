package com.kono_protected2.android.protectednotes.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kono_protected2.android.protectednotes.login;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
             startActivity(new Intent(SplashScreen.this,login.class));
             finish();
            }
        },1500);
    }
}
