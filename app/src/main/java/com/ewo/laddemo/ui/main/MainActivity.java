package com.ewo.laddemo.ui.main;

import android.os.Bundle;

import com.ewo.laddemo.R;
import com.ewo.laddemo.ui.base.BaseActivity;
import com.ewo.laddemo.ui.base.BaseActivityConfig;

public class MainActivity extends BaseActivity {

    @Override
    public void getActivityConfig(BaseActivityConfig activityConfig) {
        activityConfig.layoutId = R.layout.activity_main;
        activityConfig.fragmentContainerId = R.id.main_root;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        replaceFragment(MainFragment.getInstance(), null, false);
    }
}