package com.bwie.test.lianxi1128.factory;

import android.support.v4.app.Fragment;

import com.bwie.test.lianxi1128.fragment.CartFragment;
import com.bwie.test.lianxi1128.fragment.CategoryFragment;
import com.bwie.test.lianxi1128.fragment.HomeFragment;
import com.bwie.test.lianxi1128.fragment.MineFragment;

import java.util.HashMap;

/**
 * autour: 陈杰
 * date: 2016/11/28 0028 17:52 
 * update: 2016/11/28 0028
 */
public class FragmentFactory {
    //集合
    public static HashMap<Integer, Fragment> fragmentMap = new HashMap<>();

    public static Fragment getFragment(int position) {
        Fragment fragment = fragmentMap.get(position);
        if (fragment != null) {
            return fragment;
        }
        switch (position) {
            case 0:
                fragment=new HomeFragment();
                break;
            case 1:
                fragment=new CategoryFragment();
                break;
            case 2:
                fragment=new CartFragment();
                break;
            case 3:
                fragment=new MineFragment();
                break;
        }
        //添加到集合中
        fragmentMap.put(position,fragment);
        return  fragment;
    }
}
