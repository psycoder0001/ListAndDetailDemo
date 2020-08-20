package com.ewo.laddemo.ui.main;

import android.os.Bundle;
import android.view.View;

import com.ewo.laddemo.R;
import com.ewo.laddemo.XApp;
import com.ewo.laddemo.helpers.CustomOnClickListener;
import com.ewo.laddemo.localdb.EnumViewCounterType;
import com.ewo.laddemo.localdb.PersonModel;
import com.ewo.laddemo.ui.detail.DetailActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        recyclerView = findViewById(R.id.main_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        findViewById(R.id.main_add).setOnClickListener(OnClickListener);
        findViewById(R.id.main_add_icon).setOnClickListener(OnClickListener);
        findViewById(R.id.main_wipe_data_icon).setOnClickListener(OnClickListener);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getPersonList().observe(this, personModels -> recyclerView.setAdapter(new MainActivityAdapter(personModels, this::onItemClick)));
    }

    private View.OnClickListener OnClickListener = new CustomOnClickListener() {
        @Override
        public void onClickView(View view) {
            switch (view.getId()) {
                case R.id.main_add:
                case R.id.main_add_icon:
                    // Create a new page.
                    viewModel.generateRandomPerson();
                    break;
                case R.id.main_wipe_data_icon:
                    viewModel.wipeData();
                    break;
            }
        }
    };

    private final MainCounterIncreaseListener mainCounterIncreaseListener = new MainCounterIncreaseListener() {
        @Override
        public void onIncreasePersonCounter(PersonModel personModel, EnumViewCounterType counterType) {
            viewModel.increaseViewCounterWithType(personModel, counterType);
        }
    };

    public void onItemClick(PersonModel personModel) {
        mainCounterIncreaseListener.onIncreasePersonCounter(personModel, EnumViewCounterType.INSTANT_COUNTER);
        ((XApp) getApplication()).mainCounterIncreaseListener = mainCounterIncreaseListener;
        DetailActivity.start(MainActivity.this, personModel.id);
    }
}