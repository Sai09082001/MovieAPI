package com.techja.lesson28api;

import androidx.lifecycle.MutableLiveData;

import com.techja.lesson28api.api.model.MovieModel;

public class Storage {
    private MutableLiveData<MovieModel.Result> m002Movie=new MutableLiveData<>();

    public MutableLiveData<MovieModel.Result> getM002Movie() {
        return m002Movie;
    }

    public void setM002Movie(MovieModel.Result movie) {

        m002Movie.setValue(movie);
    }
}
