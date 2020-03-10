package com.example.zuoye.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;


import com.example.shopping.ui.login.RegisterActivity;
import com.example.zuoye.R;
import com.example.zuoye.base.BaseActivity;
import com.example.zuoye.interfaces.login.LoginContract;
import com.example.zuoye.model.bean.UserBean;
import com.example.zuoye.presenter.login.LoginPersenter;
import com.example.zuoye.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity<LoginContract.View,LoginContract.Persenter> implements LoginContract.View {


    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.zuce_login)
    TextView zuceLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private String name;
    private String password;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        zuceLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, RegisterActivity.class);
                startActivityForResult(intent,100);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persenter.login(name,password);

            }
        });
    }


    @Override
    protected void initData() {

    }

    @Override
    protected LoginPersenter createPersenter() {
        return new LoginPersenter();
    }

    @Override
    public void loginReturn(UserBean result) {
        if (result.getData().getCode() == 200)
            Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();

        SharedPreferencesUtil.getInstance().setValue(editUsername.getText().toString(),editUsername.getText().toString());
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200){
            name = data.getStringExtra("name");
            password = data.getStringExtra("password");
        }
    }
}
