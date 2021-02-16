package com.example.bankproject.restService;

import com.example.bankproject.model.Customer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CustomerRepo {

    @GET("/api/customers")
    Call<List<Customer>> getAllCustomer();

    //@GET("/api/customer")



}
