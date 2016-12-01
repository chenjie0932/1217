package com.bwie.test.lianxi1128.base;

import android.text.TextUtils;

import com.bwie.test.lianxi1128.utils.CommonUtils;
import com.bwie.test.lianxi1128.utils.MD5Encoder;
import com.bwie.test.lianxi1128.view.ShowingPager;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * autour: 陈杰
 * date: 2016/11/29 0029 13:39
 * update: 2016/11/29 0029
 */
public abstract class BaseData {
    private  File fileDir;
    public static final int NOTIME = 0;
    public static final int NORMALTIME = 3 * 24 * 60 * 60 * 1000;

    public BaseData(){
        //找到存储路径
        File cacheDir= CommonUtils.getContext().getCacheDir();
        fileDir=new File(cacheDir,"lianxi1128");
        if(!fileDir.exists()){
            //创建文件夹
            fileDir.mkdir();

        }

    }
    //get请求
     public void getData(String path, String args, int index, int validTime){
         if(validTime==0){
             getDataFromNet(path, args, index, validTime);

         }else{
             String data=getDataFromLocal(path, index, validTime);
            if(TextUtils.isEmpty(data)){
                //如果为空，请求网络
                getDataFromNet(path, args, index, validTime);
             }else{
                setResultData(data);
            }
         }
     }
   //post请求
   public void postData(String path, HashMap<String, String> argsMap, int index, int validTime){
       if(validTime==0){
           postDataFromNet(path, argsMap, index, validTime);

       }else{
           String data=getDataFromLocal(path, index, validTime);
           if(TextUtils.isEmpty(data)){
               //如果为空，请求网络
               postDataFromNet(path, argsMap, index, validTime);
           }else{
               setResultData(data);
           }
       }
   }

    private void postDataFromNet(String path, HashMap<String, String> argsMap, int index, int validTime) {
      RequestParams  requestParams=new  RequestParams(path);
        //将Map中的参数取出
      Set<Map.Entry<String,String>>  entries=argsMap.entrySet();
        for (Map.Entry<String,String> entry:entries
             ) {
            requestParams.addParameter(entry.getKey(),entry.getValue());
        }
        x.http().post(requestParams, new Callback.CommonCallback<String>() {

           @Override
           public void onSuccess(String s) {

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

    public abstract  void setResultData(String data) ;

    private String getDataFromLocal(String path, int index, int validTime) {
       //读取文件信息
        //读时间
        try {
            File file=new File(fileDir,MD5Encoder.encode(path)+index);
            BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
               String s=bufferedReader.readLine();
            Long time=Long.parseLong(s);
            if(System.currentTimeMillis()<time){
                StringBuilder builder=new StringBuilder();
                String line=null;
                while ((line=bufferedReader.readLine())!=null){
                    //拼接
                    builder.append(line);
                }
                bufferedReader.close();
//                Log.i("12345","builder.toString()"+builder.toString());
                return  builder.toString();

            }else {
                return  null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    private void getDataFromNet(final String path, final String args, final int index, final int validTime) {
        RequestParams requestParams=new RequestParams(path+"?"+args);
       x.http().get(requestParams, new Callback.CommonCallback<String>() {

           @Override
           public void onSuccess(String s) {
               //请求成功，返回数据
               setResultData(s);

               //缓存到本地
               writeDataToLocal(path,args,index,validTime,s);
           }

           @Override
           public void onError(Throwable throwable, boolean b) {
                 //请求失败
               setResultError(ShowingPager.StateType.STATE_LOAD_ERROR);
           }

           @Override
           public void onCancelled(CancelledException e) {

           }

           @Override
           public void onFinished() {

           }
       });
    }

    public abstract void setResultError(ShowingPager.StateType stateLoadError);


    private void writeDataToLocal(String path, String args, int index, int validTime, String data) {
          //每一条请求都是生成一个文件
        try {
            File file=new File(fileDir, MD5Encoder.encode(path)+index);
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(System.currentTimeMillis()+validTime+"\r\n");

            //从网络上请求的数据
            bufferedWriter.write(data);
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
