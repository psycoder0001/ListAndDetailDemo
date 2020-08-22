package com.ewo.laddemo;

import com.ewo.laddemo.ui.detail.kotlin.DetailFragmentKt;
import com.ewo.laddemo.ui.main.MainFragment;
import com.ewo.laddemo.ui.addmovie.AddMovieFragment;

import androidx.fragment.app.Fragment;

public enum EnumFragmentPage {
    MAIN, DETAIL_KT, ADD_ITEM;

    public Fragment generateFragment() {
        Fragment fragment = null;
        switch (this) {
            case MAIN:
                fragment = MainFragment.instantiate();
                break;
            case DETAIL_KT:
                fragment = DetailFragmentKt.instantiate();
                break;
            case ADD_ITEM:
                fragment = AddMovieFragment.instantiate();
                break;
        }
        return fragment;
    }
}
