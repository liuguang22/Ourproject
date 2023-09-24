package com.example.ourproject.Login_Register_Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Looper;
import android.os.NetworkOnMainThreadException;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ourproject.Bottom.bottom;
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

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "tag";
    public static final int INTENT_TO_REGISTE = 256;
    private Button btnLogin, btnRegister;
    private EditText etAccent, etPassword;
    private CheckBox cbRemember, cbLogin;
    private ImageView ivVisibility;
    private final Gson gson = new Gson();
    public static String username ;
    public static String pass ;
    private Boolean bPwdSwitch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();

        // 密码 可见\不可见 状态
        ivVisibility.setOnClickListener(view -> {
            bPwdSwitch = !bPwdSwitch;
            if(bPwdSwitch){
                ivVisibility.setImageResource(R.drawable.baseline_visibility_24);
                etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }else{
                ivVisibility.setImageResource(R.drawable.baseline_visibility_off_24);
                etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                etPassword.setTypeface(Typeface.DEFAULT);
            }
        });

        //登录
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = etAccent.getText().toString();
                String password = etPassword.getText().toString();

                if (cbRemember.isChecked()) {
                    SharedPreferences mima = getSharedPreferences("Accent_and_password", MODE_PRIVATE);
                    SharedPreferences.Editor edit = mima.edit();
                    edit.putString("account", account);
                    edit.putString("password", password);
                    edit.putBoolean("isRemember", true);
                    edit.apply();
                } else {
                    SharedPreferences mima = getSharedPreferences("Accent_and_password", MODE_PRIVATE);
                    SharedPreferences.Editor edit = mima.edit();
                    edit.putBoolean("isRemember", false);
                    edit.apply();
                }
                if (TextUtils.isEmpty(account)) {
                    // 用户名为空
                    Toast.makeText(LoginActivity.this, "账号为空", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    // 密码为空
                    Toast.makeText(LoginActivity.this, "密码为空", Toast.LENGTH_LONG).show();
                    return;
                }
                post();
            }
        });
        //注册
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent,INTENT_TO_REGISTE);
                LoginActivity.this.finish();
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == INTENT_TO_REGISTE && resultCode == RESULT_OK)
        {
            Bundle bundle = data.getBundleExtra("bundle");
            etAccent.setText(bundle.getString("account"));
            etPassword.setText(bundle.getString("password"));
        }
    }

    //读取存储的密码
    private void initData() {
        String spFileName = getResources()
                .getString(R.string.shared_preferences_file_name);
        String accountKey = getResources()
                .getString(R.string.login_account_name);
        String passwordKey =  getResources()
                .getString(R.string.login_password);
        String rememberPasswordKey = getResources()
                .getString(R.string.login_remember_password);

        SharedPreferences spFile = getSharedPreferences(
                spFileName,
                MODE_PRIVATE);
        String account = spFile.getString(accountKey, null);
        String password = spFile.getString(passwordKey, null);
        Boolean rememberPassword = spFile.getBoolean(
                rememberPasswordKey, false);

        if (account != null && !TextUtils.isEmpty(account)) {
            etAccent.setText(account);
        }

        if (password != null && !TextUtils.isEmpty(password)) {
            etPassword.setText(password);
        }

        cbRemember.setChecked(rememberPassword);
    }


    //控件
    private void initView() {
        btnLogin = findViewById(R.id.buttonSignIn);
        btnRegister = findViewById(R.id.buttonRegister1);
        etAccent = findViewById(R.id.inputMobile1);
        etPassword = findViewById(R.id.inputPassword1);
        ivVisibility = findViewById(R.id.iv_visibility);
        cbRemember = findViewById(R.id.Remember);
    }

    private void post() {
        new Thread(() -> {

            // url路径
            String url = "http://47.107.52.7:88/member/tran/user/login";

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
            bodyMap.put("username", etAccent.getText().toString());
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
            } catch (NetworkOnMainThreadException ex) {
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
            Type jsonType = new TypeToken<ResponseBody>() {
            }.getType();
            // 获取响应体的json串
            String body = response.body().string();
            Log.d("info", body);
            // 解析json串到自己封装的状态
            ResponseBody dataResponseBody = gson.fromJson(body, jsonType);
            Log.d("info", dataResponseBody.toString());
            if(dataResponseBody.getCode() == 200){
                Intent intent = new Intent(LoginActivity.this, bottom.class);
                intent.putExtra("username",dataResponseBody.getData().getUsername());
                intent.putExtra("userId",dataResponseBody.getData().getId());
                startActivity(intent);
            }
            else{
                Looper.prepare();
                Toast.makeText(LoginActivity.this,dataResponseBody.getMsg(),Toast.LENGTH_SHORT).show();
            }
        }
    };

    public static class ResponseBody {

        private String msg;
        private int code;
        private Data data;
        public void setMsg(String msg) {
            this.msg = msg;
        }
        public String getMsg() {
            return msg;
        }

        public void setCode(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }

        public void setData(Data data) {
            this.data = data;
        }
        public Data getData() {
            return data;
        }


        public static class Data {

            private String appKey;
            private String avatar;
            private long id;
            private int money;
            private String password;
            private String username;

            public void setAppKey(String appKey) {
                this.appKey = appKey;
            }

            public String getAppKey() {
                return appKey;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setId(long id) {
                this.id = id;
            }

            public long getId() {
                return id;
            }

            public void setMoney(int money) {
                this.money = money;
            }

            public int getMoney() {
                return money;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getPassword() {
                return password;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getUsername() {
                return username;
            }
        }
    }
}