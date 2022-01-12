package com.example.appcandybug.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.appcandybug.R;
import com.example.appcandybug.model.Cart;
import com.example.appcandybug.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class DetailProduct extends AppCompatActivity {
    Toolbar toolbarChiTiet;
    ImageView imgChiTiet;
    TextView txtTen, txtGia, txtMoTa;
    NumberPicker numberPicker;
    Button btnDatMua;

    private int soLuong = 0;
    private int Id = 0;
    private String Name = "";
    private String Category = "";
    private double Price = 0.0;
    private int Quantity = 0;
    private int Discount = 0;
    private String Image = "";
    private String Decription = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        anhXa();
        actionToolBar();
        getInformation();
        setValueNumberPicker();
        eventOnChangedNumberPicker();
        eventButton();
    }

    private void eventOnChangedNumberPicker() {
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                soLuong = newVal;
            }
        });
    }

    private void eventButton() {
        btnDatMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Index.mangCart.size() > 0){
                    boolean exist = false;
                    for (int i = 0; i < Index.mangCart.size(); i++){
                        if(Index.mangCart.get(i).getIdSP() == Id){
                            Index.mangCart.get(i).setSoLuongSP(Index.mangCart.get(i).getSoLuongSP() + soLuong);
                            if(Index.mangCart.get(i).getSoLuongSP() >= 10){
                                Index.mangCart.get(i).setSoLuongSP(10);
                            }
                            Index.mangCart.get(i).setGiaSP(Price * Index.mangCart.get(i).getSoLuongSP());
                            exist = true;
                        }
                    }
                    if(exist == false){
                        double giaMoi = Price * soLuong;
                        Index.mangCart.add(new Cart(Id, Name, giaMoi, Image, soLuong));
                    }
                }else{
                    double giaMoi = Price * soLuong;
                    Index.mangCart.add(new Cart(Id, Name, giaMoi, Image, soLuong));
                }
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });
    }



    private void setValueNumberPicker() {
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(100);
        numberPicker.setValue(1);
    }

    private void getInformation() {
        Product product = (Product) getIntent().getSerializableExtra("sanPham");
        Id = product.getId();
        Name = product.getName();
        Category = product.getCategory();
        Price = product.getPrice();
        Quantity = product.getQuantity();
        Discount = product.getDiscount();
        Image = product.getImage();
        Decription = product.getDescription();

        txtTen.setText(Name);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGia.setText("Giá: " + decimalFormat.format(Price) + " Đ");
        txtMoTa.setText(Decription);
        Picasso.with(getApplicationContext()).load(Image)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(imgChiTiet);
    }

    private void anhXa() {
        toolbarChiTiet = (Toolbar) findViewById(R.id.toolBarChiTietSanPham);
        imgChiTiet = (ImageView) findViewById(R.id.imageViewChiTietSanPham);
        txtTen = (TextView) findViewById(R.id.textViewTenChiTietSanPham);
        txtGia = (TextView) findViewById(R.id.textViewGiaChiTietSanPham);
        txtMoTa = (TextView) findViewById(R.id.textViewMotaChiTietSanPham);
        btnDatMua = (Button) findViewById(R.id.buttonThemGioHangChiTietSanPham);
        numberPicker = (NumberPicker) findViewById(R.id.numberPickerSoLuongChiTietSanPham);
    }

    private void actionToolBar() {
       setSupportActionBar(toolbarChiTiet);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       toolbarChiTiet.setNavigationOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });
    }

    //Tạo menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //Sự kiện này tạo ra để phục vụ cho onOptionsItemSelected
    private void suKienChonItemMenu(int id){
        if(id == R.id.menuCart){
            Intent intent = new Intent(getApplicationContext(), CartActivity.class);
            startActivity(intent);
        }
    }

    //Sư kiện chọn item menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        this.suKienChonItemMenu(item.getItemId());

        return super.onOptionsItemSelected(item);
    }

}