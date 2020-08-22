package com.ewo.laddemo.ui.main;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;

import com.ewo.laddemo.R;
import com.ewo.laddemo.ui.base.BaseActivity;
import com.ewo.laddemo.ui.base.BaseActivityConfig;

public class MainActivity extends BaseActivity {

    @Override
    public void getActivityConfig(BaseActivityConfig activityConfig) {
        activityConfig.layoutId = R.layout.activity_main;
        activityConfig.fragmentContainerId = R.id.main_fragment_container;
        activityConfig.useAnimation = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        replaceFragment(MainFragment.instantiate(), null, false);
        fadeOutWhiteCurtain(findViewById(R.id.main_white_curtain));
    }

    private void fadeOutWhiteCurtain(View whiteCurtainV) {
        ObjectAnimator whiteCurtainFadeOutAnimation = ObjectAnimator.ofFloat(whiteCurtainV, View.ALPHA, 1, 0);
        whiteCurtainFadeOutAnimation.setDuration(getResources().getInteger(R.integer.animation_duration));
        whiteCurtainFadeOutAnimation.setStartDelay(getResources().getInteger(R.integer.animation_start_offset) * 10);
        whiteCurtainFadeOutAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                whiteCurtainV.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        whiteCurtainFadeOutAnimation.start();
    }
}