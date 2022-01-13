package com.example.appcandybug.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.example.appcandybug.R;
import com.example.appcandybug.adapter.CartAdapter;
import com.example.appcandybug.server.CheckConnection;

import java.text.DecimalFormat;

public class CartActivity extends AppCompatActivity {
    ListView lvCart;
    TextView txtThongBao;
    static TextView txtTongTien;
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
        catchOnClickListView();
        eventButton();
    }

    private void eventButton() {
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnTiepTucMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Index.mangCart.size() > 0){
                    finish();
                }else{
                    CheckConnection.ShowToast_Short(getApplicationContext(), getString(R.string.empty_cart));
                }
            }
        });
    }

    private void catchOnClickListView() {
        lvCart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này");
                builder.setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(Index.mangCart.size() <= 0){
                            txtThongBao.setVisibility(View.VISIBLE);
                        }else{
                            Index.mangCart.remove(position);
                            cartAdapter.notifyDataSetChanged();
                            eventUntil();
                            if(Index.mangCart.size() <= 0){
                                txtThongBao.setVisibility(View.VISIBLE);
                            }else{
                                txtThongBao.setVisibility(View.INVISIBLE);
                                cartAdapter.notifyDataSetChanged();
                                eventUntil();
                            }
                        }
                    }
                });
                builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cartAdapter.notifyDataSetChanged();
                        eventUntil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void eventUntil() {
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