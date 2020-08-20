package com.ewo.laddemo.helpers;

import android.view.View;

/**
 * This is a custom click listener class.
 * <p>
 * It helps view to be clicked only once ins a short period of time.
 * <p>
 * Multiple clicks on the same button can create inconsistencies, crashes
 * or performance issues in some cases.
 */
public abstract class CustomOnClickListener implements View.OnClickListener {
    public abstract void onClickView(View view);

    @Override
    public void onClick(View view) {
        if (MultiClickChecker.isMultiClick(view)) {
            return;
        }
        onClickView(view);
    }
}
