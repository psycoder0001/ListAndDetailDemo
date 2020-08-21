package com.ewo.laddemo.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.ewo.laddemo.R;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomRecyclerView extends RecyclerView {
    private CustomGridLayoutManager gridLayoutManager;
    private int countOfColumns;
    public boolean isReverse;

    public CustomRecyclerView(Context context) {
        super(context);
        init(context, null);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomRecyclerView);
            try {
                countOfColumns = ta.getInt(R.styleable.CustomRecyclerView_columnCount, 1);
            } catch (Exception e) {
                countOfColumns = 1;
            }
            ta.recycle();
        }
        gridLayoutManager = new CustomGridLayoutManager(context, countOfColumns, LinearLayoutManager.VERTICAL, isReverse);
        setLayoutManager(gridLayoutManager);
    }

    public void setGridType(Context context, int countOfColumns, CustomGridLayoutManager.EndlessScrollListener listener) {
        gridLayoutManager = new CustomGridLayoutManager(context, countOfColumns, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setListener(listener);
        setLayoutManager(gridLayoutManager);
    }

    public void setListener(CustomGridLayoutManager.EndlessScrollListener listener) {
        gridLayoutManager.setListener(listener);
    }

    public GridLayoutManager getLayoutManager() {
        return gridLayoutManager;
    }
}