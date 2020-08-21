package com.ewo.laddemo.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ewo.laddemo.R;
import com.ewo.laddemo.localdb.MovieModel;
import com.ewo.laddemo.ui.custom.CustomIv;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MainPersonViewHolder> {
    private final ItemClickListener itemClickListener;
    public List<MovieModel> data;

    public MainActivityAdapter(List<MovieModel> personModels, ItemClickListener itemClickListener) {
        this.data = personModels;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MainPersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainPersonViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false));
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
        private TextView nameTv;
        private CustomIv photoIv;
        private TextView totalTv;
        private TextView yearTv;
        private TextView counterTypeTv;
        private TextView viewTv;

        MainPersonViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.item_movie_name);
            photoIv = itemView.findViewById(R.id.item_movie_photo);
            totalTv = itemView.findViewById(R.id.item_movie_total);
            yearTv = itemView.findViewById(R.id.item_movie_paid);
            viewTv = itemView.findViewById(R.id.item_movie_viewed);
            counterTypeTv = itemView.findViewById(R.id.item_movie_type);
        }

        public void setData(MovieModel personModel) {
            itemView.setOnClickListener(view -> itemClickListener.onItemClick(personModel));

            nameTv.setText(personModel.name);
            totalTv.setText(String.valueOf(personModel.rating));
            yearTv.setText(String.valueOf(personModel.year));
            photoIv.loadImageUrl(personModel.imgUrl);
            viewTv.setText(viewTv.getContext().getString(R.string.view, String.valueOf(personModel.viewCountValue)));
            counterTypeTv.setText(personModel.getViewCounterType().getShortName());
        }
    }

    public interface ItemClickListener {
        void onItemClick(MovieModel personModel);
    }
}
