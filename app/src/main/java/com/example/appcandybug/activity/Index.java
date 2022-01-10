package com.example.appcandybug.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcandybug.Other.NgayGiao;
import com.example.appcandybug.R;
import com.example.appcandybug.adapter.CategoryAdapter;
import com.example.appcandybug.adapter.ProductAdapter;
import com.example.appcandybug.model.Category;
import com.example.appcandybug.model.Order;
import com.example.appcandybug.server.CheckConnection;
import com.example.appcandybug.server.IMyAPI;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Index extends AppCompatActivity {
    Toolbar toolbar_index;
    ViewFlipper viewflipper_index;
    RecyclerView recyclerview_index;
    NavigationView navigationview_index;
    ListView listview_index;
    DrawerLayout drawerlayout_index;
    ArrayList<Category> listCate;
    List<com.example.appcandybug.model.Product> listNewProduct;
    CategoryAdapter categoryAdapter;
    ProductAdapter productAdapter;

    //Phần thuộc tính của thịnh
    Button btnConfirmOrder, btnCancelOrder;
    EditText edtPhoneOrder, edtAdressOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        anhXa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            actionBar();
            actionViewFliper();
            getListCate();
            getNewProduct();
            catchOnitemListView();
        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Hãy kết nối mạng");
            finish();
        }


    }

    private void catchOnitemListView() {
        listview_index.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(Index.this, ProductActivity.class);
                            intent.putExtra("idCategory",listCate.get(position).getId());
                            intent.putExtra("nameCate",listCate.get(position).getName());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Vui lòng kiểm tra kết nối");
                        }
                        drawerlayout_index.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(Index.this, ProductActivity.class);
                            intent.putExtra("idCategory",listCate.get(position).getId());
                            intent.putExtra("nameCate",listCate.get(position).getName());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Vui lòng kiểm tra kết nối");
                        }
                        drawerlayout_index.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(Index.this, ProductActivity.class);
                            intent.putExtra("idCategory",listCate.get(position).getId());
                            intent.putExtra("nameCate",listCate.get(position).getName());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Vui lòng kiểm tra kết nối");
                        }
                        drawerlayout_index.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(Index.this, ProductActivity.class);
                            intent.putExtra("idCategory",listCate.get(position).getId());
                            intent.putExtra("nameCate",listCate.get(position).getName());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Vui lòng kiểm tra kết nối");
                        }
                        drawerlayout_index.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(Index.this, ProductActivity.class);
                            intent.putExtra("idCategory",listCate.get(position).getId());
                            intent.putExtra("nameCate",listCate.get(position).getName());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Vui lòng kiểm tra kết nối");
                        }
                        drawerlayout_index.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void anhXa(){
        toolbar_index = (Toolbar) findViewById(R.id.toolbar_index);
        viewflipper_index = (ViewFlipper) findViewById(R.id.viewlipper_index);
        recyclerview_index = (RecyclerView) findViewById(R.id.recycleview_index);
        navigationview_index = (NavigationView) findViewById(R.id.navigationview_index);
        listview_index = (ListView) findViewById(R.id.listview_index);
        drawerlayout_index = (DrawerLayout) findViewById(R.id.drawerlayout_index);
        recyclerview_index.setAdapter(productAdapter);
        recyclerview_index.setHasFixedSize(true);
        recyclerview_index.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

    }

    //Phần ánh xạ của thịnh
    private void anhXaDialog(Dialog dialog){
        btnConfirmOrder = (Button) dialog.findViewById(R.id.buttonConfirm);
        btnCancelOrder = (Button) dialog.findViewById(R.id.buttonCancel);
        edtPhoneOrder = (EditText) dialog.findViewById(R.id.editTextPhone);
        edtAdressOrder = (EditText) dialog.findViewById(R.id.editTextAdress);
    }

    private void actionBar(){
        setSupportActionBar(toolbar_index);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_index.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar_index.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerlayout_index.openDrawer(GravityCompat.START);
            }
        });
    }

    private void actionViewFliper(){
        ArrayList<String> listQuangCao = new ArrayList<>();
        listQuangCao.add("http://ushop.somee.com/Content/Client/img/KitKat-hero-banner.png");
        listQuangCao.add("http://ushop.somee.com/Content/Client/img/chupachups-banner.png");
        listQuangCao.add("http://ushop.somee.com/Content/Client/img/laysbanner.png");
        listQuangCao.add("http://ushop.somee.com/Content/Client/img/chrismas-banner.png");
        listQuangCao.add("http://ushop.somee.com/Content/Client/img/brand-lays.png");
        listQuangCao.add("http://ushop.somee.com/Content/Client/img/brand-mm.png");
        listQuangCao.add("http://ushop.somee.com/Content/Client/img/brand-oreo.png");
        for(int i = 0; i< listQuangCao.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(listQuangCao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewflipper_index.addView(imageView);
        }
        viewflipper_index.setFlipInterval(5000);
        viewflipper_index.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewflipper_index.setInAnimation(animation_slide_in);
        viewflipper_index.setOutAnimation(animation_slide_out);
    }

    public void getListCate(){
        List<String> listIcon = new ArrayList<>();
        listIcon.add("http://ushop.somee.com/Content/Client/img/catecandy.png");
        listIcon.add("http://ushop.somee.com/Content/Client/img/catesnack.png");
        listIcon.add("http://ushop.somee.com/Content/Client/img/catemilk.png");
        listIcon.add("http://ushop.somee.com/Content/Client/img/catecookie.png");
        listIcon.add("http://ushop.somee.com/Content/Client/img/catecake.png");
        IMyAPI.iMyAPI.getListCate().enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                listCate = response.body();
                if(listCate != null)
                for(int i = 0 ;i < listCate.size();i++){
                    listCate.get(i).setIcon(listIcon.get(i));
                }
                categoryAdapter = new CategoryAdapter(listCate,R.layout.row_list_category,getApplicationContext());
                listview_index.setAdapter(categoryAdapter);
            }
            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
            }
        });
    }


    public void getNewProduct(){
        IMyAPI.iMyAPI.getNewProduct().enqueue(new Callback<List<com.example.appcandybug.model.Product>>() {
            @Override
            public void onResponse(Call<List<com.example.appcandybug.model.Product>> call, Response<List<com.example.appcandybug.model.Product>> response) {
                if(response.body()!=null){
                    listNewProduct = response.body();
                    productAdapter = new ProductAdapter(getApplicationContext(),listNewProduct);
                    recyclerview_index.setAdapter(productAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<com.example.appcandybug.model.Product>> call, Throwable t) {
                Toast.makeText(Index.this, "in failure", Toast.LENGTH_SHORT).show();
                Log.d("onFailure: ",t.getMessage());
            }
        });
    }


    //Phần phương thức của thịnh
    private void orderDialog(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_order);

        anhXaDialog(dialog);
        thucHienDialog(dialog);

        dialog.show();
    }

    private void thucHienDialog(Dialog dialog) {
        btnConfirmOrder.setOnClickListener(v -> {
            if (edtPhoneOrder.getText().toString().isEmpty() && edtAdressOrder.getText().toString().isEmpty())
                Toast.makeText(Index.this, "Xin bạn hãy nhập thông tin", Toast.LENGTH_SHORT).show();
            else {
                int phone = new Integer(edtPhoneOrder.getText().toString());
                String adress = edtAdressOrder.getText().toString();
                Date ngayTao = new Date();
                NgayGiao ngayGiao = new NgayGiao(ngayTao, 3, 8, 22, 100, 30);

                Order order = new Order(1, ngayTao, "CHƯA DUYỆT", adress, ngayGiao.tinhNgayGiaoHang(1), phone);

            }
        });

        btnCancelOrder.setOnClickListener(v -> {
            dialog.dismiss();
        });
    }
}