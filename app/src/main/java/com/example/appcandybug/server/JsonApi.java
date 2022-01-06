package com.example.appcandybug.server;

import com.example.appcandybug.model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface JsonApi {

    @POST("createOrder")
    Call<Order> createOrder(Order order);
}
