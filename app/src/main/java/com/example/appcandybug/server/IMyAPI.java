package com.example.appcandybug.server;
import com.example.appcandybug.model.Account;
import com.example.appcandybug.model.Category;
import com.example.appcandybug.model.OrderInfo;
import com.example.appcandybug.model.Product;
import com.example.appcandybug.model.Order;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IMyAPI {
    IMyAPI iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);;


    @POST("api-login")
    Call<Account> login(@Body Account  account);

    @POST("api-register")
    Call<Account> register(@Body Account  account);

    @GET("api/Product")
    Call<List<Product>> getListProduct();

    @GET("api-getCategory")
    Call<ArrayList<Category>> getListCate();

    @GET("api-getNewProduct")
    Call<List<Product>> getNewProduct();

    @GET("api-getProbyCate/{idCate}")
    Call<List<Product>> getProbyCate(@Path("idCate") int idCate,@Query("page") int page);

    @GET("api-searchProduct/{key}")
    Call<List<Product>> searchProduct(@Path("key") String key,@Query("page") int page);

    //Phần của create order
    @GET("getMaxIdOrder")
    Call<Integer> getMaxIdOrder();

    @GET("getTongSoLuongOrderChuaDuyet")
    Call<Integer> getTongSoLuongOrder();

    @POST("createOrder")
    Call<Order> createOrder(@Body Order order);

    //Phần của delete hoặc cancel order
    @DELETE("api/Order/{id}")
    Call<String> deleteOrder(@Path("id") int id);

    //Phần thêm vào order info
    @POST("addOrderInfo")
    Call<String> addOrderInfo(@Body List<OrderInfo> orderInfoList);
}
