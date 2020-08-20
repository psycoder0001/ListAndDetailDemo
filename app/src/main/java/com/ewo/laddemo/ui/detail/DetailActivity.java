package com.ewo.laddemo.ui.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ewo.laddemo.R;
import com.ewo.laddemo.XApp;
import com.ewo.laddemo.helpers.CustomOnClickListener;
import com.ewo.laddemo.localdb.EnumViewCounterType;
import com.ewo.laddemo.ui.main.MainCounterIncreaseListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

@SuppressWarnings("FieldCanBeLocal")
public class DetailActivity extends AppCompatActivity {
    private static final String KEY_POS = "PERSON_INDEX";

    private DetailViewModel viewModel;
    private TextView nameTv;
    private TextView totalTv;
    private TextView paidTv;
    private TextView viewedTv;
    private TextView counterTypeTv;
    private View counterIncreaseV;
    private MainCounterIncreaseListener mainCounterIncreaseListener;

    public static void start(Activity activity, int personPosition) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(KEY_POS, personPosition);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_detail);
        super.onCreate(savedInstanceState);

        // Find views.
        nameTv = findViewById(R.id.detail_name);
        totalTv = findViewById(R.id.detail_total);
        paidTv = findViewById(R.id.detail_paid);
        viewedTv = findViewById(R.id.detail_viewed);
        counterTypeTv = findViewById(R.id.detail_counter_type);
        counterIncreaseV = findViewById(R.id.detail_counter_increase_button);

        // Find the necessary references.
        mainCounterIncreaseListener = ((XApp) getApplication()).mainCounterIncreaseListener;

        // Generate view model & Set listeners
        viewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        viewModel.getPerson(getIntent().getIntExtra(KEY_POS, 0)).observe(this, personModel -> {
            viewModel.personModel = personModel;
            if (personModel == null) {
                return;
            }
            nameTv.setText(personModel.name);
            totalTv.setText(personModel.getTotal());
            paidTv.setText(personModel.getPaid());
            viewedTv.setText(String.valueOf(personModel.viewCountValue));
            counterTypeTv.setText(personModel.getViewCounterType().toString());
            counterIncreaseV.setVisibility(personModel.getViewCounterType() == EnumViewCounterType.REMOTE_EVENT_COUNTER ? View.VISIBLE : View.GONE);
        });
        findViewById(R.id.detail_delete_icon).setOnClickListener(onClickListener);
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
                    if (mainCounterIncreaseListener != null && viewModel.personModel != null) {
                        mainCounterIncreaseListener.onIncreasePersonCounter(viewModel.personModel, EnumViewCounterType.REMOTE_EVENT_COUNTER);
                    }

                    // TODO Below line does the same job without the listener reference requirement.
                    // viewModel.increaseViewCounterWithType(viewModel.personModel, EnumViewCounterType.REMOTE_EVENT_COUNTER);
                    break;
            }
        }
    };

    private void deletePerson() {
        viewModel.removePerson();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.increaseViewCounterWithType(viewModel.personModel, EnumViewCounterType.DESTROY_COUNTER);
    }
}