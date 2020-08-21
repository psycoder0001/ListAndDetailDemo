package com.ewo.laddemo.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.ewo.laddemo.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class CustomIv extends AppCompatImageView {
    private int filterColor = 0;
    private boolean isColorFiltered = false;
    private int roundAmount;
    private Path path = new Path();
    private boolean isPathValid;

    public CustomIv(Context context) {
        super(context);
        init(context, null);
    }

    public CustomIv(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomIv(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setContentDescription(" ");
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomIv);
        filterColor = typedArray.getColor(R.styleable.CustomIv_customColor, 0);
        typedArray.recycle();

        filterColor(filterColor);
    }

    public void filterColor(int filterColor) {
        this.filterColor = filterColor;
        applyFilterColor();
    }

    private void applyFilterColor() {
        if (filterColor != 0) {
            isColorFiltered = true;
            customizeColor(filterColor, getDrawable());
        } else if (isColorFiltered) {
            isColorFiltered = false;
            clearCustomColor(getDrawable());
        }
    }

    @Override
    public void setImageResource(@DrawableRes int resId) {
        super.setImageResource(resId);
        filterColor(filterColor);
    }

    @Override
    public void setImageURI(@Nullable Uri uri) {
        super.setImageURI(uri);
        filterColor(filterColor);
    }

    public void loadImageUrl(String url) {
        loadImageUrl(url, 0, 0);
    }

    public void loadImageUrl(String url, @DrawableRes int placeHolder, @DrawableRes int errorImg) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        RequestCreator requestCreator = Picasso.with(getContext().getApplicationContext()).load(url);
        if (placeHolder != 0) {
            requestCreator.placeholder(placeHolder);
        }
        if (errorImg != 0) {
            requestCreator.error(errorImg);
        }
        requestCreator.into(this);
        filterColor(filterColor);
    }

    public static void customizeColor(int filterColor, Drawable drawable) {
        if (filterColor != 0 && drawable != null) {
            drawable.mutate().setColorFilter(filterColor, PorterDuff.Mode.SRC_IN);
        }
    }

    public static void clearCustomColor(Drawable drawable) {
        if (drawable != null) {
            drawable.mutate().clearColorFilter();
        }
    }

    public void setRounded(int roundAmount) {
        this.roundAmount = roundAmount;
        postInvalidate();
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (roundAmount != 0) {
            canvas.clipPath(getRoundRectPath());
            super.dispatchDraw(canvas);
            return;
        }
        super.dispatchDraw(canvas);
    }

    @Override
    public void draw(Canvas canvas) {
        if (roundAmount != 0) {
            canvas.clipPath(getRoundRectPath());
            super.draw(canvas);
            return;
        }
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (roundAmount != 0) {
            canvas.clipPath(getRoundRectPath());
            super.onDraw(canvas);
            return;
        }
        super.onDraw(canvas);
    }

    private Path getRoundRectPath() {
        if (isPathValid) {
            return path;
        }

        path.reset();

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        RectF bounds = new RectF(0, 0, width, height);
//        RectF bounds = new RectF(0, 0, width, height);

        path.addRoundRect(bounds, roundAmount, roundAmount, Path.Direction.CCW);
        isPathValid = true;
        return path;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int oldWidth = getMeasuredWidth();
        int oldHeight = getMeasuredHeight();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int newWidth = getMeasuredWidth();
        int newHeight = getMeasuredHeight();
        if (newWidth != oldWidth || newHeight != oldHeight) {
            isPathValid = false;
        }
    }
}
