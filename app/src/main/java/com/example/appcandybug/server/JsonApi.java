package com.example.appcandybug.server;

import com.example.appcandybug.model.Order;

import java.util.List;

import retrofit2.Call;

public interface JsonApi {


    Call<List<Order>> getPost();
}
