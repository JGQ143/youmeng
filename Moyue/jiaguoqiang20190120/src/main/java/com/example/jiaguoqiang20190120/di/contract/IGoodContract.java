package com.example.jiaguoqiang20190120.di.contract;

import com.example.jiaguoqiang20190120.di.beans.GoodBean;

public class IGoodContract {
    //v层
    public interface IGoodView{
        public void goodData(GoodBean goodBean);
    }

    //p层
    public interface IGoodPresenter<IGoodView>{
        //绑定
        public void attach(IGoodView iGoodView);

        //解绑
        public void deach(IGoodView iGoodView);

        //传值
        public void requestData();
    }
    //m层
    public interface IGoodModel{
        public void containGoodData(CallBack callback);

        //接口回调
        public interface CallBack{
            public void responseData(GoodBean goodBean);
        }
    }
}
