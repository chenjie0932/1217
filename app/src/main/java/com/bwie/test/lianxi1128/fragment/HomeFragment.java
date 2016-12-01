package com.bwie.test.lianxi1128.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bwie.test.lianxi1128.R;
import com.bwie.test.lianxi1128.adapter.MyViewPagerAdapter;
import com.bwie.test.lianxi1128.base.BaseData;
import com.bwie.test.lianxi1128.base.BaseFragment;
import com.bwie.test.lianxi1128.bean.ZhuYeBean;
import com.bwie.test.lianxi1128.okhttp.OkHttp;
import com.bwie.test.lianxi1128.okhttp.Tools;
import com.bwie.test.lianxi1128.utils.ImageLoaderUtils;
import com.bwie.test.lianxi1128.utils.URLutils;
import com.bwie.test.lianxi1128.view.ShowingPager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Request;

/**
 * Created by johpo on 2016/11/28 0028.
 */
public class HomeFragment extends BaseFragment {
    private DisplayImageOptions options = ImageLoaderUtils.initOptions();
   private  ViewPager vp;
    private LinearLayout fragment1_yuandian;
    private ZhuYeBean zhuYe;

    Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1){
                zhuYe = (ZhuYeBean) msg.obj;
                /**
                 * 主页最上面的viewapaget的操作
                 */
                vp.setAdapter(new MyViewPagerAdapter(zhuYe, getActivity(), options));

            }else if(msg.what==2){
                int it=(int) msg.obj;
                vp.setCurrentItem(++it);
            }
            super.handleMessage(msg);
        }
    };

  public String data;
    @Override
    protected void onLoad() {
        //加载数据
        //获取数据
        new BaseData(){

            @Override
            public void setResultData(String data) {
                   HomeFragment.this.data=data;
//                Log.i("aaaaaaaaa","data"+data);
                HomeFragment.this.showCurrentPage(ShowingPager.StateType.STATE_LOAD_SUCCESS);
            }

            @Override
            public void setResultError(ShowingPager.StateType stateLoadError) {

            }
        }.getData(URLutils.homeUrl,URLutils.homeArgs,0,BaseData.NORMALTIME);
    }

    @Override
    public View createSuccessView() {
       /*         Log.i("aaaaaaaaa","data"+data);

        TextView textView=new TextView(getActivity());
        textView.setText(data);*/
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.homefragment,container,false);
        vp=(ViewPager)view.findViewById(R.id.vp);
        fragment1_yuandian=(LinearLayout)view.findViewById(R.id.fragment1_yuandian);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //请求数据
        jiexi();
        //自动轮播
        lunbo();
        super.onActivityCreated(savedInstanceState);
    }

    private void jiexi() {
        OkHttp.getAsync("http://m.yunifang.com/yunifang/mobile/home?random=59676&encode=62d458fefce9c740359873cc19b05188", new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Log.i("12345","result"+result);

                ZhuYeBean zhuYeBean=Tools.parseJsonWithGson(result,ZhuYeBean.class);
                Message msg = new Message();
                msg.obj = zhuYeBean;
                msg.what = 1;
               handler.sendMessage(msg);


            }
        });
    }

    //轮播
    private void lunbo() {
        Timer t = new Timer();
        TimerTask tsk = new TimerTask() {
            @Override
            public void run() {
                int item = vp.getCurrentItem();
                Message ms = new Message();
                ms.obj = item;
                ms.what = 2;
                handler.sendMessage(ms);

            }
        };
        t.schedule(tsk, 4000, 4000);
        /**
         * 加轮播的小圆点
         */
        for (int i = 0; i < 4; i++) {
            ImageView yuan = new ImageView(getActivity());
            if (i == 0) {
                yuan.setImageResource(R.drawable.dot_normal);
            } else {
                yuan.setImageResource(R.drawable.dot_focuse);
            }
            LinearLayoutCompat.LayoutParams l = new LinearLayoutCompat.LayoutParams(20, 20);
            l.setMargins(5, 2, 5, 5);
            fragment1_yuandian.addView(yuan, l);
        }

    }
}
