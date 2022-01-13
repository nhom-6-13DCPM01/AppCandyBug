package com.example.appcandybug.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.appcandybug.R;
import com.example.appcandybug.adapter.MyOrderAdapter;
import com.example.appcandybug.model.Account;
import com.example.appcandybug.model.Mycart;
import com.example.appcandybug.server.IMyAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrder extends AppCompatActivity {
    ListView listMyOrder;
    MyOrderAdapter myOrderAdapter;
    List<Mycart> myOrderList;
    Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        anhXa();
        checkInput();
        loadData();
    }

    private void loadData() {
        account = getIntent().getParcelableExtra("accountMyOrder");
        IMyAPI.iMyAPI.viewOrder(account.getId()).enqueue(new Callback<List<Mycart>>() {
            @Override
            public void onResponse(Call<List<Mycart>> call, Response<List<Mycart>> response) {
                if(response!=null){
                    myOrderList = response.body();
                    myOrderAdapter = new MyOrderAdapter(getApplicationContext(), (ArrayList<Mycart>) myOrderList);
                    listMyOrder.setAdapter(myOrderAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Mycart>> call, Throwable t) {
                Log.d("Lỗi",t.toString());
            }
        });
    }

    private void checkInput() {
    }

    public void anhXa(){
        listMyOrder = findViewById(R.id.listview_myorder);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}