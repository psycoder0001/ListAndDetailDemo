package com.ewo.laddemo;

import com.ewo.laddemo.ui.detail.DetailFragment;
import com.ewo.laddemo.ui.main.MainFragment;

import androidx.fragment.app.Fragment;

public enum EnumFragmentPage {
    MAIN, DETAIL;

    public Fragment generateFragment() {
        Fragment fragment = null;
        switch (this) {
            case MAIN:
                fragment = MainFragment.getInstance();
                break;
            case DETAIL:
                fragment = DetailFragment.getInstance();
                break;
        }
        return fragment;
    }
}
