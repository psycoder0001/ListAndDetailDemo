package com.ewo.laddemo.ui.newmovie;

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
import com.ewo.laddemo.ui.base.BaseActivity;
import com.ewo.laddemo.ui.base.BaseActivityConfig;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

public class NewMovieActivity extends BaseActivity {

    private EditText nameEt;
    private EditText ratingEt;
    private EditText yearEt;
    private EditText imgUrlEt;
    private TextView counterTypeValTv;
    private Spinner counterTypeSpn;
    private NewMovieViewModel viewModel;

    @Override
    public void getActivityConfig(BaseActivityConfig activityConfig) {
        activityConfig.layoutId = R.layout.activity_new_movie;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nameEt = findViewById(R.id.new_movie_name_et);
        ratingEt = findViewById(R.id.new_movie_rating_et);
        yearEt = findViewById(R.id.new_movie_year_et);
        imgUrlEt = findViewById(R.id.new_movie_url_et);
        counterTypeValTv = findViewById(R.id.new_movie_counter_type_val);
        counterTypeSpn = findViewById(R.id.new_movie_counter_type_spn);

        viewModel = new ViewModelProvider(this).get(NewMovieViewModel.class);
        counterTypeSpn.setOnItemSelectedListener(onSpinnerItemSelected);
        counterTypeSpn.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.item_spinner, EnumViewCounterType.getListOfItem()));
        findViewById(R.id.new_movie_save).setOnClickListener((view) -> onSave());
        findViewById(R.id.new_movie_counter_type_val).setOnClickListener((view) -> counterTypeSpn.performClick());

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
        finish();
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
