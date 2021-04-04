package com.techja.lesson28api.view.act;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.techja.lesson28api.view.viewmodel.BaseViewModel;

public abstract class BaseAct<T extends BaseViewModel>
        extends AppCompatActivity implements View.OnClickListener {

    protected T mModel;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mModel = new ViewModelProvider(this).get(getClassViewModel());

        initViews();
    }

    protected abstract Class<T> getClassViewModel();

    protected abstract void initViews();

    protected abstract int getLayoutId();

    public final <G extends View> G findViewById(int id, View.OnClickListener event) {
        G v = findViewById(id);
        if (v != null && event != null) {
            v.setOnClickListener(this);
        }
        return v;
    }

    @Override
    public void onClick(View v) {
        // do nothing
    }

    protected void showFragment(int layoutId, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(layoutId, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
