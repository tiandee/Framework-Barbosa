package me.keeganlee.kandroid.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.keeganlee.kandroid.R;
import me.keeganlee.kandroid.app.KBaseActivity;
import me.keeganlee.kandroid.core.ActionCallbackListener;

/**
 * Created by Tian on 2016/5/3.
 */
public class RegisterActivity extends KBaseActivity {
    @Bind(R.id.edit_phone)
    EditText phoneEdit;
    @Bind(R.id.btn_send_code)
    Button sendCodeBtn;
    @Bind(R.id.edit_code)
    EditText codeEdit;
    @Bind(R.id.edit_password)
    EditText passwordEdit;
    @Bind(R.id.btn_register)
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        getActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }

    // 准备发送验证码
    public void toSendCode(View view) {
        String phoneNum = phoneEdit.getText().toString();
        sendCodeBtn.setEnabled(false);
        this.appAction.senSmsCode(phoneNum, new ActionCallbackListener<Void>() {
            @Override
            public void onSuccess(Void data) {
                Toast.makeText(context, R.string.toast_code_has_sent, Toast.LENGTH_SHORT).show();
                sendCodeBtn.setEnabled(true);
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                sendCodeBtn.setEnabled(true);
            }
        });
    }

    // 准备注册
    public void toRegister(View view) {
        String phoneNum = phoneEdit.getText().toString();
        String code = codeEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        registerBtn.setEnabled(false);
        this.appAction.register(phoneNum, code, password, new ActionCallbackListener<Void>() {
            @Override
            public void onSuccess(Void data) {
                Toast.makeText(context, R.string.toast_register_success, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, CouponListActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                registerBtn.setEnabled(true);
            }
        });
    }
}
