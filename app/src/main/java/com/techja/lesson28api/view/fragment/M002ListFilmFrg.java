package com.techja.lesson28api.view.fragment;

import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.com.techja.lesson28api.R;
import com.techja.lesson28api.App;
import com.techja.lesson28api.api.model.MovieModel;
import com.techja.lesson28api.view.adapter.FilmAdapter;
import com.techja.lesson28api.view.viewmodel.M002ListFilmModel;

public class M002ListFilmFrg extends BaseFragment<M002ListFilmModel> implements FilmAdapter.OnItemClick{
    private static final String TAG = M002ListFilmModel.class.getName();
    public static final String KEY_SHOW_FILM_DETAIL = "KEY_SHOW_FILM_DETAIL";
    private RecyclerView rvFilm;

    @Override
    protected void initViews() {
        rvFilm=findViewById(R.id.rv_list_film);
        rvFilm.setLayoutManager(new LinearLayoutManager(mContext));
        mModel.getListFilm();
    }

    @Override
    public void onCallBack(String key, Object data) {
        if(key.equals(M002ListFilmModel.KEY_NOTIFY)){
            notify((String)data);
        }else if(key.equals(M002ListFilmModel.API_KEY_LIST_FILM)){
            MovieModel movieModel=(MovieModel) data;
            Log.i(TAG,"onCallBack -"+key+":"+movieModel.getListResult().size());
            FilmAdapter filmAdapter=new FilmAdapter(movieModel.getListResult(),mContext);
            filmAdapter.setOnItemClick(this);
            rvFilm.setAdapter(filmAdapter);
            getStorage().getM002Movie().observe(this, movie -> {
                ((FilmAdapter)rvFilm.getAdapter()).setSelectedMovie(movie);
            });

        }
    }

    @Override
    protected Class<M002ListFilmModel> getClassViewModel() {
        return M002ListFilmModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_m002_list_film;
    }

    @Override
    public void onItemClick(MovieModel.Result movie) {
        Toast.makeText(App.getInstance(),"OKI+",Toast.LENGTH_SHORT).show();
        callBack.onCallBack(KEY_SHOW_FILM_DETAIL, movie);
    }
}

