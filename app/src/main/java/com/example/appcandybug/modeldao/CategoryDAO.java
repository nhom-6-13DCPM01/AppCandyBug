package com.example.appcandybug.modeldao;


import com.example.appcandybug.model.Category;
import com.example.appcandybug.server.IMyAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryDAO {
    private static CategoryDAO instance;
    ArrayList<Category> listCate;
    public static CategoryDAO getInstance() {
        if(instance == null){
            instance = new CategoryDAO();
        }
        return instance;
    }

    public ArrayList<Category> getListCate(){
        listCate = new ArrayList<>();
        List<String> listIcon = new ArrayList<>();
        listIcon.add("http://ushop.somee.com/Content/Client/img/catecandy.png");
        listIcon.add("http://ushop.somee.com/Content/Client/img/catesnack.png");
        listIcon.add("http://ushop.somee.com/Content/Client/img/catesmilk.png");
        listIcon.add("http://ushop.somee.com/Content/Client/img/cateccookie.png");
        listIcon.add("http://ushop.somee.com/Content/Client/img/catecake.png");
        IMyAPI.iMyAPI.getListCate().enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                listCate = response.body();
                for(int i = 0 ;i < listCate.size();i++){
                    listCate.get(i).setIcon(listIcon.get(i));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {

            }
        });

       return listCate;
    }
}
