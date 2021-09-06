package com.android.catchfruit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utilities.setToFullScreen(getWindow());
        setContentView(R.layout.activity_main);
    }

    public void startGame(View view){
        Intent gameIntent=new Intent(this,GameActivity.class);
        startActivity(gameIntent);
    }

    public void aboutGame(View view){

    }

    // set window to full screen
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            Utilities.setToFullScreen(getWindow());;
        }
    }
}