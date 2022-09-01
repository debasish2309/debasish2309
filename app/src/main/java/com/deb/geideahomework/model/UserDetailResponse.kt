package com.deb.geideahomework.model

data class UserDetailResponse(
    val data: Data1,
    val support: Support1
)

data class Data1(
    val avatar: String,
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String
)

data class Support1(
    val text: String,
    val url: String
)