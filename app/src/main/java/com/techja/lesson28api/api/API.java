package com.techja.lesson28api.api;

import com.techja.lesson28api.api.model.AccountReqModel;
import com.techja.lesson28api.api.model.MovieModel;
import com.techja.lesson28api.api.model.TokenResModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface API {
    String API_KEY = "8d2cc10d6d30121c3ae4048743b64da3";

    @GET("authentication/token/new?api_key=" + API_KEY)
    @Headers({"ContentType: application/json"})
    Call<TokenResModel> getTokenReq();

    @POST("authentication/token/validate_with_login?api_key=" + API_KEY)
    @Headers({"ContentType: application/json"})
    Call<TokenResModel> login(@Body AccountReqModel acc);

    @GET("discover/movie?api_key=" + API_KEY)
    @Headers({"ContentType: application/json"})
    Call<MovieModel> getListFilm();
}
