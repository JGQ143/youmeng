package com.example.jiaguoqiang20190120.ui.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;


import com.example.jiaguoqiang20190120.R;
import com.example.jiaguoqiang20190120.ui.fragment.Frag_One;
import com.example.jiaguoqiang20190120.ui.fragment.Frag_Two;


public class HomeActivity extends AppCompatActivity{

    private RadioGroup radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //开启
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.flow,new Frag_One());
        transaction.commit();
        radio = findViewById(R.id.radio);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                switch (checkedId){
                    case R.id.gou:
                        transaction1.replace(R.id.flow,new Frag_One());
                        break;
                    case R.id.wo:
                        transaction1.replace(R.id.flow,new Frag_Two());
                        break;
                }
                transaction1.commit();
            }
        });
    }

}
