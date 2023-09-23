package com.example.ourproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.NetworkOnMainThreadException;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class shopAdapter
        extends ArrayAdapter<Record> {

    private List<Record> data;
    private Context context;
    private int resourceId;

    public shopAdapter(Context context,
                       int resourceId, List<Record> data) {
        super(context, resourceId, data);

        this.context = context;
        this.data = data;
        this.resourceId = resourceId;

    }


    @Override
    public View getView(int position,
                        View convertView, ViewGroup parent) {
        Record sh= getItem(position);
        View view ;

        final ViewHolder vh;

        if (convertView == null) {
            view = LayoutInflater.from(getContext())
                    .inflate(resourceId, parent, false);

            vh = new ViewHolder();
            vh.ivPrice = view.findViewById(R.id.tv_price);
            vh.ivTitle  = view.findViewById(R.id.tv_title);
            vh.ivImage = view.findViewById(R.id.iv_image);

            view.setTag(vh);
        } else {
            view = convertView;
            vh = (ViewHolder) view.getTag();
        }
//        if (sh.getImageUrlList() != null ) {
//            Glide.with(context)
//                    .load(sh.getImageUrlList())
//                    .into(vh.ivImage);
//        }

        if (sh.getImageUrlList() != null && sh.getImageUrlList().size()>0 ) {

            Glide.with(context)
                    .load(sh.getImageUrlList().get(0))
                    .into(vh.ivImage);
        }
        vh.ivTitle.setText(sh.getContent());
        vh.ivPrice.setText("￥"+sh.getPrice());

        return view;
    }

    class ViewHolder {
        TextView ivTitle;
        ImageView ivImage;
        TextView ivPrice;

    }
//    private void get(){
//        new Thread(() -> {
//
//            // url路径
//            String url = "http://47.107.52.7:88/member/tran/goods/all?current=0&size=0&typeId=0&keyword=string&userId=0";
//
//            // 请求头
//            Headers headers = new Headers.Builder()
//                    .add("Accept", "application/json, text/plain, */*")
//                    .add("appId", "48a9b33a1d3541c9a6a1ecf70529bc6c")
//                    .add("appSecret", "393466e36025ed6d44a3da4365314b94186be")
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
//            }catch (NetworkOnMainThreadException ex){
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
//        @Override
//        public void onResponse(@NonNull Call call, Response response) throws IOException {
//            //TODO 请求成功处理
//            Type jsonType = new TypeToken<ResponseBody<Object>>(){}.getType();
//            // 获取响应体的json串
//            String body = response.body().string();
//            Log.d("info", body);
//            // 解析json串到自己封装的状态
//
//
//            ResponseBody<Object> dataResponseBody = Gson.fromJson(body,jsonType);
//            Log.d("info", dataResponseBody.toString());
//        }
//    };


}
