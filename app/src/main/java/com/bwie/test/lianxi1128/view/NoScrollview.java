package com.bwie.test.lianxi1128.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by johpo on 2016/11/28 0028.
 */
public class NoScrollview extends ViewPager{

    public NoScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollview(Context context) {
        super(context);
    }

    /**
     * 不消费
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    /**
     * 不阻断
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
