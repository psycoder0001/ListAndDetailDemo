package com.ewo.laddemo.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ewo.laddemo.EnumFragmentPage;
import com.ewo.laddemo.R;
import com.ewo.laddemo.helpers.CustomOnClickListener;
import com.ewo.laddemo.localdb.EnumViewCounterType;
import com.ewo.laddemo.localdb.MovieModel;
import com.ewo.laddemo.ui.base.BaseFragment;
import com.ewo.laddemo.ui.base.BaseFragmentConfig;
import com.ewo.laddemo.ui.detail.DetailFragmentDTO;
import com.ewo.laddemo.ui.newmovie.NewMovieActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

public class MainFragment extends BaseFragment {

    private MainViewModel viewModel;
    private RecyclerView recyclerView;
    private MainActivityAdapter listAdapter;

    public static Fragment getInstance() {
        return new MainFragment();
    }

    @Override
    public void getFragmentConfig(BaseFragmentConfig fragmentConfig) {
        // set fragment configuration
        fragmentConfig.layoutId = R.layout.fragment_main;
        // generate view model.
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.main_recycler);
        view.findViewById(R.id.main_add).setOnClickListener(OnClickListener);
        view.findViewById(R.id.main_add_icon).setOnClickListener(OnClickListener);
        view.findViewById(R.id.main_wipe_data_icon).setOnClickListener(OnClickListener);

        viewModel.getPersonList().observe(getViewLifecycleOwner(), personModels -> {
            if (personModels == null || personModels.isEmpty()) {
                viewModel.generateInitialData();
                return;
            }
            if (listAdapter == null) {
                listAdapter = new MainActivityAdapter(personModels, this::onItemClick);
                recyclerView.setAdapter(listAdapter);
            } else {
                if (recyclerView.getAdapter() == null) {
                    recyclerView.setAdapter(listAdapter);
                }
                listAdapter.data = personModels;
                listAdapter.notifyDataSetChanged();
            }
        });
    }

    private View.OnClickListener OnClickListener = new CustomOnClickListener() {
        @Override
        public void onClickView(View view) {
            switch (view.getId()) {
                case R.id.main_add:
                case R.id.main_add_icon:
                    startActivity(new Intent(getActivity(), NewMovieActivity.class));
                    break;
                case R.id.main_wipe_data_icon:
                    viewModel.wipeData();
                    break;
            }
        }
    };

    private final MainCounterIncreaseListener mainCounterIncreaseListener = new MainCounterIncreaseListener() {
        @Override
        public void onIncreasePersonCounter(MovieModel personModel, EnumViewCounterType acceptableCounterType) {
            viewModel.increaseViewCounterWithType(personModel, acceptableCounterType);
        }
    };

    public void onItemClick(MovieModel personModel) {
        mainCounterIncreaseListener.onIncreasePersonCounter(personModel, EnumViewCounterType.INSTANT_COUNTER);
        getAppContext().mainCounterIncreaseListener = mainCounterIncreaseListener;
        openPage(EnumFragmentPage.DETAIL, new DetailFragmentDTO(personModel.id));
    }
}
