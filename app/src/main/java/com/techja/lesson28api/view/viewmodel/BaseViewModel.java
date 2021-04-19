package com.techja.lesson28api.view.viewmodel;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

import com.techja.lesson28api.OnActionCallBack;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseViewModel extends ViewModel {
    protected static final String TOKEN_REQ = "TOKEN_REQ";
    public static final String KEY_NOTIFY = "KEY_NOTIFY";


    private static final String TAG = BaseViewModel.class.getName();
    public static  String BASE_URL = "https://api.themoviedb.org/3/";
    protected OnActionCallBack callBack;

    public void setCallBack(OnActionCallBack callBack) {
        this.callBack = callBack;
    }
    public Retrofit getWS(){
//        OkHttpClient client=new OkHttpClient.Builder()
//                .connectTimeout(30, TimeUnit.SECONDS)
//                .build();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .build())
                //add converter json->Object
                .addConverterFactory(GsonConverterFactory.create())
                .build();
      //  return ws;
    }

    protected  <T> Callback<T> initHandlerRes(Object key) {
        return new Callback<T>() {
            @Override
            public void onResponse(@Nullable Call<T> call, @Nullable Response<T> response) {
                if(response.code()==200 || response.code()==201){
                    handleSuccess(key,response.body());
                }else{
                    handleFailed(key,response.errorBody());
                }
            }

            @Override
            public void onFailure(@Nullable Call<T> call,@Nullable Throwable t) {
                handleFailed(key,t);
            }
        };
    }

    protected void handleFailed(Object key, Object errorBody) {
          //do nothing
    }

    protected  <T> void handleSuccess(Object key, T body) {
       // do nothing
    }
}
