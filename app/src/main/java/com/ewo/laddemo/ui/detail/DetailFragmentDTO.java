package com.ewo.laddemo.ui.detail;

import com.ewo.laddemo.ui.base.BaseDataTransferObject;

public class DetailFragmentDTO extends BaseDataTransferObject {
    public int movieId;

    public DetailFragmentDTO(int movieId) {
        this.movieId = movieId;
    }
}
