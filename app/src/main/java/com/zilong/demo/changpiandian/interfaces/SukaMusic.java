package com.zilong.demo.changpiandian.interfaces;

import com.zilong.demo.changpiandian.model.MusicLibrary;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/5/22.
 */

public interface SukaMusic {
    @POST("register")
    public void registerAccount(@Query("registerType") String registerType,@Query("phone") String phone,@Query("password") String password);

    @GET("homePage")
    Call<MusicLibrary>  gethomeData();

}
