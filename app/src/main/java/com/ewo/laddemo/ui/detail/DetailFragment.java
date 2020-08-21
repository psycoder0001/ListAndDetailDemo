package com.ewo.laddemo.ui.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ewo.laddemo.R;
import com.ewo.laddemo.helpers.CustomOnClickListener;
import com.ewo.laddemo.localdb.EnumViewCounterType;
import com.ewo.laddemo.ui.base.BaseDataTransferObject;
import com.ewo.laddemo.ui.base.BaseFragment;
import com.ewo.laddemo.ui.base.BaseFragmentConfig;
import com.ewo.laddemo.ui.custom.CustomIv;
import com.ewo.laddemo.ui.main.MainCounterIncreaseListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

@SuppressWarnings("FieldCanBeLocal")
public class DetailFragment extends BaseFragment {

    private DetailViewModel viewModel;
    private TextView nameTv;
    private CustomIv photoIv;
    private TextView ratingTv;
    private TextView yearTv;
    private TextView viewedTv;
    private TextView counterTypeTv;
    private View counterIncreaseV;
    private MainCounterIncreaseListener mainCounterIncreaseListener;

    public static Fragment getInstance() {
        return new DetailFragment();
    }

    @Override
    public void getFragmentConfig(BaseFragmentConfig fragmentConfig) {
        // set fragment configuration
        fragmentConfig.layoutId = R.layout.fragment_detail;
        // generate view model.
        viewModel = new ViewModelProvider(this).get(DetailViewModel.class);
    }

    @Override
    public void onDataReceived(BaseDataTransferObject data) {
        viewModel.data = (DetailFragmentDTO) data;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Find views.
        nameTv = view.findViewById(R.id.detail_name);
        photoIv = view.findViewById(R.id.detail_photo);
        ratingTv = view.findViewById(R.id.detail_total);
        yearTv = view.findViewById(R.id.detail_paid);
        viewedTv = view.findViewById(R.id.detail_viewed);
        counterTypeTv = view.findViewById(R.id.detail_counter_type);
        counterIncreaseV = view.findViewById(R.id.detail_counter_increase_button);

        // Find the necessary references.
        mainCounterIncreaseListener = getAppContext().mainCounterIncreaseListener;

        // Generate view model & Set listeners
        viewModel.getMovieById(viewModel.data.movieId).observe(getViewLifecycleOwner(), personModel -> {
            viewModel.movieModel = personModel;
            if (personModel == null) {
                return;
            }
            nameTv.setText(personModel.name);
            ratingTv.setText(personModel.getRating());
            yearTv.setText(String.valueOf(personModel.year));
            photoIv.loadImageUrl(personModel.imgUrl);
            viewedTv.setText(String.valueOf(personModel.viewCountValue));
            counterTypeTv.setText(personModel.getViewCounterType().toString());
            counterIncreaseV.setVisibility(personModel.getViewCounterType() == EnumViewCounterType.REMOTE_EVENT_COUNTER ? View.VISIBLE : View.GONE);
        });
        view.findViewById(R.id.detail_delete_icon).setOnClickListener(onClickListener);
        counterIncreaseV.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new CustomOnClickListener() {
        @Override
        public void onClickView(View view) {
            switch (view.getId()) {
                case R.id.detail_delete_icon:
                    deletePerson();
                    break;
                case R.id.detail_counter_increase_button:
                    if (mainCounterIncreaseListener != null && viewModel.movieModel != null) {
                        mainCounterIncreaseListener.onIncreasePersonCounter(viewModel.movieModel, EnumViewCounterType.REMOTE_EVENT_COUNTER);
                    }

                    // TODO Below line does the same job without the listener reference requirement.
                    // viewModel.increaseViewCounterWithType(viewModel.personModel, EnumViewCounterType.REMOTE_EVENT_COUNTER);
                    break;
            }
        }
    };

    private void deletePerson() {
        viewModel.removeMovie();
        closePage();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.increaseViewCounterWithType(viewModel.movieModel, EnumViewCounterType.DESTROY_COUNTER);
    }
}