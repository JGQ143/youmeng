package com.example.jiaguoqiang20190120.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jiaguoqiang20190120.R;
import com.example.jiaguoqiang20190120.di.beans.GoodBean;
import com.example.jiaguoqiang20190120.ui.width.GoodView;

import java.util.List;

public class GoodsAdapter extends BaseQuickAdapter<GoodBean.DataBean.ListBean,BaseViewHolder> {

    //创建接口
    OnGoodsItemClickListener onGoodsItemClickListener;

    public void setOnGoodsItemClickListener(OnGoodsItemClickListener onGoodsItemClickListener) {
        this.onGoodsItemClickListener = onGoodsItemClickListener;
    }

    public interface OnGoodsItemClickListener{
        public void CallBack();
    }


    public GoodsAdapter(int layoutResId, @Nullable List<GoodBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final GoodBean.DataBean.ListBean item) {
        //获取数据
        helper.setText(R.id.text_goodsName,item.getSubhead());
        //避免焦点抢占
        CheckBox checkbox_goodsName = helper.getView(R.id.checkbox_goodsName);
        checkbox_goodsName.setOnCheckedChangeListener(null);
        checkbox_goodsName.setChecked(item.getOnGoodsChecked());

        helper.setText(R.id.text_goodsPrice,"￥"+item.getPrice());
        ImageView imageView = helper.getView(R.id.image_goods);
        String images = item.getImages();
        String[] split = images.split("\\|");
        Glide.with(mContext).load(split[0]).into(imageView);
        //内部选中
        checkbox_goodsName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setOnGoodsChecked(isChecked);
                //回传
                onGoodsItemClickListener.CallBack();
            }
        });

        //加减
        GoodView goodView = helper.getView(R.id.goodView);
        goodView.setOnNumberClickLinstener(new GoodView.OnNumberClickLinstener() {
            @Override
            public void jian(int number) {
                item.setDefaultNumber(number);
                //回传
                onGoodsItemClickListener.CallBack();
            }

            @Override
            public void add(int number) {
                item.setDefaultNumber(number);
                //回传
                onGoodsItemClickListener.CallBack();
            }
        });
    }
}
