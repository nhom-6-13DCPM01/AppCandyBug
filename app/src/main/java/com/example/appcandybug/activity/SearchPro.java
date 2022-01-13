package com.example.appcandybug.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.appcandybug.R;
import com.example.appcandybug.adapter.SearchAdapter;
import com.example.appcandybug.model.Product;
import com.example.appcandybug.my_interface.IClickItemListener;
import com.example.appcandybug.server.IMyAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPro extends AppCompatActivity {
    Toolbar toolbar_search ;
    RecyclerView recycleview_search;
    List<Product> list;
    SearchAdapter searchAdapter;
    SearchView searchView;
    ImageButton back_ic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pro);
        anhXa();
        setUpSearchview();
        checkKey();
        setBack();
        actionToolBar();
    }

    private void actionToolBar() {
        setSupportActionBar(toolbar_search);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        suKienChonItemMenu(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void suKienChonItemMenu(int id){
        if(id == R.id.menuCart){
            Intent intent = new Intent(getApplicationContext(), CartActivity.class);
            startActivity(intent);
        }
    }

    private void setBack() {
        back_ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!searchView.isIconified()){
                    searchView.setIconified(true);
                    return;
                }
                finish();
            }
        });
    }

    private void checkKey() {
        String key = getIntent().getStringExtra("KeySearch");
        if(key!=null){
            searchView.setQuery(key,true);
            searchView.setIconified(false);
        }
    }

    private void setUpSearchview() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                IMyAPI.iMyAPI.searchProduct(query,1).enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        if(response.body()!=null){
                            list= response.body();
                            searchAdapter = new SearchAdapter(getApplicationContext(), list, new IClickItemListener() {
                                @Override
                                public void onClickItemProduct(Product product) {
                                    Intent intent = new Intent(getApplicationContext(),DetailProduct.class);
                                    intent.putExtra("sanPham",product);
                                    Toast.makeText(getApplicationContext(), product.getName(), Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }
                            });
                            recycleview_search.setAdapter(searchAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {

                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void anhXa(){
        toolbar_search = findViewById(R.id.toolbar_search);
        recycleview_search = findViewById(R.id.recycleview_search);
        recycleview_search.setHasFixedSize(true);
        recycleview_search.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        searchView = findViewById(R.id.search_view);
        back_ic = findViewById(R.id.back_ic);
    }

    @Override
    public void onBackPressed() {
        if(!searchView.isIconified()){
            searchView.setIconified(true);
        }
        super.onBackPressed();
    }
}