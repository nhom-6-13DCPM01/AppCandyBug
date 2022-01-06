package com.example.appcandybug.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.appcandybug.R;
import com.example.appcandybug.model.Order;
import com.example.appcandybug.server.IMyAPI;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Order order = new Order(1, new Date(), "CHƯA DUYỆT", "", new Date(), 1234);

        IMyAPI.iMyAPI.createOrder(order).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(OrderActivity.this,response.body().toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(OrderActivity.this,"404 HTTP not Found",Toast.LENGTH_SHORT).show();
            }
        });
    }
}