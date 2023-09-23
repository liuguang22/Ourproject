package com.example.ourproject.Person;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import android.view.View.OnClickListener;

import com.example.ourproject.R;

public class PersonActivity extends AppCompatActivity implements OnClickListener{
    private TextView Myinf,Mycollection,My_orders,My_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        initObj();

        Myinf .setOnClickListener(this);
        Mycollection .setOnClickListener(this);
        My_orders .setOnClickListener(this);
        My_address .setOnClickListener(this);

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


    }

    private void initObj() {
        Myinf =(TextView)findViewById(R.id.my_inf);
        Mycollection =(TextView)findViewById(R.id.my_collection);
        My_orders =(TextView)findViewById(R.id.my_orders);
        My_address =(TextView)findViewById(R.id.my_address);
    }
}
