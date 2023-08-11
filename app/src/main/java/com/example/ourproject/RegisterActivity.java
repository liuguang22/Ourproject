package com.example.ourproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnRegister;
    private EditText etAccount,etPass,etPassConfirm;
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

        btnRegister.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        String name = etAccount.getText().toString();
        String pass = etPass.getText().toString();
        String passConfirm = etPassConfirm.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(RegisterActivity.this,"账号不能为空！",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(pass)){
            Toast.makeText(RegisterActivity.this,"密码不能为空！",Toast.LENGTH_LONG).show();
            return;
        }

        if(!TextUtils.equals(pass,passConfirm)){
            Toast.makeText(RegisterActivity.this,"密码不一致！",Toast.LENGTH_LONG).show();
            return;
        }

        if(!cbAgree.isChecked()){
            Toast.makeText(RegisterActivity.this,"请先同意用户协议！",Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_LONG).show();
    }
}