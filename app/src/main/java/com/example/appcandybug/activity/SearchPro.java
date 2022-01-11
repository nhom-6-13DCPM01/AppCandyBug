package com.example.appcandybug.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pro);
        anhXa();
        setUpSearchview();
    }

    private void setUpSearchview() {
        final SearchView searchView = findViewById(R.id.search_view);
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
                                    intent.putExtra("detailProduct",product);
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
    }
}