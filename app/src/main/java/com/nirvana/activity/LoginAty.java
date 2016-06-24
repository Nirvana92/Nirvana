package com.nirvana.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nirvana.nirvana.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Nirvana on 2016/6/24.
 */
public class LoginAty extends BaseActivity {

    @Bind(R.id.usernameEdt)
    EditText usernameEdt;
    @Bind(R.id.passwordEdt)
    EditText passwordEdt;

    @Bind(R.id.loginBtn)
    Button loginBtn;

    @OnClick(R.id.loginBtn)
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                login();
                break;
        }
    }

    /**
     * 登录
     */
    void login() {
        String username = usernameEdt.getText().toString().trim();
        String password = passwordEdt.getText().toString().trim();
    }

    @Override
    protected int getLayout() {
        return R.layout.aty_login;
    }
}
