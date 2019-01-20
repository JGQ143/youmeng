package com.example.jiaguoqiang20190120.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jiaguoqiang20190120.R;
import com.example.jiaguoqiang20190120.di.beans.GoodBean;

import java.util.List;

public class GoodAdapter extends BaseQuickAdapter<GoodBean.DataBean,BaseViewHolder> {

    //创建接口
    OnGoodItemClickListener onGoodItemClickListener;

    public void setOnGoodItemClickListener(OnGoodItemClickListener onGoodItemClickListener) {
        this.onGoodItemClickListener = onGoodItemClickListener;
    }

    public interface OnGoodItemClickListener{
        public void CallBack();
    }

    public GoodAdapter(int layoutResId, @Nullable List<GoodBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final GoodBean.DataBean item) {
        helper.setText(R.id.checkbox_goodName,item.getSellerName());
        //避免焦点抢占
        final CheckBox checkbox_goodName = helper.getView(R.id.checkbox_goodName);
        checkbox_goodName.setOnCheckedChangeListener(null);
        checkbox_goodName.setChecked(item.getOnGoodChecked());

        //获取子数据
        RecyclerView recyclerview_goods = helper.getView(R.id.recyclerview_goods);
        List<GoodBean.DataBean.ListBean> goodsList = item.getList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerview_goods.setLayoutManager(linearLayoutManager);
        //创建adapter
        final GoodsAdapter goodsAdapter = new GoodsAdapter(R.layout.item_goods,goodsList);
        recyclerview_goods.setAdapter(goodsAdapter);

        //内部控制外部
        goodsAdapter.setOnGoodsItemClickListener(new GoodsAdapter.OnGoodsItemClickListener() {
            @Override
            public void CallBack() {
                boolean result = true;
                for (int i = 0; i <item.getList().size(); i++) {
                    result=result&item.getList().get(i).getOnGoodsChecked();
                }
                    checkbox_goodName.setChecked(result);
                    goodsAdapter.notifyDataSetChanged();
                    onGoodItemClickListener.CallBack();
            }
        });

        //外部控制内部

        checkbox_goodName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i <item.getList().size(); i++) {
                    item.getList().get(i).setOnGoodsChecked(checkbox_goodName.isChecked());
                }
                    item.setOnGoodChecked(checkbox_goodName.isChecked());
                    notifyDataSetChanged();
                    onGoodItemClickListener.CallBack();
            }
        });
    }
}
