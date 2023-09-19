package com.dinesh.dineshwayaman.Services;

import com.dinesh.dineshwayaman.Models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiServices {
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("cba/")
    Call<LoginResponse> login(@Field("name") String name, @Field("password") String password);

}
