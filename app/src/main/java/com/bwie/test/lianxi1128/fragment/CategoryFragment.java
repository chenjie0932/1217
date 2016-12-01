package com.bwie.test.lianxi1128.fragment;

import android.view.View;

import com.bwie.test.lianxi1128.base.BaseFragment;
import com.bwie.test.lianxi1128.view.ShowingPager;


/**
 * Created by zhiyuan on 16/9/28.
 */
public class CategoryFragment extends BaseFragment {
    @Override
    protected void onLoad() {
        CategoryFragment.this.showCurrentPage(ShowingPager.StateType.STATE_LOAD_ERROR);
    }
    @Override
    public View createSuccessView() {
      return null;
    }

}
