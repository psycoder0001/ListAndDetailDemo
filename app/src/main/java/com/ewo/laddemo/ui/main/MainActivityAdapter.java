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

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MainMovieViewHolder> {
    private final ItemClickListener itemClickListener;
    public List<MovieModel> data;

    public MainActivityAdapter(List<MovieModel> movieModels, ItemClickListener itemClickListener) {
        this.data = movieModels;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MainMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainMovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainMovieViewHolder holder, int position) {
        holder.setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class MainMovieViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTv;
        private CustomIv photoIv;
        private TextView ratingTv;
        private TextView yearTv;
        private TextView counterTypeTv;
        private TextView viewTv;

        MainMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.item_movie_name);
            photoIv = itemView.findViewById(R.id.item_movie_photo);
            ratingTv = itemView.findViewById(R.id.item_movie_rating);
            yearTv = itemView.findViewById(R.id.item_movie_year);
            viewTv = itemView.findViewById(R.id.item_movie_viewed);
            counterTypeTv = itemView.findViewById(R.id.item_movie_type);
        }

        public void setData(MovieModel movieModel) {
            itemView.setOnClickListener(view -> itemClickListener.onItemClick(movieModel));

            nameTv.setText(movieModel.name);
            ratingTv.setText(String.valueOf(movieModel.rating));
            yearTv.setText(String.valueOf(movieModel.year));
            photoIv.loadImageUrl(movieModel.imgUrl);
            viewTv.setText(viewTv.getContext().getString(R.string.view, String.valueOf(movieModel.viewCountValue)));
            counterTypeTv.setText(movieModel.getViewCounterType().getShortName());
        }
    }

    public interface ItemClickListener {
        void onItemClick(MovieModel movieModel);
    }
}
