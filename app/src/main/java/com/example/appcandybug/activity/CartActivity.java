package com.example.appcandybug.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.example.appcandybug.R;
import com.example.appcandybug.adapter.CartAdapter;

import java.text.DecimalFormat;

public class CartActivity extends AppCompatActivity {
    ListView lvCart;
    TextView txtThongBao;
    TextView txtTongTien;
    Button btnThanhToan, btnTiepTucMua;
    Toolbar toolBarCart;
    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        anhXa();
        actionBar();
        checkData();
        eventUntil();
    }

    private void eventUntil() {
        double tongTien = 0;
        for(int i = 0; i < Index.mangCart.size(); i++){
            tongTien += Index.mangCart.get(i).getGiaSP();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTongTien.setText(decimalFormat.format(tongTien) + " Đ");
    }

    private void checkData() {
        if(Index.mangCart.size() <= 0){
            cartAdapter.notifyDataSetChanged();
            txtThongBao.setVisibility(View.VISIBLE);
            lvCart.setVisibility(View.INVISIBLE);
        }else {
            cartAdapter.notifyDataSetChanged();
            txtThongBao.setVisibility(View.INVISIBLE);
            lvCart.setVisibility(View.VISIBLE);
        }
    }

    private void anhXa() {
        lvCart = (ListView) findViewById(R.id.listViewCart);
        txtThongBao = (TextView) findViewById(R.id.textViewThongBao);
        txtTongTien = (TextView) findViewById(R.id.textViewGiaTien);
        btnThanhToan = (Button) findViewById(R.id.buttonThanhToan);
        btnTiepTucMua = (Button) findViewById(R.id.buttonTiepTucMua);
        toolBarCart = (Toolbar) findViewById(R.id.toolBarCart);
        cartAdapter = new CartAdapter(CartActivity.this, Index.mangCart);
        lvCart.setAdapter(cartAdapter);
    }

    private void actionBar() {
        setSupportActionBar(toolBarCart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarCart.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}