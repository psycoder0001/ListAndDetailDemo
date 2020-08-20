package com.ewo.laddemo.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ewo.laddemo.R;
import com.ewo.laddemo.localdb.PersonModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MainPersonViewHolder> {
    private final ItemClickListener itemClickListener;
    private List<PersonModel> data;

    public MainActivityAdapter(List<PersonModel> personModels, ItemClickListener itemClickListener) {
        this.data = personModels;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MainPersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainPersonViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loan, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainPersonViewHolder holder, int position) {
        holder.setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class MainPersonViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView total;
        private TextView paid;
        private TextView counterType;
        private TextView viewed;

        MainPersonViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_loan_name);
            total = itemView.findViewById(R.id.item_loan_total);
            paid = itemView.findViewById(R.id.item_loan_paid);
            viewed = itemView.findViewById(R.id.item_loan_viewed);
            counterType = itemView.findViewById(R.id.item_loan_type);
        }

        public void setData(PersonModel personModel) {
            itemView.setOnClickListener(view -> itemClickListener.onItemClick(personModel));

            name.setText(personModel.name);
            total.setText(personModel.getTotal());
            paid.setText(personModel.getPaid());
            viewed.setText(String.valueOf(personModel.viewCountValue));
            counterType.setText(personModel.getViewCounterType().toString());
        }
    }

    public interface ItemClickListener {
        void onItemClick(PersonModel personModel);
    }
}
