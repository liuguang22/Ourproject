package com.example.ourproject.Person;

import android.content.Intent;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourproject.R;
import com.example.ourproject.home.HomeActivity;
import com.example.ourproject.home.Record;
import com.example.ourproject.home.Response;
import com.example.ourproject.home.shop;
import com.example.ourproject.home.shopRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class My_orders extends AppCompatActivity implements View.OnClickListener{

    private Button Comeback;
    Gson gson = new Gson();
    private static final String TAG = HomeActivity.class.getSimpleName();
    private ListView list;
    private List<Record> Data;
    private orderadapter adapter;
    private TextView textView;

    private okhttp3.Callback callback = new okhttp3.Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.e(TAG, "Failed to connect server!");
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, okhttp3.Response response)
                throws IOException {
            String body = response.body().string();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    Type jsonType = new TypeToken<Response<shop>>() {
                    }.getType();
                    // 获取响应体的json串

                    Log.d("info", body);
                    // 解析json串到自己封装的状态
                    Response<shop> dataResponse = gson.fromJson(body, jsonType);
                    Log.d("info", dataResponse.toString());
                    for (Record s : dataResponse.getData().getRecords()) {
//                        for (Record ss : s.getRecords()) {
                        adapter.add(s);
//                        }

                    }

                    adapter.notifyDataSetChanged();
                }
            });

        }

    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bg);

        textView = (TextView)findViewById(R.id.Top_name);
        textView.setText("我的订单");

        Comeback =(Button) findViewById(R.id.comeback);
        Comeback.setOnClickListener(this);

        initView();
        initData();
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        if(view.getId() == R.id.comeback){
            finish();
        }

    }

    private void initView() {
        list = findViewById(R.id.goods_list);
    }

    private void initData() {
        Data = new ArrayList<>();
        adapter = new orderadapter(My_orders.this,
                R.layout.my_orders, Data);
        list.setAdapter(adapter);

        refreshData(1);
    }

    private void refreshData(final int page) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                shopRequest requestObj = new shopRequest();

                requestObj.setPage(page);
                String urlParams = requestObj.toString();
                String url = "http://47.107.52.7:88/member/tran/trading/buy?userId=186&=";

                Headers headers = new Headers.Builder()
                        .add("Accept", "application/json, text/plain, */*")
                        .add("appId", "56f251050481428a96cab2420d1e9ce9")
                        .add("appSecret", "368341198e42203e748358d022cb0199c314b")
                        .build();
                Request request = new Request.Builder()
                        .url(url)
                        // 将请求头加至请求中
                        .headers(headers)
                        .get()
                        .build();
                try {
                    OkHttpClient client = new OkHttpClient();
                    client.newCall(request).enqueue(callback);
                } catch (NetworkOnMainThreadException ex) {

                    ex.printStackTrace();
                }
            }
        }).start();
    }

}
