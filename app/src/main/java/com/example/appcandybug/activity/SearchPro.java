package com.example.appcandybug.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.appcandybug.R;
import com.example.appcandybug.server.IMyAPI;

public class SearchPro extends AppCompatActivity {
    Toolbar toolbar_search ;
    RecyclerView recycleview_search;

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
                IMyAPI.iMyAPI.searchProduct(query,1);
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
    }
}