package com.example.james.tft_android.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.tft_android.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment{

    protected View rootView;
    Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayout(),container,false);
        unbinder = ButterKnife.bind(this, rootView);
        onCreateView();
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (unbinder != null)
        unbinder.unbind();
    }

    public abstract int getLayout();

    public abstract void onCreateView();

}
