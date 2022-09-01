package com.deb.geideahomework.di

import android.app.Application
import com.deb.geideahomework.db.UserDAO
import com.deb.geideahomework.db.UserDatabase
import com.deb.geideahomework.network.ServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getAppDatabase(context: Application): UserDatabase {
        return UserDatabase.getAppDBInstance(context)
    }

    @Provides
    @Singleton
    fun getAppDao(appDatabase: UserDatabase): UserDAO {
        return appDatabase.getAppDao()
    }

    val BASE_URL = "https://reqres.in/"

    @Provides
    @Singleton
    fun getRetroServiceInstance(retrofit: Retrofit): ServiceInterface {
        return retrofit.create(ServiceInterface::class.java)
    }

    @Provides
    @Singleton
    fun getRetroInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}