package com.techja.lesson28api.view.fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.com.techja.lesson28api.R;
import com.techja.lesson28api.App;
import com.techja.lesson28api.api.model.TokenResModel;
import com.techja.lesson28api.view.viewmodel.BaseViewModel;
import com.techja.lesson28api.view.viewmodel.M001LoginModel;

public class M001LoginFrg extends BaseFragment<M001LoginModel> {
    private static final String TAG = M001LoginFrg.class.getName();
    public static final String KEY_SHOW_LIST_FILM = "KEY_SHOW_LIST_FILM";
    private EditText edtUserName, edtPass;

    @Override
    protected void initViews() {
        edtUserName = findViewById(R.id.edt_user_name);
        edtPass = findViewById(R.id.edt_pass);
        findViewById(R.id.tv_login).setOnClickListener(this);
    }

    @Override
    public void clickView(View v, int id) {
        if (id == R.id.tv_login) {
            doLogin(edtUserName.getText().toString(), edtPass.getText().toString());
        }
    }

    private void doLogin(String userName, String pass) {
        if (userName.isEmpty() || pass.isEmpty()) {
            notify("Please input values!");
            return;
        }

        mModel.login(userName, pass);
    }

    @Override
    protected Class<M001LoginModel> getClassViewModel() {
        return M001LoginModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_m001_login;
    }

    @Override
    public void onCallBack(String key, Object data) {
        if (key.equals(BaseViewModel.KEY_NOTIFY)) {
            notify(data.toString());
        }else if (key.equals(M001LoginModel.API_KEY_LOGIN)) {
            doAfterLogin((TokenResModel)data);
        }
    }

    private void doAfterLogin(TokenResModel data) {
        Toast.makeText(App.getInstance(),"Oki",Toast.LENGTH_SHORT).show();
        notify("Login success!");
        callBack.onCallBack(KEY_SHOW_LIST_FILM,null);
    }
}