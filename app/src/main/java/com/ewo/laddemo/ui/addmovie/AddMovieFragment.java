package com.ewo.laddemo.ui.addmovie;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ewo.laddemo.R;
import com.ewo.laddemo.localdb.DummyItemGenerator;
import com.ewo.laddemo.localdb.EnumViewCounterType;
import com.ewo.laddemo.localdb.MovieModel;
import com.ewo.laddemo.ui.base.BaseFragment;
import com.ewo.laddemo.ui.base.BaseFragmentConfig;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

public class AddMovieFragment extends BaseFragment {

    private EditText nameEt;
    private EditText ratingEt;
    private EditText yearEt;
    private EditText imgUrlEt;
    private TextView counterTypeValTv;
    private Spinner counterTypeSpn;
    private AddMovieViewModel viewModel;

    public static AddMovieFragment instantiate() {
        return new AddMovieFragment();
    }

    @Override
    public void getFragmentConfig(BaseFragmentConfig fragmentConfig) {
        fragmentConfig.layoutId = R.layout.fragment_add_movie;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameEt = view.findViewById(R.id.add_movie_name_et);
        ratingEt = view.findViewById(R.id.add_movie_rating_et);
        yearEt = view.findViewById(R.id.add_movie_year_et);
        imgUrlEt = view.findViewById(R.id.add_movie_url_et);
        counterTypeValTv = view.findViewById(R.id.add_movie_counter_type_val);
        counterTypeSpn = view.findViewById(R.id.add_movie_counter_type_spn);

        viewModel = new ViewModelProvider(this).get(AddMovieViewModel.class);
        counterTypeSpn.setOnItemSelectedListener(onSpinnerItemSelected);
        counterTypeSpn.setAdapter(new ArrayAdapter<>(getAppContext(), R.layout.item_spinner, EnumViewCounterType.getListOfItem()));
        view.findViewById(R.id.add_movie_save).setOnClickListener((clickedView) -> onSave());
        view.findViewById(R.id.add_movie_counter_type_val).setOnClickListener((clickedView) -> counterTypeSpn.performClick());

        initializeWithRandomValues();
    }

    private void initializeWithRandomValues() {
        MovieModel movieModel = DummyItemGenerator.GenerateNewItem(EnumViewCounterType.INSTANT_COUNTER);
        nameEt.setText(movieModel.name);
        ratingEt.setText(String.valueOf(movieModel.rating));
        yearEt.setText(String.valueOf(movieModel.year));
        imgUrlEt.setText(movieModel.imgUrl);
        counterTypeSpn.setSelection(viewModel.selectedCounterType.ordinal());
    }

    private void onSave() {
        viewModel.save(nameEt.getText().toString(), ratingEt.getText().toString(), yearEt.getText().toString(), imgUrlEt.getText().toString());
        closePage();
    }

    private AdapterView.OnItemSelectedListener onSpinnerItemSelected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            viewModel.selectedCounterType = EnumViewCounterType.parse(i);
            counterTypeValTv.setText(viewModel.selectedCounterType.toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
}
