package com.bwie.test.lianxi1128.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.test.lianxi1128.view.ShowingPager;
/**
 * autour: 陈杰
 * date: 2016/11/28 0028 20:36
 * update: 2016/11/28 0028
 */
public abstract class BaseFragment extends Fragment{
    private ShowingPager showingPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        showingPager=new ShowingPager(getContext()){

            @Override
            protected void onload() {

            }

            @Override
            public View createSuccessView() {
                return BaseFragment.this.createSuccessView();
            }
        };
        BaseFragment.this.onLoad();
        return showingPager;

    }

    protected abstract void onLoad();


    public abstract View createSuccessView ();
    /**
     * 对外提供的方法，调用展示界面
     * */
    public void  showCurrentPage(ShowingPager.StateType stateType){
        Log.i("sss", "showCurrentPage: ");
            if(showingPager!=null){
                showingPager.showCurrentPage(stateType);
            }
    }

}
