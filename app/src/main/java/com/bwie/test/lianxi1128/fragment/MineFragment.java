package com.bwie.test.lianxi1128.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.test.lianxi1128.R;
import com.bwie.test.lianxi1128.base.BaseFragment;


/**
 * Created by zhiyuan on 16/9/28.
 */
public class MineFragment extends BaseFragment {
    @Override
    protected void onLoad() {

    }

    @Override
    public View createSuccessView() {

        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.myfragment,container,false);
        return view;
    }
}
