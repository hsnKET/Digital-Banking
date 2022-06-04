package com.ketlas.ebanking.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.ketlas.ebanking.R;


public class AnimationManager {

    private static final int DEFAULT_REVEAL_ANIMATION_DURATION = 400;
    public static void scale(Context context,View v){
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale);
        v.startAnimation(animation);
    }


    public static void RevealAnim(View v, AnimatorListenerAdapter animatorListenerAdapter){
        AnimationManager.RevealAnim(v,animatorListenerAdapter, RevealType.DIRECT,DEFAULT_REVEAL_ANIMATION_DURATION);
    }
    public static void RevealAnim(View v, AnimatorListenerAdapter animatorListenerAdapter,RevealType revealType){
        AnimationManager.RevealAnim(v,animatorListenerAdapter,revealType,DEFAULT_REVEAL_ANIMATION_DURATION);
    }
    public static void RevealAnim(View v, AnimatorListenerAdapter animatorListenerAdapter,RevealType revealType,int duration){
        v.post(new Runnable() {
            @Override
            public void run() {
                int cx = v.getRight();
                int cy = v.getBottom();
                int endradius = Math.max(v.getWidth(), v.getHeight());
                int startRadius = (revealType == RevealType.DIRECT)?0:endradius;
                endradius = startRadius == 0 ? endradius:0;
                Animator animator = ViewAnimationUtils.createCircularReveal(v,cx,cy,startRadius,endradius);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.setDuration(duration);
                if (animatorListenerAdapter!=null)
                animator.addListener(animatorListenerAdapter);
                animator.start();

            }
        });

    }
    public static Animation slideUP(Context context,View v){
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_up);
        v.startAnimation(animation);
        return animation;
    }
    public static Animation slideDOWN(Context context,View v){
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_down);
        v.startAnimation(animation);
        return animation;
    }
    public enum RevealType{
        DIRECT,
        REVERSE;
    }
}
