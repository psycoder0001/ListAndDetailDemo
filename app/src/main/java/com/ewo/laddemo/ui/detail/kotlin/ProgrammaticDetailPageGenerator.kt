package com.ewo.laddemo.ui.detail.kotlin

import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import com.ewo.laddemo.R
import com.ewo.laddemo.ui.custom.CustomBtn
import com.ewo.laddemo.ui.custom.CustomIv
import com.ewo.laddemo.ui.custom.CustomTv

class ProgrammaticDetailPageGenerator {
    companion object {

        // LAYOUT SIZE SHORTCUTS
        private const val MATCH_PARENT: Int = ConstraintLayout.LayoutParams.MATCH_PARENT
        private const val WRAP_CONTENT: Int = ConstraintLayout.LayoutParams.WRAP_CONTENT
        private const val DYNAMIC_SIZE: Int = 0

        // CONSTRAINT LAYOUT PARAMS SHORTCUTS
        private const val PARENT: Int = ConstraintSet.PARENT_ID
        private const val START: Int = ConstraintSet.START
        private const val END: Int = ConstraintSet.END
        private const val TOP: Int = ConstraintSet.TOP
        private const val BOTTOM: Int = ConstraintSet.BOTTOM

        @JvmStatic
        fun generateDetailPageUi(rootConstraintLayout: ConstraintLayout): View {
            val sizeUnit = rootConstraintLayout.resources.getDimensionPixelSize(R.dimen.oneDp)
            val padding = sizeUnit * 12;

            val rootLayoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            rootConstraintLayout.layoutParams = rootLayoutParams
            rootConstraintLayout.setPadding(padding, padding, padding, padding)
            generateHeaderArea(rootConstraintLayout, sizeUnit)
            generateFooterArea(rootConstraintLayout, sizeUnit)
            generateInfoArea(rootConstraintLayout, sizeUnit)
            generatePhotoArea(rootConstraintLayout, sizeUnit)
            return rootConstraintLayout
        }

        // ====================================================================
        // GENERATE HEADER COMPONENTS
        private fun generateHeaderArea(parent: ConstraintLayout, sizeUnit: Int) {
            // GENERATE TITLE TV
            val titleTv = CustomTv(parent.context)
            titleTv.id = R.id.detail_title_tv
            parent.addView(titleTv)
            // set usual params
            val layoutParams = ConstraintLayout.LayoutParams(DYNAMIC_SIZE, WRAP_CONTENT)
            titleTv.layoutParams = layoutParams
            titleTv.setPadding(sizeUnit * 12)
            titleTv.gravity = Gravity.CENTER
            titleTv.setTextColorClr(R.color.text_header)
            titleTv.setTextSizeSp(24)
            titleTv.typeface = Typeface.DEFAULT_BOLD
            // set constraints
            val titleConstraints = ConstraintSet()
            titleConstraints.clone(parent)
            titleConstraints.connect(titleTv.id, TOP, PARENT, TOP)
            titleConstraints.connect(titleTv.id, START, PARENT, START, sizeUnit * 80)
            titleConstraints.connect(titleTv.id, END, PARENT, END, sizeUnit * 80)
            titleConstraints.applyTo(parent)

            // GENERATE DELETE BTN
            val deleteBtn = CustomBtn(parent.context)
            deleteBtn.id = R.id.detail_delete_btn
            parent.addView(deleteBtn)
            // set usual params
            val deleteParams = ConstraintLayout.LayoutParams(sizeUnit * 32, sizeUnit * 32)
            deleteParams.setMargins(sizeUnit * 24)
            deleteBtn.layoutParams = deleteParams
            deleteBtn.setBackgroundResource(R.drawable.baseline_delete_white_36)
            // set constraints
            val deleteConstraints = ConstraintSet()
            deleteConstraints.clone(parent)
            deleteConstraints.connect(deleteBtn.id, TOP, R.id.detail_title_tv, TOP)
            deleteConstraints.connect(deleteBtn.id, END, PARENT, END)
            deleteConstraints.connect(deleteBtn.id, BOTTOM, R.id.detail_title_tv, BOTTOM)
            deleteConstraints.applyTo(parent)
        }
        // GENERATE HEADER COMPONENTS
        // ====================================================================

        // ====================================================================
        // GENERATE PHOTO COMPONENTS
        private fun generatePhotoArea(parent: ConstraintLayout, sizeUnit: Int) {
            val photoIv = CustomIv(parent.context)
            photoIv.id = R.id.detail_photo_iv
            parent.addView(photoIv)
            // set usual params
            val photoParams = ConstraintLayout.LayoutParams(MATCH_PARENT, DYNAMIC_SIZE)
            photoParams.bottomMargin = sizeUnit * 12
            photoIv.layoutParams = photoParams
            photoIv.setBackgroundResource(R.color.bgWindow)
            photoIv.scaleType = ImageView.ScaleType.FIT_CENTER
            // set constraints
            val deleteConstraints = ConstraintSet()
            deleteConstraints.clone(parent)
            deleteConstraints.connect(photoIv.id, TOP, R.id.detail_title_tv, BOTTOM)
            deleteConstraints.connect(photoIv.id, START, PARENT, START)
            deleteConstraints.connect(photoIv.id, END, PARENT, END)
            deleteConstraints.connect(photoIv.id, BOTTOM, R.id.detail_separator, BOTTOM)
            deleteConstraints.applyTo(parent)
        }
        // GENERATE PHOTO COMPONENTS
        // ====================================================================

        // ====================================================================
        // GENERATE DETAILED INFO COMPONENTS
        private fun generateInfoArea(parent: ConstraintLayout, sizeUnit: Int) {
            // GENERATE HORIZONTAL SEPARATOR V
            val horizontalSeparatorV = View(parent.context)
            horizontalSeparatorV.id = R.id.detail_separator
            parent.addView(horizontalSeparatorV)
            // set usual params
            val horizontalSeparatorParams = ConstraintLayout.LayoutParams(MATCH_PARENT, sizeUnit)
            horizontalSeparatorParams.bottomMargin = sizeUnit * 96
            horizontalSeparatorV.layoutParams = horizontalSeparatorParams
            horizontalSeparatorV.setBackgroundResource(R.drawable.circle)
            // set constraints
            val horizontalSeparatorConstraints = ConstraintSet()
            horizontalSeparatorConstraints.clone(parent)
            horizontalSeparatorConstraints.connect(horizontalSeparatorV.id, START, PARENT, START, sizeUnit * 32)
            horizontalSeparatorConstraints.connect(horizontalSeparatorV.id, END, PARENT, END, sizeUnit * 32)
            horizontalSeparatorConstraints.connect(horizontalSeparatorV.id, BOTTOM, R.id.detail_counter_type_val, TOP, sizeUnit * 96)
            horizontalSeparatorConstraints.applyTo(parent)

            // GENERATE VERTICAL LEFT SEPARATOR V
            val verticalLeftSeparatorV = View(parent.context)
            verticalLeftSeparatorV.id = R.id.detail_ver_separator_l
            parent.addView(verticalLeftSeparatorV)
            // set usual params
            val verticalLeftSeparatorParams = ConstraintLayout.LayoutParams(sizeUnit, sizeUnit * 48)
            verticalLeftSeparatorParams.bottomMargin = sizeUnit * 96
            verticalLeftSeparatorV.layoutParams = verticalLeftSeparatorParams
            verticalLeftSeparatorV.setBackgroundResource(R.drawable.circle_dark)
            // set constraints
            val verticalLeftSeparatorConstraints = ConstraintSet()
            verticalLeftSeparatorConstraints.clone(parent)
            verticalLeftSeparatorConstraints.connect(verticalLeftSeparatorV.id, TOP, R.id.detail_separator, BOTTOM, sizeUnit * 16)
            verticalLeftSeparatorConstraints.connect(verticalLeftSeparatorV.id, START, PARENT, START)
            verticalLeftSeparatorConstraints.connect(verticalLeftSeparatorV.id, END, PARENT, END)
            verticalLeftSeparatorConstraints.setHorizontalBias(verticalLeftSeparatorV.id, 0.33f)
            verticalLeftSeparatorConstraints.applyTo(parent)

            // GENERATE VERTICAL RIGHT SEPARATOR V
            val verticalRightSeparatorV = View(parent.context)
            verticalRightSeparatorV.id = R.id.detail_ver_separator_r
            parent.addView(verticalRightSeparatorV)
            // set usual params
            val verticalRightSeparatorParams = ConstraintLayout.LayoutParams(sizeUnit, sizeUnit * 48)
            verticalRightSeparatorParams.bottomMargin = sizeUnit * 96
            verticalRightSeparatorV.layoutParams = verticalRightSeparatorParams
            verticalRightSeparatorV.setBackgroundResource(R.drawable.circle_dark)
            // set constraints
            val verticalRightSeparatorConstraints = ConstraintSet()
            verticalRightSeparatorConstraints.clone(parent)
            verticalRightSeparatorConstraints.connect(verticalRightSeparatorV.id, TOP, R.id.detail_separator, BOTTOM, sizeUnit * 16)
            verticalRightSeparatorConstraints.connect(verticalRightSeparatorV.id, START, PARENT, START)
            verticalRightSeparatorConstraints.connect(verticalRightSeparatorV.id, END, PARENT, END)
            verticalRightSeparatorConstraints.setHorizontalBias(verticalRightSeparatorV.id, 0.66f)
            verticalRightSeparatorConstraints.applyTo(parent)

            // GENERATE RATING LBL
            val ratingLblTv = CustomTv(parent.context)
            // Set params via style
            ratingLblTv.setTextAppearance(R.style.style_detail_lbl)
            ratingLblTv.id = R.id.detail_rating_lbl
            ratingLblTv.setText(R.string.rating)
            parent.addView(ratingLblTv)
            // Params can be this way too but it is way too slower
//            val ratingLblParams = ConstraintLayout.LayoutParams(DYNAMIC_SIZE, WRAP_CONTENT)
//            ratingLblParams.marginStart = sizeUnit * 12
//            ratingLblParams.marginEnd = sizeUnit * 12
//            ratingLblTv.layoutParams = ratingLblParams
//            ratingLblTv.gravity = Gravity.CENTER
//            ratingLblTv.setTextColorClr(R.color.text_lbl)
//            ratingLblTv.setTextSizeSp(16)
            // set constraints
            val ratingLblConstraints = ConstraintSet()
            ratingLblConstraints.clone(parent)
            ratingLblConstraints.connect(ratingLblTv.id, START, PARENT, START)
            ratingLblConstraints.connect(ratingLblTv.id, END, R.id.detail_ver_separator_l, START)
            ratingLblConstraints.connect(ratingLblTv.id, BOTTOM, R.id.detail_ver_separator_l, BOTTOM)
            ratingLblConstraints.applyTo(parent)

            // GENERATE RATING VAL
            val ratingValTv = CustomTv(parent.context)
            // Set params via style
            ratingValTv.setTextAppearance(R.style.style_detail_val)
            ratingValTv.id = R.id.detail_rating_val
            parent.addView(ratingValTv)
            // set constraints
            val ratingValConstraints = ConstraintSet()
            ratingValConstraints.clone(parent)
            ratingValConstraints.connect(ratingValTv.id, START, R.id.detail_rating_lbl, START)
            ratingValConstraints.connect(ratingValTv.id, END, R.id.detail_rating_lbl, END)
            ratingValConstraints.connect(ratingValTv.id, BOTTOM, R.id.detail_rating_lbl, TOP)
            ratingValConstraints.applyTo(parent)

            // GENERATE YEAR LBL
            val yearLblTv = CustomTv(parent.context)
            // Set params via style
            yearLblTv.setTextAppearance(R.style.style_detail_lbl)
            yearLblTv.id = R.id.detail_year_lbl
            yearLblTv.setText(R.string.year)
            parent.addView(yearLblTv)
            // set constraints
            val yearLblConstraints = ConstraintSet()
            yearLblConstraints.clone(parent)
            yearLblConstraints.connect(yearLblTv.id, START, R.id.detail_ver_separator_l, END)
            yearLblConstraints.connect(yearLblTv.id, END, R.id.detail_ver_separator_r, START)
            yearLblConstraints.connect(yearLblTv.id, BOTTOM, R.id.detail_ver_separator_l, BOTTOM)
            yearLblConstraints.applyTo(parent)

            // GENERATE YEAR VAL
            val yearValTv = CustomTv(parent.context)
            // Set params via style
            yearValTv.setTextAppearance(R.style.style_detail_val)
            yearValTv.id = R.id.detail_year_val
            parent.addView(yearValTv)
            // set constraints
            val yearValConstraints = ConstraintSet()
            yearValConstraints.clone(parent)
            yearValConstraints.connect(yearValTv.id, START, R.id.detail_year_lbl, START)
            yearValConstraints.connect(yearValTv.id, END, R.id.detail_year_lbl, END)
            yearValConstraints.connect(yearValTv.id, BOTTOM, R.id.detail_year_lbl, TOP)
            yearValConstraints.applyTo(parent)

            // GENERATE VIEWED LBL
            val viewedLblTv = CustomTv(parent.context)
            // Set params via style
            viewedLblTv.setTextAppearance(R.style.style_detail_lbl)
            viewedLblTv.id = R.id.detail_viewed_lbl
            viewedLblTv.setText(R.string.viewed)
            parent.addView(viewedLblTv)
            // set constraints
            val viewedLblConstraints = ConstraintSet()
            viewedLblConstraints.clone(parent)
            viewedLblConstraints.connect(viewedLblTv.id, START, R.id.detail_ver_separator_r, END)
            viewedLblConstraints.connect(viewedLblTv.id, END, PARENT, END)
            viewedLblConstraints.connect(viewedLblTv.id, BOTTOM, R.id.detail_ver_separator_l, BOTTOM)
            viewedLblConstraints.applyTo(parent)

            // GENERATE VIEWED VAL
            val viewedValTv = CustomTv(parent.context)
            // Set params via style
            viewedValTv.setTextAppearance(R.style.style_detail_val)
            viewedValTv.id = R.id.detail_viewed_val
            parent.addView(viewedValTv)
            // set constraints
            val viewedValConstraints = ConstraintSet()
            viewedValConstraints.clone(parent)
            viewedValConstraints.connect(viewedValTv.id, START, R.id.detail_viewed_lbl, START)
            viewedValConstraints.connect(viewedValTv.id, END, R.id.detail_viewed_lbl, END)
            viewedValConstraints.connect(viewedValTv.id, BOTTOM, R.id.detail_viewed_lbl, TOP)
            viewedValConstraints.applyTo(parent)
        }
        // GENERATE DETAILED INFO COMPONENTS
        // ====================================================================

        // ====================================================================
        // GENERATE FOOTER COMPONENTS
        private fun generateFooterArea(parent: ConstraintLayout, sizeUnit: Int) {
            // GENERATE DELETE BTN
            val counterIncBtn = CustomBtn(parent.context)
            counterIncBtn.id = R.id.detail_counter_increase_btn
            counterIncBtn.visibility = View.GONE
            parent.addView(counterIncBtn)
            // set usual params
            val counterIncParams = ConstraintLayout.LayoutParams(sizeUnit * 32, sizeUnit * 32)
            counterIncParams.setMargins(sizeUnit * 12)
            counterIncBtn.layoutParams = counterIncParams
            counterIncBtn.setBackgroundResource(R.drawable.baseline_add_circle_white_36)
            // set constraints
            val counterIncConstraints = ConstraintSet()
            counterIncConstraints.clone(parent)
            counterIncConstraints.connect(counterIncBtn.id, START, PARENT, START)
            counterIncConstraints.connect(counterIncBtn.id, END, PARENT, END)
            counterIncConstraints.connect(counterIncBtn.id, BOTTOM, PARENT, BOTTOM)
            counterIncConstraints.applyTo(parent)

            // GENERATE COUNTER TYPE LBL
            val counterTypeLblTv = CustomTv(parent.context)
            // set margin
//            val counterTypeLblParams = ConstraintLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
//            counterTypeLblParams.setMargins(sizeUnit * 12)
//            counterTypeLblTv.layoutParams = counterTypeLblParams
            // Set params via style
            counterTypeLblTv.setTextAppearance(R.style.style_detail_lbl)
            counterTypeLblTv.id = R.id.detail_counter_type_lbl
            counterTypeLblTv.setText(R.string.counter_type)
            parent.addView(counterTypeLblTv)
            // set constraints
            val counterTypeLblConstraints = ConstraintSet()
            counterTypeLblConstraints.clone(parent)
            counterTypeLblConstraints.connect(counterTypeLblTv.id, START, PARENT, START)
            counterTypeLblConstraints.connect(counterTypeLblTv.id, END, PARENT, END)
            counterTypeLblConstraints.connect(counterTypeLblTv.id, BOTTOM, R.id.detail_counter_increase_btn, TOP)
            counterTypeLblConstraints.applyTo(parent)

            // GENERATE COUNTER TYPE VAL
            val counterTypeValTv = CustomTv(parent.context)
            // set margin
//            val counterTypeValParams =  ConstraintLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
//            counterTypeValParams.setMargins(sizeUnit * 12)
//            counterTypeValTv.layoutParams = counterTypeLblParams
            // Set params via style
            counterTypeValTv.setTextAppearance(R.style.style_detail_val)
            counterTypeValTv.id = R.id.detail_counter_type_val
            parent.addView(counterTypeValTv)
            // set constraints
            val counterTypeValConstraints = ConstraintSet()
            counterTypeValConstraints.clone(parent)
            counterTypeValConstraints.connect(counterTypeValTv.id, START, PARENT, START)
            counterTypeValConstraints.connect(counterTypeValTv.id, END, PARENT, END)
            counterTypeValConstraints.connect(counterTypeValTv.id, BOTTOM, R.id.detail_counter_type_lbl, TOP)
            counterTypeValConstraints.applyTo(parent)
        }
        // GENERATE FOOTER COMPONENTS
        // ====================================================================
    }
}