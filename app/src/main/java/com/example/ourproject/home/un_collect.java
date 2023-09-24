package com.example.ourproject.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
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

public class un_collect extends AppCompatActivity implements View.OnClickListener{

    private String t1;
    //    private Record data;
    private TextView text1,text2,c1,c2,c3;
    private ImageView image;
    private Button fh,sc,buy;
    Gson gson = new Gson();
    private ListView list;
    private long id;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.un_collection);
        Intent intent = getIntent();
        image =findViewById(R.id.un_image);
        text1 = findViewById(R.id.un_title);
        text2 = findViewById(R.id.un_price);
        c1=findViewById(R.id.un_mj);
//        c2=findViewById(R.id.xq_ct);
        c3=findViewById(R.id.un_dz);
        text1.setText(intent.getStringExtra("content"));
        text2.setText(intent.getStringExtra("prices"));
//        Log.d("t",intent.getStringExtra("image")+"1");
//        if (intent.getStringExtra("image") != null ) {
        Glide.with(un_collect.this)
                .load(intent.getStringExtra("image"))
                .into(image);

//        image.setImageBitmap((Bitmap)intent.getParcelableExtra("image"));

        c1.setText("卖家用户名： "+intent.getStringExtra("username"));
//        c2.setText(intent.getStringExtra("createtime"));
        c3.setText("卖家地址： "+intent.getStringExtra("addr"));
        fh =(Button) findViewById(R.id.un_fh);
        fh.setOnClickListener(this);
        sc =(Button) findViewById(R.id.un_sc);
        sc.setOnClickListener(this);
        buy =(Button) findViewById(R.id.un_buy);
        buy.setOnClickListener(this);


    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.un_fh){
            finish();
        }
        else if (view.getId()==R.id.un_sc){
            post2();
            Toast.makeText(un_collect.this, "商品已收藏", Toast.LENGTH_SHORT).show();
        }
        else if(view.getId()==R.id.un_buy){
            post();
            Toast.makeText(un_collect.this, "商品已购买", Toast.LENGTH_SHORT).show();
        }

    }
    private void post2(){
        new Thread(() -> {

            // url路径
            String url = "http://47.107.52.7:88/member/tran/goods/save";

            // 请求头
            Headers headers = new Headers.Builder()
                    .add("appId", "56f251050481428a96cab2420d1e9ce9")
                    .add("appSecret", "368341198e42203e748358d022cb0199c314b")
                    .add("Accept", "application/json, text/plain, */*")
                    .build();

            // 请求体
            // PS.用户也可以选择自定义一个实体类，然后使用类似fastjson的工具获取json串
            Map<String, Object> bodyMap = new HashMap<>();
            bodyMap.put("price", (int)getIntent().getFloatExtra("price",0));
            bodyMap.put("imageCode", getIntent().getLongExtra("imagecode",0));
            bodyMap.put("typeName", getIntent().getStringExtra("typename"));
            bodyMap.put("typeId", getIntent().getIntExtra("typeid",0));
            bodyMap.put("id", getIntent().getLongExtra("id",0));
            bodyMap.put("addr", getIntent().getStringExtra("addr"));
            bodyMap.put("userId", "186");
            bodyMap.put("content", getIntent().getStringExtra("content"));
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

    private void post(){
        new Thread(() -> {

            // url路径
            String url = "http://47.107.52.7:88/member/tran/trading";

            // 请求头
            Headers headers = new Headers.Builder()
                    .add("appId", "56f251050481428a96cab2420d1e9ce9")
                    .add("appSecret", "368341198e42203e748358d022cb0199c314b")
                    .add("Accept", "application/json, text/plain, */*")
                    .build();

            // 请求体
            // PS.用户也可以选择自定义一个实体类，然后使用类似fastjson的工具获取json串
            Map<String, Object> bodyMap = new HashMap<>();
            bodyMap.put("sellerId", getIntent().getLongExtra("sid",0));
            bodyMap.put("goodsId", getIntent().getLongExtra("id",0));
            bodyMap.put("price", (int)getIntent().getFloatExtra("price",0));
            bodyMap.put("buyerId", "186");
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

        public void onResponse(Call call, okhttp3.Response response)
                throws IOException {
            String body = response.body().string();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    Type jsonType = new TypeToken<Response<Object>>() {
                    }.getType();
                    // 获取响应体的json串

                    Log.d("info", body);
                    // 解析json串到自己封装的状态
                    Response<Object> dataResponse = gson.fromJson(body, jsonType);
                    Log.d("info", dataResponse.toString());
                }
            });
        }
    };



//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.shopping);
//        Intent intent = getIntent();
//        id = intent.getLongExtra("shops_id", 0);
////        Log.d("id",id+"111");
//        get();
////        final Handler handler = new Handler();
////        handler.post(new Runnable() {
////            @Override
////            public void run() {
////                TextView text1, text2;
////                text1=findViewById(R.id.xq_title);
////                text1=findViewById(R.id.xq_title);
////                text1.setText(t1);
////            }
////        });
//
//
//
//
////        text1.setText(data.getContent());
//
//    }

//    private void updataThrid(){
//        text1.post(new Runnable() {
//            @Override
//            public void run() {
//                text1.setText(t1);
//            }
//        });
//    }

//    private void get() {
//        new Thread(() -> {
//
//            // url路径
//            String url = "http://47.107.52.7:88/member/tran/goods/details?goodsId=" + id;
//
//            // 请求头
//            Headers headers = new Headers.Builder()
//                    .add("appId", "48a9b33a1d3541c9a6a1ecf70529bc6c")
//                    .add("appSecret", "393466e36025ed6d44a3da4365314b94186be")
//                    .add("Accept", "application/json, text/plain, */*")
//                    .build();
//
//            //请求组合创建
//            Request request = new Request.Builder()
//                    .url(url)
//                    // 将请求头加至请求中
//                    .headers(headers)
//                    .get()
//                    .build();
//            try {
//                OkHttpClient client = new OkHttpClient();
//                //发起请求，传入callback进行回调
//                client.newCall(request).enqueue(callback);
//            } catch (NetworkOnMainThreadException ex) {
//                ex.printStackTrace();
//            }
//        }).start();
//    }
//
//    /**
//     * 回调
//     */
//    private final Callback callback = new Callback() {
//        @Override
//        public void onFailure(@NonNull Call call, IOException e) {
//            //TODO 请求失败处理
//            e.printStackTrace();
//        }
//
//        @Override
//        public void onResponse(Call call, okhttp3.Response response) throws IOException {
//            String body = response.body().string();
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//
//                    Type jsonType = new TypeToken<Response<Record>>() {
//                    }.getType();
//                    // 获取响应体的json串
//                    Log.d("info", body);
//                    // 解析json串到自己封装的状态
//                    Response<Record> Data = gson.fromJson(body, jsonType);
//                    Log.d("info", Data.toString());
////                    t1=Data.getData().getContent();
//
//                    }
////            //TODO 请求成功处理
////            Type jsonType = new TypeToken<Response<Record>>(){}.getType();
////            // 获取响应体的json串
////            String body = response.body().string();
////            Log.d("info", body);
////            // 解析json串到自己封装的状态
////            Response<Record> Data = gson.fromJson(body,jsonType);
////            Log.d("info", Data.toString());
////            image = findViewById(R.id.xq_image);
////            text1 = findViewById(R.id.xq_title);
////            text2 = findViewById(R.id.xq_price);
////            text1.setText(Data.getData().getContent());
////            adapter.add(Data.getData());
//                }
//
//            );
//
//        }
//
//
//    };
//
}


