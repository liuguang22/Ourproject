package com.example.ourproject.home;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.widget.ListView;

import com.example.ourproject.R;
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

public class HomeActivity extends AppCompatActivity {

    private com.example.ourproject.home.shopAdapter shopAdapter = null;
    private RecyclerView recyclerView;
    Gson gson = new Gson();
    private static final String TAG = HomeActivity.class.getSimpleName();
    private ListView list;
    private List<Record> Data;
    private shopAdapter adapter;
    //    private int page = 1;
//    private int mCurrentColIndex = 0;
//
//    private int[] mCols = new int[]{Constants.NEWS_COL5,
//            Constants.NEWS_COL7, Constants.NEWS_COL8,
//            Constants.NEWS_COL10, Constants.NEWS_COL11};
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initData();


//        MyDbOpenHelper myDb = new MyDbOpenHelper(shops.this);
//        SQLiteDatabase db = myDb.getReadableDatabase();
//        Cursor cursor = db.query(
//                shopContract.Entry.TABLE_NAME,
//                null, null, null, null, null, null);
//        List<shop> shopList = new ArrayList<>();
//
//        int titleIndex = cursor.getColumnIndex(
//                shopContract.Entry.COLUMN_NAME_TITLE);
//        int authorIndex = cursor.getColumnIndex(
//                shopContract.Entry.COLUMN_NAME_PRICE);
//        int imageIndex = cursor.getColumnIndex(
//                shopContract.Entry.COLUMN_NAME_IMAGE);
//
//        while (cursor.moveToNext()) {
//            shop shop = new shop();
//
//            String title = cursor.getString(titleIndex);
//            String author = cursor.getString(authorIndex);
//            String image = cursor.getString(imageIndex);
//            Bitmap bitmap = BitmapFactory.decodeStream(
//                    getClass().getResourceAsStream("/" + image));
//
//            shop.setTitle(title);
//            shop.setPrice(author);
//            shop.setImage(bitmap);
//            shopList.add(shop);
//        }


//        recyclerView = findViewById(R.id.sh_list);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
//
//
//        shopAdapter = new shopAdapter(
//                shops.this,
//                R.layout.list_item,
//                shopList
//        );
//
//        recyclerView.setAdapter(shopAdapter);

//        Toast.makeText(shops.this, "正在更新请稍后", Toast.LENGTH_LONG).show();


//        LayoutInflater factory = LayoutInflater.from(shops.this);
//        View layout = factory.inflate(R.layout.list_item, null);
//        RelativeLayout card=layout.findViewById(R.id.card1);
//        card.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(shops.this, "正在更新请稍后", Toast.LENGTH_LONG).show();
////                Intent intent=new Intent(shops.this,shopping.class);
////                startActivity(intent);
//            }
//        });


    }


    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        initView();
//        initData();
//    }
    private void initView() {
        list= findViewById(R.id.sh_list);

//        list.setOnItemClickListener(
//                new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView,
//                                            View view, int i, long l) {
//
//                        Intent intent = new Intent(HomeActivity.this,
//                                shopping.class);
//                        Record s = adapter.getItem(i);
//                        intent.putExtra("shops_id",
//                                s.getId());
////                        Log.d("id",s.getId()+"111");
//
//                        startActivity(intent);
//                    }
//                });
    }
    private void initData() {
        Data = new ArrayList<>();
        adapter = new shopAdapter(HomeActivity.this,
                R.layout.list_item_goods, Data);
        list.setAdapter(adapter);

        refreshData(1);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
//
//
//        shopAdapter = new shopAdapter(
//                shops.this,
//                R.layout.list_item,
//                newsData
//        );
//
//        recyclerView.setAdapter(shopAdapter);
    }

    private void refreshData(final int page) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                shopRequest requestObj = new shopRequest();

//                requestObj.setCol(mCols[mCurrentColIndex]);
//                requestObj.setNum(Constants.NEWS_NUM);
                requestObj.setPage(page);
                String urlParams = requestObj.toString();
                String url = "http://47.107.52.7:88/member/tran/goods/all?userId=124&=";

                Headers headers = new Headers.Builder()
                        .add("Accept", "application/json, text/plain, */*")
                        .add("appId", "48a9b33a1d3541c9a6a1ecf70529bc6c")
                        .add("appSecret", "393466e36025ed6d44a3da4365314b94186be")
                        .build();
                Request request = new Request.Builder()
                        .url(url)
                        // 将请求头加至请求中
                        .headers(headers)
                        .get()
                        .build();
//                Request request = new Request.Builder()
//                        .url(Constants.ALL_NEWS_URL + urlParams)
//                        .get().build();
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