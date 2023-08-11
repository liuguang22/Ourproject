package com.example.ourproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PersonActivity extends AppCompatActivity {

    private TextView tvContent;
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        tvContent = findViewById(R.id.tv_welcome);
        exit = findViewById(R.id.exit);
        Intent intent = getIntent();
        String account = intent.getStringExtra("account");
        tvContent.setText("欢迎你："+account);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}