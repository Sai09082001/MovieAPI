package com.techja.lesson28api.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.com.techja.lesson28api.R;
import com.techja.lesson28api.api.model.MovieModel;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmHolder> {
    private static String LINK_PATH="https://www.themoviedb.org/t/p/w600_and_h900_bestv2/";
    private List<MovieModel.Result> listData;
    private Context context;

    private MovieModel.Result selectedMovie;
    private OnItemClick callBack;

    public interface OnItemClick {
        void onItemClick(MovieModel.Result movie);
    }
    public FilmAdapter(List<MovieModel.Result> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public FilmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        return new FilmHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmHolder holder, int position) {
        MovieModel.Result data=listData.get(position);
        RequestOptions options = new RequestOptions()
                .centerCrop().placeholder(R.drawable.bg_demo)
                .error(R.drawable.bg_demo);

        Glide.with(context).load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/"+data.getPosterPath()).apply(options).into(holder.ivPhoto);
       // Glide.with(context).load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/"+data.getPosterPath()).centerCrop().into(holder.ivPhoto);
        holder.tvOverview.setText(data.getOverview());
        holder.tvDate.setText(data.getReleaseDate());
        holder.tvTitle.setText(data.getTitle());
        holder.lnMovie.setBackgroundResource(
                data==selectedMovie &&
                        data.isSelectedMovie() ? R.color.thin : R.color.white);
        holder.movie = data; // cập nhật dữ liệu cho từng holder chứa từng view của story

    }


    public void setOnItemClick(OnItemClick event) {
        callBack = event;
    }

    public void setSelectedMovie(MovieModel.Result movie) {
        if(movie!=null){
            selectedMovie.setSelectedMovie(false);
        }
        movie.setSelectedMovie(true);
        selectedMovie=movie;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class FilmHolder extends RecyclerView.ViewHolder {
        public LinearLayout lnMovie;
        TextView tvDate,tvTitle,tvOverview;
        ImageView ivPhoto;
        MovieModel.Result movie;
        public FilmHolder(@NonNull View itemView) {
            super(itemView);
            ivPhoto=itemView.findViewById(R.id.iv_photo);
            tvTitle=itemView.findViewById(R.id.tv_title);
            tvDate=itemView.findViewById(R.id.tv_date);
            tvOverview=itemView.findViewById(R.id.tv_overview);
            lnMovie=itemView.findViewById(R.id.ln_item_movie);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // v.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.anim_click));
                    if (selectedMovie != null) {
                        selectedMovie.setSelectedMovie(false);
                    }
                    movie.setSelectedMovie(true);

                    notifyDataSetChanged();
                    selectedMovie = movie;
                    if (callBack != null) {
                        callBack.onItemClick(movie);
                    }

                }
            });

        }

    }
}