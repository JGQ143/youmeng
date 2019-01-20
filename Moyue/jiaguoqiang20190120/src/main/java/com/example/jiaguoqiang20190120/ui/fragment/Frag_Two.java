package com.example.jiaguoqiang20190120.ui.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.jiaguoqiang20190120.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Frag_Two extends Fragment {
    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.mBt_dong)
    Button mBtDong;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_two, container, false);
        unbinder = ButterKnife.bind(this, view);
        mBtDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator =ObjectAnimator.ofFloat(imageView,"rotationY",0f,180f);
                ObjectAnimator objectAnimatorr =ObjectAnimator.ofFloat(imageView,"scaleY",1f,3f,1f);
                objectAnimator.setDuration(5000);
                objectAnimatorr.setDuration(5000);
                objectAnimator.start();
                objectAnimatorr.start();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
