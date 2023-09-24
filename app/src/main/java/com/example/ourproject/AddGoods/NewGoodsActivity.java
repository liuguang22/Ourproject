package com.example.ourproject.AddGoods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;

import com.example.ourproject.data.add;
import com.example.ourproject.data.image;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ourproject.home.HomeActivity;
import com.example.ourproject.R;

import android.text.TextUtils;

import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NewGoodsActivity extends AppCompatActivity {

    private final Gson gson = new Gson();
    private static final int REQUEST_IMAGE_SELECT = 1; // 添加常量用于标识图像选择请求
    private ImageView ivProduct;
    private Button btnPublish;
    private EditText productNameEditText;
    private EditText productPriceEditText;
    private EditText productDescriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgoods);

        ivProduct = findViewById(R.id.iv_product);
        btnPublish = findViewById(R.id.up);
        productNameEditText = findViewById(R.id.et_1);
        productPriceEditText = findViewById(R.id.et_3);
        productDescriptionEditText = findViewById(R.id.et_2);

        // 设置点击事件监听器
        ivProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 打开图像选择器或相册
//                Log.d("debug", "onClick: ");
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, REQUEST_IMAGE_SELECT);
                openSysAlbum();
            }
        });


        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里获取商品信息并发布
                String imageCode = "imageCode"; // 替换为获取的图片imageCode
                String name = productNameEditText.getText().toString().trim();
                String price = productPriceEditText.getText().toString().trim();
                String description = productDescriptionEditText.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(NewGoodsActivity.this, "请填写商品名称", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(price)) {
                    Toast.makeText(NewGoodsActivity.this, "请填写商品价格", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(description)) {
                    Toast.makeText(NewGoodsActivity.this, "请填写商品描述", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 先上传图片
                File imageFile = new File("path/to/image.jpg"); // 替换为实际的图片文件路径
                uploadImage(imageFile, new UploadImageCallback() {
                    @Override
                    public void onSuccess(String imageCode) {
                        // 图片上传成功后，调用添加商品信息的方法
                        addGoods(imageCode, name, price, description);
                    }

                    @Override
                    public void onFailure() {
                        // 图片上传失败处理
                        Toast.makeText(NewGoodsActivity.this, "图片上传失败", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
            }
        });
    }
    private void openSysAlbum() {
        Intent albumIntent = new Intent(Intent.ACTION_PICK);
        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(albumIntent, REQUEST_IMAGE_SELECT);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_SELECT && resultCode == RESULT_OK) {
            if (data != null) {
                Uri selectedImageUri = data.getData();
                // 从选定的图像URI获取实际路径或使用它进行上传
                String imagePath = getImagePathFromUri(selectedImageUri);
                // 执行图像上传操作
                File imageFile = new File(imagePath);
                uploadImage(imageFile, new UploadImageCallback() {
                    @Override
                    public void onSuccess(String imageCode) {
                        // 图片上传成功后，调用添加商品信息的方法
                        String name = productNameEditText.getText().toString().trim();
                        String price = productPriceEditText.getText().toString().trim();
                        String description = productDescriptionEditText.getText().toString().trim();

                        if (TextUtils.isEmpty(name)) {
                            Toast.makeText(NewGoodsActivity.this, "请填写商品名称", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(price)) {
                            Toast.makeText(NewGoodsActivity.this, "请填写商品价格", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(description)) {
                            Toast.makeText(NewGoodsActivity.this, "请填写商品描述", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Log.d("imageCode",imageCode);
                        // 图片上传成功后，调用添加商品信息的方法
                        addGoods(imageCode, name, price, description);
                    }

                    @Override
                    public void onFailure() {
                        // 图片上传失败处理
                        Toast.makeText(NewGoodsActivity.this, "图片上传失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    // 实现 getImagePathFromUri 方法
    private String getImagePathFromUri(Uri uri) {
        String imagePath = null;
        if (uri != null) {
            ContentResolver contentResolver = getContentResolver();
            Cursor cursor = contentResolver.query(uri, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                    if (columnIndex != -1) {
                        imagePath = cursor.getString(columnIndex);
                    }
                }
                cursor.close();
            }
        }
        return imagePath;
    }


    // 上传图片
    private void uploadImage(File imageFile, final UploadImageCallback callback) {
        // url路径
        String url = "http://47.107.52.7:88/member/tran/image/upload";

        // 请求头
        Headers headers = new Headers.Builder()
                .add("Accept", "application/json, text/plain, */*")
                .add("appId", "56f251050481428a96cab2420d1e9ce9")
                .add("appSecret", "368341198e42203e748358d022cb0199c314b")
                .add("Content-Type", "multipart/form-data")
                .build();

        // 创建请求体，包含图片文件
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", imageFile.getName(), RequestBody.create(MediaType.parse("image/jpeg"), imageFile))
                .build();

        // 创建请求
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .post(requestBody)
                .build();

        try {
            OkHttpClient client = new OkHttpClient();
            // 发起请求，传入callback进行回调
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, IOException e) {
                    // 请求失败处理
                    e.printStackTrace();
                    callback.onFailure();
                }

                @Override
                public void onResponse(@NonNull Call call, Response response) throws IOException {
                    Type jsonType = new TypeToken<image>() {}.getType();
                    // 获取响应体的JSON串
                    String responseBody = response.body().string();
                    // 解析JSON串到自己封装的状态
                    image dataResponseBody = gson.fromJson(responseBody, jsonType);
                    if (dataResponseBody.getCode() == 200) {
                        // 图片上传成功，获取imageCode并回调
                        String imageCode = dataResponseBody.getData().toString();
                        callback.onSuccess(imageCode);
                    } else {
                        // 图片上传失败处理
                        Toast.makeText(NewGoodsActivity.this, "图片上传失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            callback.onFailure();
        }
    }

    // 添加商品信息
    private void addGoods(String imageCode, String name, String price, String description) {
        // url路径
        String url = "http://47.107.52.7:88/member/tran/goods/add";

        // 请求头
        Headers headers = new Headers.Builder()
                .add("Accept", "application/json, text/plain, */*")
                .add("appId", "56f251050481428a96cab2420d1e9ce9")
                .add("appSecret", "368341198e42203e748358d022cb0199c314b")
                .add("Content-Type", "application/json")
                .build();

        // 创建请求体，包含商品信息
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("imageCode", imageCode);
        requestBody.put("name", name);
        requestBody.put("price", price);
        requestBody.put("description", description);
        String jsonBody = gson.toJson(requestBody);

        // 创建请求
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .post(RequestBody.create(MediaType.parse("application/json"), jsonBody))
                .build();

        try {
            OkHttpClient client = new OkHttpClient();
            // 发起请求，传入callback进行回调
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, IOException e) {
                    // 请求失败处理
                    e.printStackTrace();
                    Toast.makeText(NewGoodsActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(@NonNull Call call, Response response) throws IOException {
                    Type jsonType = new TypeToken<add>() {}.getType();
                    // 获取响应体的JSON串
                    String responseBody = response.body().string();
                    // 解析JSON串到自己封装的状态
                    add dataResponseBody = gson.fromJson(responseBody, jsonType);
                    if (dataResponseBody.getCode() == 200) {
                        // 商品信息添加成功
                        Toast.makeText(NewGoodsActivity.this, "商品信息添加成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 商品信息添加失败处理
                        Toast.makeText(NewGoodsActivity.this, "商品信息添加失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(NewGoodsActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
        }
    }

    // 回调接口，用于处理图片上传结果
    interface UploadImageCallback {
        void onSuccess(String imageCode);

        void onFailure();
    }
}