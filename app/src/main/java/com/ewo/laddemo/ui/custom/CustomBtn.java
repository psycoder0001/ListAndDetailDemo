package com.ewo.laddemo.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.appcompat.widget.AppCompatButton;

public class CustomBtn extends AppCompatButton {
    public CustomBtn(@NonNull Context context) {
        super(context);
        init(null);
    }

    public CustomBtn(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomBtn(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
    }

    public void setTextSizePx(@Px int sizeInPx) {
        setTextSize(TypedValue.COMPLEX_UNIT_PX, sizeInPx);
    }

    public void setTextSizeSp(int sizeInSp) {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, sizeInSp);
    }

    public void setTextSizeDim(@DimenRes int resId) {
        setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(resId));
    }

    public void setTextColorClr(@ColorRes int resId) {
        setTextColor(getResources().getColor(resId));
    }
}
