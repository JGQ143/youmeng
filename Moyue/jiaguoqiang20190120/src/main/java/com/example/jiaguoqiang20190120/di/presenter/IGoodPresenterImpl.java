package com.example.jiaguoqiang20190120.di.presenter;

import com.example.jiaguoqiang20190120.di.beans.GoodBean;
import com.example.jiaguoqiang20190120.di.contract.IGoodContract;
import com.example.jiaguoqiang20190120.di.model.IGoodModelImpl;

import java.lang.ref.SoftReference;

public class IGoodPresenterImpl implements IGoodContract.IGoodPresenter<IGoodContract.IGoodView> {

    IGoodContract.IGoodView iGoodView;
    private SoftReference<IGoodContract.IGoodView> iGoodViewSoftReference;
    private IGoodContract.IGoodModel iGoodModel;

    @Override
    public void attach(IGoodContract.IGoodView iGoodView) {
        this.iGoodView=iGoodView;
        //软引用
        iGoodViewSoftReference = new SoftReference<>(iGoodView);
        //创建model
        iGoodModel = new IGoodModelImpl();
    }

    @Override
    public void deach(IGoodContract.IGoodView iGoodView) {
        iGoodViewSoftReference.clear();
    }

    @Override
    public void requestData() {
        iGoodModel.containGoodData(new IGoodContract.IGoodModel.CallBack() {
            @Override
            public void responseData(GoodBean goodBean) {
                iGoodView.goodData(goodBean);
            }
        });
    }
}
