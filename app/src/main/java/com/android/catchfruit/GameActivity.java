package com.android.catchfruit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utilities.setToFullScreen(getWindow());
        setContentView(R.layout.activity_game);
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
