package me.keeganlee.kandroid.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.keeganlee.kandroid.R;
import me.keeganlee.kandroid.app.KBaseActivity;
import me.keeganlee.kandroid.core.ActionCallbackListener;

/**
 * Created by Tian on 2016/5/3.
 */
public class LoginActivity extends KBaseActivity {
    @Bind(R.id.edit_phone)
    EditText phoneEdit;
    @Bind(R.id.edit_password)
    EditText passwordEdit;
    @Bind(R.id.btn_login)
    Button loginBtn;
    @Bind(R.id.btn_goto_register)
    Button btnGotoRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }
    // 初始化View
    private void initViews() {
        phoneEdit = (EditText) findViewById(R.id.edit_phone);
        passwordEdit = (EditText) findViewById(R.id.edit_password);
        loginBtn = (Button) findViewById(R.id.btn_login);
    }

    //准备登陆
    public void toLogin() {
        String loginName = phoneEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        loginBtn.setEnabled(false);
        this.appAction.login(loginName, password, new ActionCallbackListener<Void>() {
            @Override
            public void onSuccess(Void data) {
                Toast.makeText(context, R.string.toast_login_success, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, CouponListActivity.class);
                startActivity(intent);
                finish();
            }
            @Override
            public void onFailure(String errorEvent, String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                loginBtn.setEnabled(true);
            }
        });
    }
    public void toRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    @OnClick({R.id.btn_login, R.id.btn_goto_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                toLogin();
                break;
            case R.id.btn_goto_register:
                toRegister();
                break;
        }
    }
}
