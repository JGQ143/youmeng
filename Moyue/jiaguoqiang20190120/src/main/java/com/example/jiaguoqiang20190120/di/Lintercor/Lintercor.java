package com.example.jiaguoqiang20190120.di.Lintercor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class Lintercor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        logPain(request);
        Response response = chain.proceed(request);
        return response;
    }

    public void logPain(Request request){
        Log.i("jgq",""+request);
        Log.i("jgq",request.method());
    }
}
