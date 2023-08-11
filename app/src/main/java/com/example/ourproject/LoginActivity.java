package com.example.ourproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "tag";
    private Button btnLogin,btnRegister;
    private EditText etAccent,etPassword;
    private CheckBox cbRemember,cbLogin;

    private String username = "root";
    private String pass = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initData();

        //登录
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = etAccent.getText().toString();
                String password = etPassword.getText().toString();
                Log.d(TAG,"account:" + account);
                Log.d(TAG,"password:" + password);

                if(account.equals(username)){
                    if(password.equals(pass)){
                        Toast.makeText(LoginActivity.this,"恭喜你，登录成功！",Toast.LENGTH_LONG).show();
                        if(cbRemember.isChecked()){
                            SharedPreferences mima = getSharedPreferences("Accent_and_password",MODE_PRIVATE);
                            SharedPreferences.Editor edit = mima.edit();
                            edit.putString("account",account);
                            edit.putString("password",password);
                            edit.putBoolean("isRemember",true);
                            edit.apply();
                        } else {
                            SharedPreferences mima = getSharedPreferences("Accent_and_password",MODE_PRIVATE);
                            SharedPreferences.Editor edit = mima.edit();
                            edit.putBoolean("isRemember",false);
                            edit.apply();
                        }
                        Intent intent = new Intent(LoginActivity.this,PersonActivity.class);
                        intent.putExtra("account",account);
                        startActivity(intent);
                        //结束事件，关闭窗口，不将在点击返回后返回
                        LoginActivity.this.finish();

                    } else {
                        Toast.makeText(LoginActivity.this,"密码错误！",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this,"账号错误或者您还没有账号！",Toast.LENGTH_LONG).show();
                }
            }
        });
        //注册
        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    //读取存储的密码
    private void initData() {
        SharedPreferences mima = getSharedPreferences("Accent_and_password",MODE_PRIVATE);
        boolean isRemember = mima.getBoolean("isRemember",false);
        String account = mima.getString("account","");
        String password = mima.getString("password","");
        if(isRemember){
            etAccent.setText(account);
            etPassword.setText(password);
            cbRemember.setChecked(true);
        }
    }

    //控件
    private void initView() {
        btnLogin = findViewById(R.id.buttonSignIn);
        btnRegister = findViewById(R.id.buttonRegister1);
        etAccent = findViewById(R.id.inputMobile1);
        etPassword = findViewById(R.id.inputPassword1);
        cbRemember = findViewById(R.id.Remember);
    }
}