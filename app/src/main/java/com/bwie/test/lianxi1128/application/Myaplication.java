package com.bwie.test.lianxi1128.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;
import com.bwie.test.lianxi1128.utils.ImageLoaderUtils;
import org.xutils.x;

/**
 * autour: 陈杰
 * date: 2016/11/28 0028 18:12 
 * update: 2016/11/28 0028
 */
public class Myaplication extends Application {

    private static Context context;
    private static Handler handler;
    private static int mainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();
        //获取当前应用的上下文
        context = getApplicationContext();
        handler = new Handler();
        //获取主线程的线程号
        mainThreadId = Process.myTid();
        //imageLoader初始化
        ImageLoaderUtils.initConfiguration(this);
        //xutils3初始化配置
        x.Ext.init(this);
        //设置是debug模式
        x.Ext.setDebug(true);
    }

    public static int getMainThreadId(){
        return mainThreadId;
    }

    public static Handler getHandler() {
        return handler;
    }

    /**
     * 获取主线程
     * @return
     */
    public static Thread getMainThread(){
        return Thread.currentThread();
    }

    /**
     * 获取整个应用的上下文
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }

}
