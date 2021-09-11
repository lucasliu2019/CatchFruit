package com.android.catchfruit;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.Log;

public class FruitListener implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
    private Fruit fruit;

    public FruitListener(Fruit fruit){ this.fruit=fruit; }

    @Override
    public void onAnimationStart(Animator animator) {
    }

    @Override
    public void onAnimationEnd(Animator animator) {
        Log.d("Anima","End"+fruit.getY());
        if(!fruit.isCaught()) {
            fruit.destroy();
        }
    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        Log.d("Anima","Update"+fruit.getY()+"Obj"+fruit);
        fruit.setY((float)valueAnimator.getAnimatedValue());
    }
}
