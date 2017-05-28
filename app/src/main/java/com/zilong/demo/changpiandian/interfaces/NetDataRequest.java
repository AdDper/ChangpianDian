package com.zilong.demo.changpiandian.interfaces;

import com.zilong.demo.changpiandian.model.Banner;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017/5/18.
 */

public interface NetDataRequest {
    @GET("homePhoto")
    Call<Banner> getBannerData();
}
