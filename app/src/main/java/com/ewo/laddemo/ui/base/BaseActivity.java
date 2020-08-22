package com.ewo.laddemo.ui.base;

import android.os.Bundle;

import com.ewo.laddemo.EnumFragmentPage;
import com.ewo.laddemo.R;
import com.ewo.laddemo.XApp;
import com.ewo.laddemo.ui.addmovie.AddMovieFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public abstract class BaseActivity extends AppCompatActivity {

    private BaseActivityConfig activityConfig;

    public abstract void getActivityConfig(BaseActivityConfig activityConfig);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        activityConfig = new BaseActivityConfig();
        getActivityConfig(activityConfig);

        setContentView(activityConfig.layoutId);
        super.onCreate(savedInstanceState);
        overridePendingTransition(activityConfig.useAnimation ? R.anim.anim_page_open_vertical_in : 0, 0);
    }

    private FragmentEventListener fragmentEventListener = new FragmentEventListener() {
        @Override
        public void openPage(EnumFragmentPage pageEnum, BaseDataTransferObject data) {
            replaceFragment(pageEnum.generateFragment(), data, true);
        }

        @Override
        public void closeCurrentPage() {
            // Triggers the fragment pop operation automatically.
            onBackPressed();
        }
    };

    protected void replaceFragment(Fragment fragment, BaseDataTransferObject data, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        String tag = fragment.getClass().getName();
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(tag);
            if (fragment instanceof AddMovieFragment) {
                fragmentTransaction.setCustomAnimations(R.anim.anim_page_open_vertical_in, R.anim.anim_page_open_vertical_out
                        , R.anim.anim_page_close_vertical_in, R.anim.anim_page_close_vertical_out);
            } else {
                fragmentTransaction.setCustomAnimations(R.anim.anim_page_open_horizontal_in, R.anim.anim_page_open_horizontal_out
                        , R.anim.anim_page_close_horizontal_in, R.anim.anim_page_close_horizontal_out);
            }
        }
        if (data != null) {
            Bundle dataBundle = new Bundle();
            dataBundle.putSerializable(tag, data);
            fragment.setArguments(dataBundle);
        }
        fragmentTransaction.replace(activityConfig.fragmentContainerId, fragment, tag).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((XApp) getApplicationContext()).fragmentEventListener = fragmentEventListener;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, activityConfig.useAnimation ? R.anim.anim_page_close_vertical_out : 0);
    }
}
