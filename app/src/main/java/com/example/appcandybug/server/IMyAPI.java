package com.example.appcandybug.server;
import com.example.appcandybug.model.Account;
import com.example.appcandybug.model.Category;
import com.example.appcandybug.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IMyAPI {
    IMyAPI iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);;


    @POST("api-login")
    Call<String> login(@Body Account  account);

    @GET("api/Product")
    Call<List<Product>> getListProduct();

    @GET("api-getCategory")
    Call<ArrayList<Category>> getListCate();
}
