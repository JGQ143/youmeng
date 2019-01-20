package com.example.jiaguoqiang20190120.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jiaguoqiang20190120.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.edit_passwordd)
    EditText editPasswordd;
    @BindView(R.id.mBt_zhu)
    Button mBtZhu;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        //获取sp
        sp = getSharedPreferences("register", Context.MODE_PRIVATE);

    }

    @OnClick(R.id.mBt_zhu)
    public void onViewClicked() {
        String name = editName.getText().toString();
        String pwd = editPassword.getText().toString();
        String pwdd = editPasswordd.getText().toString();
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("editName", Boolean.parseBoolean(name));
        edit.putBoolean("editPassword", Boolean.parseBoolean(pwd));
        edit.putBoolean("editPasswordd", Boolean.parseBoolean(pwdd));
        if (name.equals("")){
            Toast.makeText(RegisterActivity.this,"账号不能为空,长度为11",Toast.LENGTH_LONG).show();
            return;
        }else if(pwd.equals("")){
            Toast.makeText(RegisterActivity.this,"密码不能为空,长度为6",Toast.LENGTH_LONG).show();
            return;
        }else if (pwdd.equals("")){
            Toast.makeText(RegisterActivity.this,"确认密码不能为空,长度为6",Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}
