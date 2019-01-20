package com.example.jiaguoqiang20190120.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.jiaguoqiang20190120.R;
import com.example.jiaguoqiang20190120.di.beans.GoodBean;
import com.example.jiaguoqiang20190120.di.contract.IGoodContract;
import com.example.jiaguoqiang20190120.di.presenter.IGoodPresenterImpl;
import com.example.jiaguoqiang20190120.ui.adapter.GoodAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Frag_One extends Fragment implements IGoodContract.IGoodView, View.OnClickListener {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.check_All)
    CheckBox checkAll;
    @BindView(R.id.countPrice)
    TextView countPrice;
    Unbinder unbinder;
    private IGoodContract.IGoodPresenter iGoodPresenter;
    private GoodAdapter goodAdapter;
    private List<GoodBean.DataBean> goodList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_one, container, false);
        //创建presenter
        iGoodPresenter = new IGoodPresenterImpl();
        iGoodPresenter.attach(this);
        iGoodPresenter.requestData();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    //购物车数据
    @Override
    public void goodData(GoodBean goodBean) {
        checkAll.setOnCheckedChangeListener(null);
        checkAll.setOnClickListener(this);
//        Toast.makeText(getActivity(),""+goodBean,Toast.LENGTH_LONG).show();
        goodList = goodBean.getData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        goodAdapter = new GoodAdapter(R.layout.item_good, goodList);
        recyclerview.setAdapter(goodAdapter);

        //内外部选中控制
        goodAdapter.setOnGoodItemClickListener(new GoodAdapter.OnGoodItemClickListener() {
            @Override
            public void CallBack() {
                boolean result = true;
                //外部选中
                for (int i = 0; i <goodList.size(); i++) {
                    boolean onGoodChecked = goodList.get(i).getOnGoodChecked();
                    result=result&onGoodChecked;
                    //内部选中
                    for (int j = 0; j <goodList.get(i).getList().size(); j++) {
                        boolean onGoodsChecked = goodList.get(i).getList().get(j).getOnGoodsChecked();
                        result=result&onGoodsChecked;
                    }
                }
                checkAll.setChecked(result);
                //总价
                count();
            }
        });
    }

    //计算价格
    private void count() {
        double totalPrice=0;
        for (int i = 0; i <goodList.size(); i++) {
            for (int j = 0; j <goodList.get(i).getList().size(); j++) {
                if (goodList.get(i).getList().get(j).getOnGoodsChecked()==true){
                    int defaultNumber = goodList.get(i).getList().get(j).getDefaultNumber();
                    double price = goodList.get(i).getList().get(j).getPrice();
                    double countt = defaultNumber * price;
                    totalPrice=totalPrice+countt;
                }
            }
        }
        countPrice.setText("总价为："+totalPrice);
    }

    //内存泄露
    @Override
    public void onDestroy() {
        super.onDestroy();
        iGoodPresenter.deach(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //全返选
    @Override
    public void onClick(View v) {
        for (int i = 0; i <goodList.size(); i++) {
            goodList.get(i).setOnGoodChecked(checkAll.isChecked());
            for (int j = 0; j <goodList.get(i).getList().size(); j++) {

                goodList.get(i).getList().get(j).setOnGoodsChecked(checkAll.isChecked());
            }
        }
        goodAdapter.notifyDataSetChanged();
        count();
    }
}
