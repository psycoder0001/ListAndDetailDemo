package com.ewo.laddemo.ui.detail.kotlin

import android.app.Application
import androidx.lifecycle.LiveData
import com.ewo.laddemo.localdb.MovieModel
import com.ewo.laddemo.ui.base.BaseViewModel
import com.ewo.laddemo.ui.detail.DetailFragmentDTO

/**
 * This is the view model class of the fragment_detail_empty page.
 *
 *
 * View classes such as fragment & activity should not contain any data operation
 * & all the data operations must be written here.
 */
class DetailViewModel(application: Application) : BaseViewModel(application) {
    var movieModel: MovieModel? = null
    var data: DetailFragmentDTO? = null

    fun getMovieById(movieId: Int): LiveData<MovieModel> {
        return repository.getMovieById(movieId)
    }

    fun removeMovie() {
        repository.removeMovie(movieModel)
        movieModel = null
    }
}