package com.example.jiaguoqiang20190120.di.OkHttp;

import com.example.jiaguoqiang20190120.di.Lintercor.Lintercor;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OKUtil {
    private static OKUtil okUtil;

    private final OkHttpClient okHttpClient;


    //构造私有方法

    public OKUtil(){
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Lintercor())
        .build()
        ;
    }

    public static OKUtil getInstance(){
        if (null == okUtil){
            synchronized (OKUtil.class){
                if (okUtil ==null){
                    return new OKUtil();
                }
            }
        }
        return okUtil;
    }


    public void get(String utrString, Callback callback){
        Request request = new Request.Builder().url(utrString).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
