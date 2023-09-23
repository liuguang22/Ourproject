package com.example.ourproject.Person;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourproject.Bottom.Main;
import com.example.ourproject.R;

public class My_address extends AppCompatActivity implements View.OnClickListener{

    private Button Comeback;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_address);

        Comeback =(Button) findViewById(R.id.comeback);
        Comeback.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        if(view.getId() == R.id.comeback){
            finish();
        }

    }
}
