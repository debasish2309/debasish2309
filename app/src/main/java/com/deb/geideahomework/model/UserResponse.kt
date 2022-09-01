package com.deb.geideahomework.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class UserResponse(
    val data: List<Data>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)

@Entity(tableName = "table_user")
data class Data(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="ids")
    val ids: Int = 0,
    @ColumnInfo(name = "avatar")
    val avatar: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "first_name")
    val first_name: String,
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "last_name")
    val last_name: String
)

data class Support(
    val text: String,
    val url: String
)