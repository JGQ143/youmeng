package com.example.jiaguoqiang20190120.ui.width;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jiaguoqiang20190120.R;

public class GoodView extends LinearLayout implements View.OnClickListener {

    private final Button mBtn_jian,mBtn_add;
    private final TextView text_number;

    public GoodView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.item_goodview, this);
        //获取控件
        mBtn_jian = view.findViewById(R.id.mBtn_jian);
        text_number = view.findViewById(R.id.text_number);
        mBtn_add = view.findViewById(R.id.mBtn_add);
        //监听
        mBtn_jian.setOnClickListener(this);
        mBtn_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String numberString = text_number.getText().toString();
        int number = Integer.parseInt(numberString);
        switch (v.getId()){
            case R.id.mBtn_jian:
                number--;
                if (number<0){
                    number=0;
                    text_number.setText(String.valueOf(number));
                }
                text_number.setText(String.valueOf(number));
                onNumberClickLinstener.jian(number);
                break;
            case R.id.mBtn_add:
                number++;
                text_number.setText(String.valueOf(number));
                onNumberClickLinstener.add(number);
                break;
        }
    }

    //创建接口

    OnNumberClickLinstener onNumberClickLinstener;

    public void setOnNumberClickLinstener(OnNumberClickLinstener onNumberClickLinstener) {
        this.onNumberClickLinstener = onNumberClickLinstener;
    }

    public interface OnNumberClickLinstener{
        public void jian(int number);

        public void add(int number);
    }
}
