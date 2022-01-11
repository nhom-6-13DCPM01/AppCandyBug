package com.example.appcandybug.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcandybug.Other.NgayGiao;
import com.example.appcandybug.R;
import com.example.appcandybug.adapter.CategoryAdapter;
import com.example.appcandybug.adapter.ProductAdapter;
import com.example.appcandybug.model.Account;
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
    LinearLayout layout_logout;
    Account account_login;
    TextView txt_login,txt_email_login;
    SearchView search_view_index;

    //Thuộc tính cần cho phần tự động đặt ngày giao
    private int soLuongDonTon, maHoaDonNgayDat;
    private Order hoaDonVuaThem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        anhXa();
        loadData();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            actionBar();
            actionViewFliper();
            getListCate();
            getNewProduct();
            catchOnitemListView();
            search();
            logout();
        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Hãy kết nối mạng");
            finish();
        }
    }

    private void search() {
        search_view_index.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(Index.this,SearchPro.class);
                intent.putExtra("KeySearch",query);
                search_view_index.setQuery("", false);
                search_view_index.setIconified(true);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(!search_view_index.isIconified()){
            search_view_index.setIconified(true);
        }
        super.onBackPressed();
    }

    private void loadData() {
        account_login = getIntent().getParcelableExtra("Account");
        if(account_login!=null)
        {
            txt_login.setText(account_login.getUsername());
            txt_email_login.setText(account_login.getEmail());
        }
    }

    private void logout() {
        layout_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        layout_logout = findViewById(R.id.linear_logout);
        txt_login = findViewById(R.id.txt_login);
        txt_email_login = findViewById(R.id.txt_email_login);
        search_view_index = findViewById(R.id.search_view_index);
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

    //Phần phương thức của create order
    //Tạo menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //Sự kiện này tạo ra để phục vụ cho onOptionsItemSelected
    private void suKienChonItemMenu(int id){
        if(hoaDonVuaThem == null){
            if(id == R.id.menuCreateOrder){
                createOrderDialog();
            }
            if(id == R.id.menuCancelOrder){
                cancelOrderDialog();
            }
        }
    }

    //Sư kiện chọn item menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        this.suKienChonItemMenu(item.getItemId());

        return super.onOptionsItemSelected(item);
    }

    //Phần thuộc tính của create order
    //View
    Button btnConfirmOrder, btnCancelOrder;
    EditText edtPhoneOrder, edtAdressOrder;

    //Phần ánh xạ của create order
    private void anhXaDialog(Dialog dialog){
        btnConfirmOrder = (Button) dialog.findViewById(R.id.buttonConfirmCreateOrder);
        btnCancelOrder = (Button) dialog.findViewById(R.id.buttonCancelCreateOrder);
        edtPhoneOrder = (EditText) dialog.findViewById(R.id.editTextPhone);
        edtAdressOrder = (EditText) dialog.findViewById(R.id.editTextAddress);
    }

    //tạo dialog create order
    private void createOrderDialog(){
        //Chỉnh sửa dialog
        Dialog dialog = new Dialog(Index.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_order);

        //Ánh xạ và thực hiện chức năng của dialog khi có sự kiện phù hợp
        anhXaDialog(dialog);
        thucHienDialog(dialog);

        //Hiển thị dialog
        dialog.show();
    }

    //tạo dialog cancel order
    private void cancelOrderDialog(){
        //Chỉnh sửa dialog
        Dialog dialog = new Dialog(Index.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_cancel_order);

        //Ánh xạ và thực hiện chức năng của dialog khi có sự kiện phù hợp
        anhXaDiaLogCancel(dialog);
        thucHienDialog(dialog);

        //Hiển thị dialog
        dialog.show();
    }

    //Các phương thức cần để thực hiện create order
    //Lấy mã hóa đơn lớn nhất
    private void getMaHoaDonLonNhat(){
        //Lấy dữ liệu trên web về
        IMyAPI.iMyAPI.getMaxIdOrder().enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(!response.isSuccessful())
                    Toast.makeText(Index.this, "Somethinh went wrong in layHoaDon at isSuccess", Toast.LENGTH_SHORT).show();
                else if(response.body() != null)
                    maHoaDonNgayDat = response.body();
                else
                    Toast.makeText(Index.this, "Somethinh went wrong in layHoaDon at respone null", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(Index.this, "Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //get tổng số lượng đơn chưa duyệt
    private void getTongSoOrderChuaDuyet(){
        //Lấy dữ liệu trên web về
        IMyAPI.iMyAPI.getTongSoLuongOrder().enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(!response.isSuccessful())
                    Toast.makeText(Index.this, "Somethinh went wrong in getSoLuongOrder at isSuccess", Toast.LENGTH_SHORT).show();
                else if(response.body() != null)
                    soLuongDonTon = response.body();
                else
                    Toast.makeText(Index.this, "Somethinh went wrong in layHoaDon at respone null", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(Index.this, "Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //tạo hóa đơn
    private void createOrder(int idAcc, int ngayDuKien, int gioBatDauGiao, int gioKetThucGiao, int maHoaDon, int soLuongNhanVien){
        //Truyền dữ liệu từ view về các biến
        int phone = new Integer(edtPhoneOrder.getText().toString());
        String adress = edtAdressOrder.getText().toString();

        //Tạo ngày tạo
        Date ngayTao = new Date();
        //Tạo đơn hàng
        Order order = new Order(idAcc, ngayTao, "CHƯA DUYỆT", adress, null, phone);

        //Đẩy dữ liệu lên web và lấy về
        IMyAPI.iMyAPI.createOrder(order).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if(!response.isSuccessful())
                    Toast.makeText(Index.this, "Something went wrong in createOrder at isSuccess", Toast.LENGTH_SHORT).show();
                else if(response.body() != null){
                    Order order = response.body();
                    hoaDonVuaThem = order;
                }
                else
                    Toast.makeText(Index.this, "Something went wrong in createOrder at respone null", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Toast.makeText(Index.this, "Failed: " + t.getCause().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void thucHienDialog(Dialog dialog) {
        btnConfirmOrder.setOnClickListener(v -> {
            if (edtPhoneOrder.getText().toString().isEmpty() && edtAdressOrder.getText().toString().isEmpty())
                Toast.makeText(Index.this, "Xin bạn hãy nhập thông tin", Toast.LENGTH_SHORT).show();
            else{
                if(hoaDonVuaThem == null){
                    createOrder(account_login.getId(), 5, 8, 22, maHoaDonNgayDat + 1, 30);
                    dialog.dismiss();
                }
                else
                    Toast.makeText(Index.this, "Bạn đã có đơn hàng rồi. Hãy cứ mua sắm đi!", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancelOrder.setOnClickListener(v -> {
            dialog.dismiss();
        });
    }

    //Phần thuộc tính của cancel order
    //View
    Button btnConfirmCancelOrder, btnCancelCancelOrder;
    TextView txtName, txtDateCreate, txtPhone, txtAddress;

    //Phần ánh xạ
    private void anhXaDiaLogCancel(Dialog dialog){
        btnConfirmCancelOrder = (Button) dialog.findViewById(R.id.buttonConfirmCancel);
        btnCancelCancelOrder = (Button) dialog.findViewById(R.id.buttonCancelCancel);
        txtName = (TextView) dialog.findViewById(R.id.textViewName);
        txtDateCreate = (TextView) dialog.findViewById(R.id.textViewDateCreate);
        txtPhone = (TextView) dialog.findViewById(R.id.textViewPhoneNumber);
        txtAddress = (TextView) dialog.findViewById(R.id.textViewAddress);
    }

    //Các phương thức cần cho cancel order
    private void deleteOrder(){
        IMyAPI.iMyAPI.deleteOrder(hoaDonVuaThem.getId()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful())
                    Toast.makeText(Index.this, "something went wrong at success", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Index.this, response.body(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Index.this, "Fail: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void thucHienCancelDialog(Dialog dialog){
        btnConfirmCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteOrder();
                dialog.cancel();
            }
        });
        btnCancelCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }
}