package com.techja.lesson28api.view.fragment;

import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.techja.lesson28api.R;
import com.techja.lesson28api.api.model.MovieModel;
import com.techja.lesson28api.view.viewmodel.M003FilmDetailModel;

import lombok.Setter;

@Setter
public class M003FilmDetailFrg extends BaseFragment<M003FilmDetailModel> {
    private MovieModel.Result movie;
    private ImageView ivTitle;
    private TextView tvTitle,tvOverview;
    @Override
    protected void initViews() {
       ivTitle=findViewById(R.id.iv_title);
        Glide.with(mContext).load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/"+movie.getPosterPath()).into(ivTitle);
        tvTitle=findViewById(R.id.tv_title);
        tvTitle.setText(movie.getTitle());
        tvOverview=findViewById(R.id.tv_overview);
        tvOverview.setText(movie.getOverview());
        findViewById(R.id.tv_play).setOnClickListener(this);
        findViewById(R.id.iv_play).setOnClickListener(this);
    }

    @Override
    protected void clickView(View v, int id) {
        if(v.getId()==R.id.tv_play|| v.getId()==R.id.iv_play){
            showDialog();
        }
    }

    private void showDialog() {
        AlertDialog alertDialog=new AlertDialog.Builder(mContext).create();
        alertDialog.setTitle("Play Trailer");
        alertDialog.setIcon(R.drawable.ic_play_film);
        alertDialog.setMessage(movie.getTitle()+"");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"", (dialog, which) -> {

                });
        alertDialog.show();
    }

    @Override
    protected Class<M003FilmDetailModel> getClassViewModel() {
        return M003FilmDetailModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_m003_film_detail;
    }
}
