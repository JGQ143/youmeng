package com.example.jiaguoqiang20190120.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jiaguoqiang20190120.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.mBt_deng)
    Button mBtDeng;
    @BindView(R.id.mBt_zhu)
    Button mBtZhu;
    @BindView(R.id.mBt_qq)
    Button mBtQq;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //sp
        sp = getSharedPreferences("login", Context.MODE_PRIVATE);
    }

    @OnClick({R.id.mBt_deng, R.id.mBt_zhu, R.id.mBt_qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mBt_deng:
                String name = editName.getText().toString();
                String password = editPassword.getText().toString();
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("editName",name);
                edit.putString("editPassword",password);
                edit.commit();
                if (name.equals("")){
                    Toast.makeText(LoginActivity.this,"请输入用户名,sj号为11位",Toast.LENGTH_LONG).show();
                    return;
                }else if (password.equals("")){
                    Toast.makeText(LoginActivity.this,"请输入密码,长度为6",Toast.LENGTH_LONG).show();
                    return;
                }else {
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.mBt_zhu:
                Intent intentt = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intentt);
                break;
            case R.id.mBt_qq:

                break;
        }
    }
}
