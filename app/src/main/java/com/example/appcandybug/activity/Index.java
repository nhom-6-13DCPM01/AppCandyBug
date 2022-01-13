package com.example.appcandybug.activity;

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

import com.example.appcandybug.R;
import com.example.appcandybug.adapter.CategoryAdapter;
import com.example.appcandybug.adapter.ProductAdapter;
import com.example.appcandybug.model.Account;
import com.example.appcandybug.model.Cart;
import com.example.appcandybug.model.Category;
import com.example.appcandybug.model.Order;
import com.example.appcandybug.model.Product;
import com.example.appcandybug.my_interface.IClickItemListener;
import com.example.appcandybug.server.CheckConnection;
import com.example.appcandybug.server.IMyAPI;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
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
    public static Account account_login;
    TextView txt_login,txt_email_login;
    SearchView search_view_index;

    //Thuộc tính khác
    private int soLuong;
    private double tongTien;
    private Order hoaDonVuaThem = null;
    public static List<Cart> mangCart;

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
        if(mangCart != null){

        }else{
            mangCart = new ArrayList<>();
        }
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
                    productAdapter = new ProductAdapter(getApplicationContext(), listNewProduct, new IClickItemListener() {
                        @Override
                        public void onClickItemProduct(Product product) {
                            Intent intent = new Intent(getApplicationContext(), DetailProduct.class);
                            intent.putExtra("sanPham", product);
                            startActivity(intent);
                        }
                    });
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

    /*//Phần thuộc tính dialog thông tin order info
    TextView txtTotal, txtQuantity;
    NumberPicker npQuantity;
    Button btnBuy, btnCancelOrderInfo;

    private void anhXaDialogOrderInfo(Dialog dialog){
        txtQuantity = (TextView)        dialog.findViewById(R.id.textViewQuantity);
        txtTotal = (TextView)           dialog.findViewById(R.id.textViewTotal);
        npQuantity = (NumberPicker)     dialog.findViewById(R.id.numberPickerQuantity);
        btnBuy = (Button)               dialog.findViewById(R.id.buttonBuy);
        btnCancelOrderInfo = (Button)   dialog.findViewById(R.id.buttonCancelOrderInfo);

        npQuantity.setMinValue(1);
        npQuantity.setMaxValue(10000);
        npQuantity.setValue(1);
    }

    //tạo dialog create order
    private void orderInfoDialog(Product product){
        //Chỉnh sửa dialog
        Dialog dialog = new Dialog(Index.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_add_orderinfo);

        //Ánh xạ và thực hiện chức năng của dialog khi có sự kiện phù hợp
        anhXaDialogOrderInfo(dialog);
        thucHienDialogOrderinfo(dialog, product);

        //Hiển thị dialog
        dialog.show();
    }

    private void thucHienDialogOrderinfo(Dialog dialog, Product product){
        onChangeNumberPicker(product);
        btnBuy.setOnClickListener(v -> {
            addOrderInfo(product);
            dialog.dismiss();
        });
        btnCancelOrderInfo.setOnClickListener(v -> {
            dialog.dismiss();
        });
    }

    private void addOrderInfo(Product product){
        OrderInfo orderInfo = new OrderInfo(hoaDonVuaThem.getId(), product.getId(), soLuong, tongTien);
        IMyAPI.iMyAPI.addOrderInfo(orderInfo).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful())
                    Toast.makeText(Index.this, getString(R.string.notice_error_success), Toast.LENGTH_SHORT).show();
                if(response != null)
                    Toast.makeText(Index.this, response.body(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Index.this, getString(R.string.notice_error_null), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Index.this, "Fail: " + t.getCause().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onChangeNumberPicker(Product product){
        DecimalFormat format = new DecimalFormat("###,###,###");
        String total = String.valueOf(txtTotal.getText()) + " ";
        String quantity = String.valueOf(txtQuantity.getText()) + " ";
        npQuantity.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                soLuong = newVal;
                tongTien = tinhTongTien(product, newVal);
                txtQuantity.setText(quantity.toString() + newVal);
                txtTotal.setText(total.toString() + format.format(tinhTongTien(product, newVal)));
            }
        });

    }

    private double tinhTongTien(Product product, int number){
        double ketQua = 0;
        if(product.getDiscount() == 0){
            ketQua = product.getPrice() * number;
        }
        else{
            ketQua = product.getPrice() * number * product.getDiscount();
        }
        return ketQua;
    }

    private void thucHienDialogCreateOrder(Dialog dialog) {
        btnConfirmOrder.setOnClickListener(v -> {
            if (edtPhoneOrder.getText().toString().isEmpty() && edtAdressOrder.getText().toString().isEmpty())
                Toast.makeText(Index.this, getString(R.string.notice_error_blank_edit_text), Toast.LENGTH_SHORT).show();
            else{
                if(hoaDonVuaThem == null){
                    createOrder(account_login.getId());
                    dialog.dismiss();
                }
                else
                    Toast.makeText(Index.this, getString(R.string.notice_error_have_order), Toast.LENGTH_SHORT).show();
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

    //tạo dialog cancel order
    private void cancelOrderDialog(){
        //Chỉnh sửa dialog
        Dialog dialog = new Dialog(Index.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_cancel_order);

        //Ánh xạ và thực hiện chức năng của dialog khi có sự kiện phù hợp
        anhXaDiaLogCancel(dialog);
        thucHienDialogCancel(dialog);

        //Hiển thị dialog
        dialog.show();
    }

    //Các phương thức cần cho cancel order
    private void deleteOrder(){
        IMyAPI.iMyAPI.deleteOrder(hoaDonVuaThem.getId()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful())
                    Toast.makeText(Index.this, getString(R.string.notice_error_success), Toast.LENGTH_SHORT).show();
                if(response != null)
                    Toast.makeText(Index.this, response.body(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Index.this, getString(R.string.notice_error_null), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Index.this, "Fail: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void hienThiThongTinXoa(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        if(hoaDonVuaThem != null && account_login != null){
            txtName.setText(account_login.getDisplayName() + "");
            txtDateCreate.setText(format.format(hoaDonVuaThem.getDateCreate()) + "");
            txtAddress.setText(hoaDonVuaThem.getAddress() + "");
            txtPhone.setText(hoaDonVuaThem.getSDT() + "");
        }
    }

    private void thucHienDialogCancel(Dialog dialog){
        hienThiThongTinXoa();
        btnConfirmCancelOrder.setOnClickListener(v -> {
            deleteOrder();
            hoaDonVuaThem = null;
            dialog.dismiss();
        });
        btnCancelCancelOrder.setOnClickListener(v -> {
            dialog.dismiss();
        });
    }*/
}