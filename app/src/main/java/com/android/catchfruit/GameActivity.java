package com.android.catchfruit;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView levelDisplay;
    private TextView scoreDisplay;

    private Button btn;

    private int scrWidth;
    private int scrHeight;

    private boolean isGameStopped =false;
    private static  String TAG;
    private int level=1;

    ViewGroup contentView;

    private int userScore=0;
    private int pinsUsed=0;

    private int fruitLaunched=0;
    private int fruitCaught = 0;

    private ArrayList<ImageView> pinImages = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utilities.setToFullScreen(getWindow());
        setContentView(R.layout.activity_game);

        getWindow().setBackgroundDrawableResource(R.mipmap.background);

        levelDisplay=(TextView)findViewById(R.id.level_display);
        scoreDisplay=(TextView)findViewById(R.id.score_display);
        //updateGameState();

        btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Start the level when clicked
                // startLevel();
            }
        });

        pinImages.add((ImageView)findViewById(R.id.pushpin1));
        pinImages.add((ImageView)findViewById(R.id.pushpin2));
        pinImages.add((ImageView)findViewById(R.id.pushpin3));
        pinImages.add((ImageView)findViewById(R.id.pushpin4));
        pinImages.add((ImageView)findViewById(R.id.pushpin5));
        ImageView basket = (ImageView) findViewById(R.id.basket_empty);

        contentView = (ViewGroup) findViewById(R.id.content_view);
        contentView.setOnTouchListener(new View.OnTouchListener(){
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                Log.d(TAG,"onTouch");

                launchFruit((int)(event.getX()),(int)(event.getY()));
                // set up the basket here
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    basket.setOnTouchListener(onTouchListener());
                }
                return false;
            }
        });
    }

    //use to move the basket.... Not done yet
    private View.OnTouchListener onTouchListener() {
        View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        };
        return onTouchListener;
    }

    // set window to full screen
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            Utilities.setToFullScreen(getWindow());;
        }
    }

    public void removeFruit(Fruit fruit)
    {
        contentView.removeView(fruit);
    }

    public void launchFruit(int xPos, int yPos)
    {
        Random random=new Random(new Date().getTime());

        //Overwrite
        xPos=random.nextInt(scrWidth -200);

        int maxDelay=3000;
        int minDelay=1000;
        int delay=random.nextInt(maxDelay-minDelay)+minDelay;

        Fruit ftemp=new Fruit(GameActivity.this, R.drawable.fruit_apple, 1,100);
        ftemp.setY(yPos);
        ftemp.setX(xPos);

        contentView.addView(ftemp);
        ftemp.release(scrHeight,delay);

        Log.d(TAG, "Fruit created @ x="+xPos+", y="+yPos);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Utilities.setToFullScreen(getWindow());

        ViewTreeObserver viewTreeObserver = contentView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    contentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    scrWidth = contentView.getWidth();
                    scrHeight = contentView.getHeight();
                    Log.d(TAG,"scrHeight="+scrHeight+" scrWidth="+scrWidth);
                }
            });
        }
    }
}
