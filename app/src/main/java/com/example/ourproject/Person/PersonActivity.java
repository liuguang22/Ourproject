package com.example.ourproject.Person;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.view.View.OnClickListener;

import com.example.ourproject.R;

public class PersonActivity extends AppCompatActivity implements OnClickListener{
    private TextView Myinf,Mycollection,My_orders,My_address;
    private TextView account_name,account_id;
    private Long userId;
    private String username;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        initObj();

        userId = getIntent().getLongExtra("userId",0);
        username = getIntent().getStringExtra("username");
        account_name.setText("用户昵称："+username);
        account_id.setText(String.valueOf("用户id："+userId));

        Myinf .setOnClickListener(this);
        Mycollection .setOnClickListener(this);
        My_orders .setOnClickListener(this);
        My_address .setOnClickListener(this);
        btn.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        Intent intent = null;
        if(view.getId()==R.id.my_inf){
            intent = new Intent(PersonActivity.this, My_inf.class);
            startActivity(intent);
        }
        if(view.getId()==R.id.my_collection){
            intent = new Intent(PersonActivity.this, My_collection.class);
            startActivity(intent);
        }
        if(view.getId()==R.id.my_orders){
            intent = new Intent(PersonActivity.this, com.example.ourproject.Person.My_orders.class);
            startActivity(intent);
        }
        if(view.getId()==R.id.my_address){
            intent = new Intent(PersonActivity.this, com.example.ourproject.Person.My_address.class);
            startActivity(intent);
        }

        if(view.getId()==R.id.button){
            intent=new Intent(PersonActivity.this, com.example.ourproject.Login_Register_Activity.LoginActivity.class);
            startActivity(intent);
        }


    }

    private void initObj() {
        account_name = findViewById(R.id.username);
        account_id = findViewById(R.id.userId);
        btn =(Button) findViewById(R.id.button);
        Myinf = findViewById(R.id.my_inf);
        Mycollection = findViewById(R.id.my_collection);
        My_orders = findViewById(R.id.my_orders);
        My_address = findViewById(R.id.my_address);
    }
}
