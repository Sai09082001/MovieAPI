package com.techja.lesson28api.view.viewmodel;

import com.techja.lesson28api.api.API;
import com.techja.lesson28api.api.model.MovieModel;

public class M002ListFilmModel extends BaseViewModel {
    public static final String API_KEY_LIST_FILM = "API_KEY_LIST_FILM";
    private MovieModel movieModel;

    public  void getListFilm(){
        API api=getWS().create(API.class);
        api.getListFilm().enqueue(initHandlerRes(API_KEY_LIST_FILM));
    }

    @Override
    protected <T> void handleSuccess(Object key, T body) {
        movieModel=(MovieModel)body;
        callBack.onCallBack(API_KEY_LIST_FILM,movieModel);
    }

    @Override
    protected void handleFailed(Object key, Object errorBody) {
        callBack.onCallBack(KEY_NOTIFY,"Could not get list film");
    }

}