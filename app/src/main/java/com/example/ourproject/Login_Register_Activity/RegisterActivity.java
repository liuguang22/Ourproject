package com.example.ourproject.Login_Register_Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ourproject.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private Boolean bPwdSwitch = false;
    private Button btnRegister;
    private EditText etAccount, etPassword,etPassConfirm;
    private CheckBox cbAgree;
    private final Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        interview();

        btnRegister.setOnClickListener(this);

    }
    private void interview(){
        etAccount = findViewById(R.id.inputMobile2);
        etPassword = findViewById(R.id.inputPassword2);
        etPassConfirm = findViewById(R.id.turePassword);
        cbAgree = findViewById(R.id.agree);
        btnRegister = findViewById(R.id.buttonRegister2);
    }
    @Override
    public void onClick(View v) {
        //if(v.getId() == btnRegister.getId())
        {
            //储存密码
            String spFileName = getResources()
                    .getString(R.string.shared_preferences_file_name);
            String accountKey = getResources()
                    .getString(R.string.login_account_name);
            String passwordKey =  getResources()
                    .getString(R.string.login_password);
            String PasswordConfirmKey = getResources()
                    .getString(R.string.login_confirmpassword);
            String rememberPasswordKey = getResources()
                    .getString(R.string.login_remember_password);

            SharedPreferences spFile = getSharedPreferences(
                    spFileName,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = spFile.edit();
            if(cbAgree.isChecked()){
                String name = etAccount.getText().toString();
                String pass = etPassword.getText().toString();
                String passConfirm = etPassConfirm.getText().toString();

                editor.putString(accountKey,name);
                editor.putString(passwordKey,pass);
                editor.putString(PasswordConfirmKey,passConfirm);
                editor.putBoolean(rememberPasswordKey,true);
                editor.apply();
            } else {
                editor.remove(accountKey);
                editor.remove(passwordKey);
                editor.remove(PasswordConfirmKey);
                editor.remove(rememberPasswordKey);
                editor.apply();
            }
            //判断账号是否为空等情况
            if (TextUtils.isEmpty(etAccount.getText().toString())) {
                Toast.makeText(RegisterActivity.this, "账号不能为空！", Toast.LENGTH_LONG).show();
                return;
            }

            if (TextUtils.isEmpty(etPassword.getText().toString())) {
                Toast.makeText(RegisterActivity.this, "密码不能为空！", Toast.LENGTH_LONG).show();
                return;
            }

            if (!TextUtils.equals(etPassword.getText().toString(), etPassConfirm.getText().toString())) {
                Toast.makeText(RegisterActivity.this, "密码不一致！", Toast.LENGTH_LONG).show();
                return;
            }

            if (!cbAgree.isChecked()) {
                Toast.makeText(RegisterActivity.this, "请先同意用户协议！", Toast.LENGTH_LONG).show();
                return;
            }
            post();
        }


    }


    //post请求
    private void post(){
        new Thread(() -> {

            // url路径
            String url = "http://47.107.52.7:88/member/tran/user/register";

            // 请求头
            Headers headers = new Headers.Builder()
                    .add("appId", "56f251050481428a96cab2420d1e9ce9")
                    .add("appSecret", "368341198e42203e748358d022cb0199c314b")
                    .add("Accept", "application/json, text/plain, */*")
                    .build();

            // 请求体
            // PS.用户也可以选择自定义一个实体类，然后使用类似fastjson的工具获取json串
            Map<String, Object> bodyMap = new HashMap<>();
            bodyMap.put("password", etPassword.getText().toString());
            bodyMap.put("username", etAccount.getText().toString());
            // 将Map转换为字符串类型加入请求体中
            String body = gson.toJson(bodyMap);

            MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

            //请求组合创建
            Request request = new Request.Builder()
                    .url(url)
                    // 将请求头加至请求中
                    .headers(headers)
                    .post(RequestBody.create(MEDIA_TYPE_JSON, body))
                    .build();
            try {
                OkHttpClient client = new OkHttpClient();
                //发起请求，传入callback进行回调
                client.newCall(request).enqueue(callback);
            }catch (NetworkOnMainThreadException ex){
                ex.printStackTrace();
            }
        }).start();
    }

    /**
     * 回调
     */
    private final Callback callback = new Callback() {
        @Override
        public void onFailure(@NonNull Call call, IOException e) {
            //TODO 请求失败处理
            e.printStackTrace();
        }
        @Override
        public void onResponse(@NonNull Call call, Response response) throws IOException {
            //TODO 请求成功处理
            Type jsonType = new TypeToken<ResponseBody<Object>>(){}.getType();
            // 获取响应体的json串
            String body = response.body().string();
            Log.d("info", body);
            // 解析json串到自己封装的状态
            ResponseBody<Object> dataResponseBody = gson.fromJson(body,jsonType);
            Log.d("info", dataResponseBody.toString());
            if(dataResponseBody.getCode() != 200){
                return;
            }
            Intent Result = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("password",etPassword.getText().toString());
            bundle.putString("account",etAccount.getText().toString());
            Result.putExtra("bundle",bundle);
            setResult(RESULT_OK,Result);
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }
    };

    /**
     * http响应体的封装协议
     * @param <T> 泛型
     */
    public static class ResponseBody <T> {

        /**
         * 业务响应码
         */
        private int code;
        /**
         * 响应提示信息
         */
        private String msg;
        /**
         * 响应数据
         */
        private T data;

        public ResponseBody(){}

        public int getCode() {
            return code;
        }
        public String getMsg() {
            return msg;
        }
        public T getData() {
            return data;
        }

        @NonNull
        @Override
        public String toString() {
            return "ResponseBody{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    ", data=" + data +
                    '}';
        }
    }
}