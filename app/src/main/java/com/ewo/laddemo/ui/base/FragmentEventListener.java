package com.ewo.laddemo.ui.base;

import com.ewo.laddemo.EnumFragmentPage;

public interface FragmentEventListener {
    void openPage(EnumFragmentPage pageEnum, BaseDataTransferObject data);

    void closeCurrentPage();
}