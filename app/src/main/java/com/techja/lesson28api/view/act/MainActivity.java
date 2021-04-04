package com.techja.lesson28api.view.act;

import android.widget.Toast;

import com.techja.lesson28api.CommonUtils;
import com.techja.lesson28api.OnActionCallBack;
import com.techja.lesson28api.R;
import com.techja.lesson28api.api.model.MovieModel;
import com.techja.lesson28api.view.fragment.M001LoginFrg;
import com.techja.lesson28api.view.fragment.M002ListFilmFrg;
import com.techja.lesson28api.view.fragment.M003FilmDetailFrg;
import com.techja.lesson28api.view.viewmodel.M001LoginModel;
import com.techja.lesson28api.view.viewmodel.MainViewModel;

public class MainActivity extends BaseAct<MainViewModel> implements OnActionCallBack {

    @Override
    protected Class<MainViewModel> getClassViewModel() {
        return MainViewModel.class;
    }

    @Override
    protected void initViews() {
        if(CommonUtils.getInstance().isExistPref(M001LoginModel.KEY_TOKEN_USER)){
            Toast.makeText(this,CommonUtils.getInstance().getPref(M001LoginModel.KEY_TOKEN_USER),Toast.LENGTH_LONG).show();
            showListFilmFrg();
        }else {
            M001LoginFrg mainFrg = new M001LoginFrg();
            mainFrg.setCallBack(this);
            showFragment(R.id.ln_main, mainFrg, false);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onCallBack(String key, Object data) {
        if(key.equals(M001LoginFrg.KEY_SHOW_LIST_FILM)){
            showListFilmFrg();
        }else if(key.equals(M002ListFilmFrg.KEY_SHOW_FILM_DETAIL)){
            M003FilmDetailFrg filmDetailFrg=new M003FilmDetailFrg();
            filmDetailFrg.setCallBack(this);
            filmDetailFrg.setMovie((MovieModel.Result) data);
            showFragment(R.id.ln_main,filmDetailFrg,true);
        }
    }

    private void showFilmDetail() {
        M003FilmDetailFrg filmDetailFrg=new M003FilmDetailFrg();
        filmDetailFrg.setCallBack(this);
        showFragment(R.id.ln_main,filmDetailFrg,true);
    }

    private void showListFilmFrg() {
        M002ListFilmFrg listFilmFrg=new M002ListFilmFrg();
        listFilmFrg.setCallBack(this);
        showFragment(R.id.ln_main,listFilmFrg,false);
    }
}