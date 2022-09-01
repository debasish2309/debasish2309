package com.deb.geideahomework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.deb.geideahomework.model.Data

@Database(entities = [Data::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {

    abstract fun getAppDao(): UserDAO

    companion object {
        private var DB_INSTANCE: UserDatabase? = null

        fun getAppDBInstance(context: Context): UserDatabase {
            if(DB_INSTANCE == null) {
                DB_INSTANCE =  Room.databaseBuilder(context.applicationContext, UserDatabase::class.java, "user_database")
                    .allowMainThreadQueries()
                    .build()
            }
            return DB_INSTANCE!!
        }
    }
}