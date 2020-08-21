package com.ewo.laddemo.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ewo.laddemo.EnumFragmentPage;
import com.ewo.laddemo.XApp;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    private BaseFragmentConfig fragmentConfig;

    public abstract void getFragmentConfig(BaseFragmentConfig fragmentConfig);

    public void onDataReceived(BaseDataTransferObject data) {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Get configurations for this fragment.
        fragmentConfig = new BaseFragmentConfig();
        getFragmentConfig(fragmentConfig);

        // Find data transferred to this fragment.
        if (getArguments() != null) {
            Serializable serializableData = getArguments().getSerializable(getTag());
            if (serializableData != null) {
                BaseDataTransferObject data = (BaseDataTransferObject) serializableData;
                onDataReceived(data);
            }
        }

        // Initialize fragment view
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(fragmentConfig.layoutId, container, false);
    }

    public XApp getAppContext() {
        if (getActivity() != null) {
            return ((XApp) getActivity().getApplicationContext());
        }
        return XApp.get();
    }

    public void openPage(EnumFragmentPage fragmentPage, BaseDataTransferObject data) {
        getAppContext().fragmentEventListener.openPage(fragmentPage, data);
    }

    public void closePage() {
        if (getActivity() != null) {
            getAppContext().fragmentEventListener.closeCurrentPage();
        }
        // page is already dead or is about to die.
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
