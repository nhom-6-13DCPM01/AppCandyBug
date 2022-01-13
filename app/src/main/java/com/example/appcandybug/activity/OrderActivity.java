package com.example.appcandybug.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appcandybug.R;
import com.example.appcandybug.model.Order;
import com.example.appcandybug.model.OrderInfo;
import com.example.appcandybug.server.IMyAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {
    Button btnConfirmOrder, btnCancelOrder;
    EditText edtPhoneOrder, edtAdressOrder;
    private List<OrderInfo> orderInfoList;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        anhXa();
        eventButton();
    }

    private void eventButton() {
        btnConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thucHienCreateOrder();
                startActivity(new Intent(getApplicationContext(), Index.class));
            }
        });
        btnCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderActivity.this, CartActivity.class));
            }
        });
    }

    private void thucHienAddOrderInfo(int maHoaDon) {
        orderInfoList = new ArrayList<>();
        for(int i = 0; i < Index.mangCart.size(); i++){
            int maSanPham = Index.mangCart.get(i).getIdSP();
            int soLuong = Index.mangCart.get(i).getSoLuongSP();
            double tongTien = Index.mangCart.get(i).getGiaSP();
            orderInfoList.add(new OrderInfo(maHoaDon, maSanPham, soLuong, tongTien));
        }
        addOrderInfo(orderInfoList, maHoaDon);
    }

    private void thucHienCreateOrder() {
        createOrder(Index.maTaiKhoan);
    }

    private void anhXa(){
        btnConfirmOrder = (Button) findViewById(R.id.buttonConfirmCreateOrder);
        btnCancelOrder = (Button) findViewById(R.id.buttonCancelCreateOrder);
        edtPhoneOrder = (EditText) findViewById(R.id.editTextPhone);
        edtAdressOrder = (EditText) findViewById(R.id.editTextAddress);
    }

    private void createOrder(int idAcc){
        //Truyền dữ liệu từ view về các biến
        int phone = new Integer(edtPhoneOrder.getText().toString());
        String adress = edtAdressOrder.getText().toString();

        //Tạo đơn hàng
        Order order = new Order(idAcc, "CHƯA DUYỆT", adress, phone);

        //Đẩy dữ liệu lên web và lấy về
        IMyAPI.iMyAPI.createOrder(order).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(OrderActivity.this, getString(R.string.notice_error_success), Toast.LENGTH_SHORT).show();
                }
                if(response.body() != null){
                    Order order = response.body();
                    thucHienAddOrderInfo(order.getId());
                }
                else{
                    Toast.makeText(OrderActivity.this, getString(R.string.notice_error_null), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Toast.makeText(OrderActivity.this, "Failed: " + t.getCause().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addOrderInfo(List<OrderInfo> orderInfoList, int maHoaDon){
        IMyAPI.iMyAPI.addOrderInfo(orderInfoList).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful())
                    Toast.makeText(OrderActivity.this, getString(R.string.notice_error_success), Toast.LENGTH_SHORT).show();
                if(response != null) {
                    Toast.makeText(OrderActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                    for(int i = 0; i < Index.mangCart.size(); i++){
                        Index.mangCart.remove(i);
                    }
                }else
                    Toast.makeText(OrderActivity.this, getString(R.string.notice_error_null), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(OrderActivity.this, "Fail: " + t.getCause().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}