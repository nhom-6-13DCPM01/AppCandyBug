package com.example.appcandybug.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.appcandybug.R;
import com.example.appcandybug.adapter.CandyAdapter;
import com.example.appcandybug.model.Product;
import com.example.appcandybug.server.CheckConnection;
import com.example.appcandybug.server.IMyAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    Toolbar toolbar_Candy;
    ListView lv_Candy;
    CandyAdapter candyAdapter;
    ArrayList<Product> list_Candy;
    int idCandy = 0;
    int page = 1;
    View footerView;
    boolean isLoading = false;
    mHandler mHandler;
    boolean limitData = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candy);

        anhXa();
        title();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            getIdCate();
            actionToolbar();
            getData(page);
            loadMoreData();
        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Vui lòng kiểm tra kết nối");
        }

    }

    private void title() {
        setSupportActionBar(toolbar_Candy);
        String title= getIntent().getStringExtra("nameCate");
        getSupportActionBar().setTitle(title);
    }

    private void loadMoreData() {
        lv_Candy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),DetailProduct.class);
                intent.putExtra("detailProduct",list_Candy.get(position));
                startActivity(intent);
            }
        });

        lv_Candy.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem+visibleItemCount==totalItemCount && totalItemCount != 0 && isLoading == false && limitData == false){
                        isLoading = true;
                        ThreadDate threadDate = new ThreadDate();
                        threadDate.start();
                }
            }
        });
    }

    private void getData(int Page) {
        IMyAPI.iMyAPI.getProbyCate(idCandy,page).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response!=null && response.body().size()>0){
                    lv_Candy.removeFooterView(footerView);
                    for (com.example.appcandybug.model.Product pro: response.body()) {
                        list_Candy.add(pro);
                    }
                    candyAdapter = new CandyAdapter(getApplicationContext(),list_Candy);
                    lv_Candy.setAdapter(candyAdapter);
                }else {
                    limitData = true;
                    lv_Candy.removeFooterView(footerView);
                    Toast.makeText(getApplicationContext(), "Đã hết dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    private void actionToolbar() {
        setSupportActionBar(toolbar_Candy);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_Candy.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhXa() {
        toolbar_Candy = findViewById(R.id.toolbar_Candy);
        lv_Candy = findViewById(R.id.listview_Candy);
        getIdCate();
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerView = inflater.inflate(R.layout.progressbar,null);
        mHandler = new mHandler();
        list_Candy = new ArrayList<>();
        candyAdapter = new CandyAdapter(getApplicationContext(),list_Candy);
        lv_Candy.setAdapter(candyAdapter);
    }

    private void getIdCate() {
        idCandy = getIntent().getIntExtra("idCategory",-1);

    }

    public class mHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    lv_Candy.addFooterView(footerView);
                    break;
                case 1:
                    getData(++page);
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }

    public class ThreadDate extends Thread{
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }
}