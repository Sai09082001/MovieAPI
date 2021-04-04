package com.techja.lesson28api.view.viewmodel;

import android.util.Log;

import androidx.annotation.Nullable;

import com.techja.lesson28api.CommonUtils;
import com.techja.lesson28api.api.API;
import com.techja.lesson28api.api.model.AccountReqModel;
import com.techja.lesson28api.api.model.TokenResModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class M001LoginModel extends BaseViewModel {
    public static final String API_KEY_LOGIN = "API_KEY_LOGIN";
    private static final String TAG = M001LoginModel.class.getName();
    private static final String API_KEY_GET_REQ_TOKEN = "API_KEY_GET_REQ_TOKEN";
    public static final String KEY_TOKEN_USER = "KEY_TOKEN_USER";
    private String userName, pass;

    public void login(String userName, String pass) {
        this.userName = userName;
        this.pass = pass;

        //call API getTokenReq
        API apis = getWS().create(API.class);
        apis.getTokenReq().enqueue(initHandlerRes(API_KEY_GET_REQ_TOKEN));
        // Toast.makeText(App.getInstance(),apis.getTokenReq().toString()+"",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected <T> void handleSuccess(Object key, T body) {
        Log.i(TAG, "handleSuccess: key = " + key);
        Log.i(TAG, "handleSuccess: body = " + body);

        if (key.equals(API_KEY_GET_REQ_TOKEN)) {
            saveTokenReq((TokenResModel) body);
        } else if (key.equals(API_KEY_LOGIN)) {
            doAfterLogin((TokenResModel) body);
        }
    }


    private void doAfterLogin(TokenResModel body) {
        //call to UI
        callBack.onCallBack(API_KEY_LOGIN, body);
    }

    private void saveTokenReq(TokenResModel body) {

        //call API login
        AccountReqModel acc = new AccountReqModel(userName, pass, body.getRequestToken());
        API apis = getWS().create(API.class);
        apis.login(acc).enqueue(initHandlerRes(API_KEY_LOGIN));
        CommonUtils.getInstance().savePref(KEY_TOKEN_USER,body.getRequestToken());
        // Toast.makeText(App.getInstance(),body.getRequestToken(),Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void handleFailed(Object key, Object errorBody) {
        Log.e(TAG, "handleFailed: key = " + key);
        Log.e(TAG, "handleFailed: error = " + errorBody);
        if (key.equals(API_KEY_GET_REQ_TOKEN)) {
            callBack.onCallBack(BaseViewModel.KEY_NOTIFY,
                    "Error: Get request token failed");
        } else if (key.equals(API_KEY_LOGIN)) {
            callBack.onCallBack(BaseViewModel.KEY_NOTIFY,
                    "Error: Login failed");
        }

        //call to UI
    }

}
