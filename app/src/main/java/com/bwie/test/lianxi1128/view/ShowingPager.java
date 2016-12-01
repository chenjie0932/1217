package com.bwie.test.lianxi1128.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.bwie.test.lianxi1128.R;
import com.bwie.test.lianxi1128.utils.CommonUtils;

/**
 * Created by johpo on 2016/11/28 0028.
 */
public abstract class  ShowingPager extends FrameLayout implements View.OnClickListener {
    public static final int STATE_UNLOAD = 1;
    public static final int STATE_LOAD = 2;
    public static final int STATE_LOAD_ERROR = 3;
    public static final int STATE_LOAD_EMPTY = 4;
    public static final int STATE_LOAD_SUCCESS = 5;
    private View load_empty;
    private View load_err;
    private  View loading;
    private  View unload;
    private View showingPage_success;
    private LayoutParams layoutParams;
    private Button rebu;

    //定义一个初始状态--当前是未加载状态
    public int currentState = STATE_UNLOAD;

    public ShowingPager(Context context) {
        super(context);
        //初始化
        layoutParams=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        if(load_empty==null){
            load_empty=CommonUtils.inflate(R.layout.load_empty);
            this.addView(load_empty,layoutParams);
        }
        if(load_err==null){
            load_err=CommonUtils.inflate(R.layout.load_err);
            this.addView(load_err,layoutParams);
            //重新加载按钮
            rebu=(Button)load_err.findViewById(R.id.rebu);
            rebu.setOnClickListener(this);
        }
        if(loading==null){
            loading=CommonUtils.inflate(R.layout.loading);
            this.addView(loading,layoutParams);
        }
       /* if(unload==null){
            unload=CommonUtils.inflate(R.layout.unload);
            this.addView(unload,layoutParams);
        }
*/
     //添加展示
        showPage();
        //数据的加载
//        onload();
    }
    //提供一个请求结束之后，设置当前状态，展示界面的方法  6
    public void showCurrentPage(StateType stateType) {
        //获取枚举中的数字,并赋值给当前类型
        this.currentState = stateType.getCurrentState();
        //展示一下
        showPage();
    }


    protected abstract void onload();

    public void showPage() {
        CommonUtils.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                showUIpage();
            }
        });
    }

    /**
     * 在主线程中刷新UI*/
    private void showUIpage() {
        //根据当前状态进行展示
       /* //未加载
        if (unload != null) {
            unload.setVisibility(currentState == STATE_UNLOAD ? View.VISIBLE : View.GONE);
        }*/
        if (loading != null) {
            loading.setVisibility(currentState == STATE_LOAD ? View.VISIBLE : View.GONE);
        }
        if (load_empty!= null) {
            load_empty.setVisibility(currentState == STATE_LOAD_EMPTY ? View.VISIBLE : View.GONE);
        }
        if (load_err!= null) {
            load_err.setVisibility(currentState == STATE_LOAD_ERROR ? View.VISIBLE : View.GONE);
        }
        //成功的状态--没有成功界面
        if (showingPage_success == null && currentState == STATE_LOAD_SUCCESS) {
            //加载成功的界面--添加到当前的showingPage
            showingPage_success = createSuccessView();
            //添加到showingPage
            this.addView(showingPage_success, layoutParams);
        }
        //判断是否是成功状态
        if (showingPage_success != null) {
            showingPage_success.setVisibility(currentState == STATE_LOAD_SUCCESS ? View.VISIBLE : View.GONE);
        }
    }

  /**
   * 创建请求成功的界面
   *
   */
    public abstract View createSuccessView();

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rebu:
            if(currentState!=STATE_UNLOAD){
                currentState=STATE_UNLOAD;
            }
                //添加展示
                showPage();
                //数据的加载
                onload();
            break;
        }

    }
    /**
     * 枚举类
     */
    public enum StateType{
        //请求类型
        STATE_LOAD_ERROR(3),STATE_LOAD_EMPTY(4),STATE_LOAD_SUCCESS(5);

        private final int currentState;

        StateType(int currentState){
            this.currentState=currentState;
        }
        public int getCurrentState(){
            return currentState;
        }
    }
}
