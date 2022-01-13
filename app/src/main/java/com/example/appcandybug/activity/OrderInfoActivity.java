package com.example.appcandybug.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.appcandybug.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class OrderInfoActivity extends AppCompatActivity {
    private ViewPager2 myViewPage;
    private BottomNavigationView myBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);

        anhXa();
        thucHien();

    }

    private void anhXa(){
        myViewPage = (ViewPager2) findViewById(R.id.viewPageOrderinfo);
        myBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationOrderInfo);
    }

    private void thucHien (){

        /*myBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.shoppingCart)
                    myViewPage.setCurrentItem(0);
                else if(id == R.id.detailOrder)
                    myViewPage.setCurrentItem(1);
                else if(id == R.id.homeOrderInfo)
                    startActivity(new Intent(OrderInfoActivity.this, Index.class));
                return false;
            }
        });*/

        /*myViewPage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        myBottomNavigationView.getMenu().findItem(R.id.shoppingCart).setChecked(true);
                        break;
                    case 1:
                        myBottomNavigationView.getMenu().findItem(R.id.detailOrder).setChecked(true);
                        break;
                    case 2:
                        myBottomNavigationView.getMenu().findItem(R.id.homeOrderInfo).setChecked(true);
                        break;
                }
            }
        });*/
    }
}