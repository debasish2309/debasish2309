package com.deb.geideahomework.network

import com.deb.geideahomework.model.UserDetailResponse
import com.deb.geideahomework.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceInterface {

    @GET("api/users?per_page=20")
    fun getDataFromAPI(): Call<UserResponse>

    @GET("api/users/{id}")
    fun getDetailFromAPI(@Path("id") id: String) : Call<UserDetailResponse>

}