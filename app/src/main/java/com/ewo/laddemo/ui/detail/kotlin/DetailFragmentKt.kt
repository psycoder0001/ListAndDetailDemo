package com.ewo.laddemo.ui.detail.kotlin

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ewo.laddemo.R
import com.ewo.laddemo.helpers.CustomOnClickListener
import com.ewo.laddemo.ui.detail.kotlin.ProgrammaticDetailPageGenerator.Companion.generateDetailPageUi
import com.ewo.laddemo.localdb.EnumViewCounterType
import com.ewo.laddemo.localdb.MovieModel
import com.ewo.laddemo.ui.base.BaseDataTransferObject
import com.ewo.laddemo.ui.base.BaseFragment
import com.ewo.laddemo.ui.base.BaseFragmentConfig
import com.ewo.laddemo.ui.custom.CustomIv
import com.ewo.laddemo.ui.detail.DetailFragmentDTO
import com.ewo.laddemo.ui.main.MainCounterIncreaseListener

class DetailFragmentKt : BaseFragment() {

    private var viewModel: DetailViewModel? = null
    private var titleTv: TextView? = null
    private var photoIv: CustomIv? = null
    private var ratingTv: TextView? = null
    private var yearTv: TextView? = null
    private var viewedTv: TextView? = null
    private var counterTypeTv: TextView? = null
    private var counterIncreaseV: View? = null
    private var mainCounterIncreaseListener: MainCounterIncreaseListener? = null

    override fun getFragmentConfig(fragmentConfig: BaseFragmentConfig) {
        // set fragment configuration
        fragmentConfig.layoutId = R.layout.fragment_detail_empty
        // generate view model.
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
    }

    override fun onDataReceived(data: BaseDataTransferObject) {
        viewModel?.data = data as DetailFragmentDTO
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Generate ui programmatically via kotlin.
        generateDetailPageUi(view.findViewById(R.id.detail_root))

        // Find views.
        titleTv = view.findViewById(R.id.detail_title_tv)
        photoIv = view.findViewById(R.id.detail_photo_iv)
        ratingTv = view.findViewById(R.id.detail_rating_val)
        yearTv = view.findViewById(R.id.detail_year_val)
        viewedTv = view.findViewById(R.id.detail_viewed_val)
        counterTypeTv = view.findViewById(R.id.detail_counter_type_val)
        counterIncreaseV = view.findViewById(R.id.detail_counter_increase_btn)

        // Find the necessary references.
        mainCounterIncreaseListener = appContext.mainCounterIncreaseListener

        // Generate view model & Set listeners
        viewModel?.getMovieById(viewModel?.data!!.movieId)?.observe(viewLifecycleOwner, Observer { movieModel: MovieModel? ->
            viewModel?.movieModel = movieModel
            if (movieModel == null) {
                return@Observer
            }
            titleTv?.text = movieModel.name
            ratingTv?.text = movieModel.getRating()
            yearTv?.text = movieModel.year.toString()
            photoIv?.loadImageUrl(movieModel.imgUrl)
            viewedTv?.text = movieModel.viewCountValue.toString()
            counterTypeTv?.text = movieModel.getViewCounterType().toString()
            counterIncreaseV?.visibility = if (movieModel.getViewCounterType() == EnumViewCounterType.REMOTE_EVENT_COUNTER) View.VISIBLE else View.GONE
        })
        view.findViewById<View>(R.id.detail_delete_btn).setOnClickListener(onClickListener)
        counterIncreaseV?.setOnClickListener(onClickListener)
    }

    private val onClickListener: View.OnClickListener = object : CustomOnClickListener() {
        override fun onClickView(view: View) {
            when (view.id) {
                R.id.detail_delete_btn ->
                    deleteMovie()
                R.id.detail_counter_increase_btn -> {
                    if (mainCounterIncreaseListener != null && viewModel?.movieModel != null) {
                        mainCounterIncreaseListener?.onIncreaseMovieCounter(viewModel?.movieModel, EnumViewCounterType.REMOTE_EVENT_COUNTER)
                    }

                    // TODO Below line does the same job without the listener reference requirement.
                    // viewModel?.increaseViewCounterWithType(viewModel?.movieModel, EnumViewCounterType.REMOTE_EVENT_COUNTER)
                }
            }
        }
    }

    private fun deleteMovie() {
        viewModel?.removeMovie()
        closePage()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.increaseViewCounterWithType(viewModel?.movieModel, EnumViewCounterType.DESTROY_COUNTER)
    }

    companion object {
        @JvmStatic
        fun instantiate(): Fragment {
            return DetailFragmentKt()
        }
    }
}