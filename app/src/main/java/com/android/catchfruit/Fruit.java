package com.android.catchfruit;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

public class Fruit extends AppCompatImageView {
    private final String TAG=getClass().getName();
    private int imgRid;
    private int point;
    private FruitListener listener;

    private GameActivity gameActivity;

    private Boolean isCaught;

    private ValueAnimator animator;

    // animation for fruit drop down
    public void release(int scrHeight, int duration) {
        animator = new ValueAnimator();
        animator.setDuration(duration);
        animator.setFloatValues(scrHeight,0f);
        animator.setInterpolator(new LinearInterpolator());
        animator.setTarget(this);

        animator.addListener(listener);
        animator.addUpdateListener(listener);
        animator.start();
    }

    public Fruit(Context context) {
        super(context);
    }

    public Fruit(Context context, int imgRid, int point){
        super(context);
        this.gameActivity=(GameActivity) context;
        this.imgRid=imgRid;
        this.point=point;
        listener=new FruitListener(this);
    }

    public Boolean isCaught(){ return isCaught; }

    public void setCaught(){ isCaught=true;}

    public void destroy(){ gameActivity.removeFruit(this); }
}
