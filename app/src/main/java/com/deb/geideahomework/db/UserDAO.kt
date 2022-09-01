package com.deb.geideahomework.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deb.geideahomework.model.Data

@Dao
interface UserDAO {

    @Query("SELECT * FROM table_user")
    fun getAllRecords(): LiveData<List<Data>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecords(data: Data)

    @Query("DELETE FROM table_user")
    fun deleteAllRecords()
}