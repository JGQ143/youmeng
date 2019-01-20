package com.example.jiaguoqiang20190120.di.model;

import android.util.Log;

import com.example.jiaguoqiang20190120.di.OkHttp.OKUtil;
import com.example.jiaguoqiang20190120.di.beans.GoodBean;
import com.example.jiaguoqiang20190120.di.contract.IGoodContract;
import com.example.jiaguoqiang20190120.di.data.Constant;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;

public class IGoodModelImpl implements IGoodContract.IGoodModel {
    @Override
    public void containGoodData(final CallBack callback) {
        OkGo.<String>get(Constant.GOOD_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String responseData = response.body().toString();
                Log.i("jgq",""+responseData);
                //解析
                Gson gson = new Gson();
                GoodBean goodBean = gson.fromJson(responseData, GoodBean.class);
                //接口回传
                callback.responseData(goodBean);
            }
        });
//        OKUtil.getInstance().get(Constant.GOOD_URL, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                String responseData = e.getMessage();
//                Log.i("jgq",""+responseData);
//            }
//            @Override
//            public void onResponse(Call call, okhttp3.Response response) throws IOException {
//                String responseData = response.body().string();
//                Log.i("jgq","数据"+responseData);
//                //解析
//                  Gson gson = new Gson();
//                  GoodBean goodBean = gson.fromJson(responseData, GoodBean.class);
//                  callback.responseData(goodBean);
//            }
//        });


    }
}
