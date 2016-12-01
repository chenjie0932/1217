package com.bwie.test.lianxi1128.fragment;

import android.view.View;
import android.widget.TextView;

import com.bwie.test.lianxi1128.base.BaseFragment;
import com.bwie.test.lianxi1128.utils.LogUtils;
import com.bwie.test.lianxi1128.view.ShowingPager;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * autour: 陈杰
 * date: 2016/11/28 0028 18:05 
 * update: 2016/11/28 0028
 */
public class CartFragment extends BaseFragment {
    private String s;

    @Override
    protected void onLoad() {
        RequestParams re=new RequestParams("http://www.baidu.com");
        //去加载
        x.http().get(re, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                //先赋值---
                CartFragment.this.s=s;
                CartFragment.this.showCurrentPage(ShowingPager.StateType.STATE_LOAD_SUCCESS);
                LogUtils.e("AAAAA","success-------cart-"+s);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }
            @Override
            public void onCancelled(CancelledException e) {
            }
            @Override
            public void onFinished() {
            }
        });
    }
    @Override
    public View createSuccessView() {

        LogUtils.e("AAAAA","create-------cart-"+s);
        TextView textView =new TextView(getContext());
        textView.setText(s);
        return textView;
    }
}
