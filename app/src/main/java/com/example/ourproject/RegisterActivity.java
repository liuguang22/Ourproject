package com.example.ourproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

@Deprecated
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegister;
    private EditText etAccount, etPass, etPassConfirm;
    private CheckBox cbAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etAccount = findViewById(R.id.inputMobile2);
        etPass = findViewById(R.id.inputPassword2);
        etPassConfirm = findViewById(R.id.turePassword);
        cbAgree = findViewById(R.id.agree);
        btnRegister = findViewById(R.id.buttonRegister2);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onClick(View v) {
        String name = etAccount.getText().toString();
        String pass = etPass.getText().toString();
        String passConfirm = etPassConfirm.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(RegisterActivity.this, "账号不能为空！", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(RegisterActivity.this, "密码不能为空！", Toast.LENGTH_LONG).show();
            return;
        }

        if (!TextUtils.equals(pass, passConfirm)) {
            Toast.makeText(RegisterActivity.this, "密码不一致！", Toast.LENGTH_LONG).show();
            return;
        }

        if (!cbAgree.isChecked()) {
            Toast.makeText(RegisterActivity.this, "请先同意用户协议！", Toast.LENGTH_LONG).show();
            return;
        }

        //存储账号密码
        SharedPreferences mima = getSharedPreferences("Accent_and_password", MODE_PRIVATE);
        SharedPreferences.Editor edit = mima.edit();
        edit.putString("account", name);
        edit.putString("password", pass);
        edit.putBoolean("isRemember", true);

        //数据回传
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("account", name);
        bundle.putString("password", pass);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);

        Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_LONG).show();
        this.finish();
    }
}