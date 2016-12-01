package com.bwie.test.lianxi1128.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bwie.test.lianxi1128.bean.ZhuYeBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by johpo on 2016/11/30 0030.
 */
public class MyViewPagerAdapter  extends PagerAdapter{
    private ZhuYeBean zhuYe;

    private Context context;
    private DisplayImageOptions options;
    public MyViewPagerAdapter(ZhuYeBean zhuYe, Context context, DisplayImageOptions options) {
        this.zhuYe=zhuYe;
        this.context = context;
        this.options = options;
    }

    @Override
    public int getCount() {

        return Integer.MAX_VALUE;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(context);
        //iv.setImageResource(R.mipmap.about_page_mor);
        ImageLoader.getInstance().displayImage("http://image.hmeili.com/yunifang/images/goods/ad0/16111321547686778106559064.jpg",iv,options);
        container.addView(iv);
        return iv;
    }

}
