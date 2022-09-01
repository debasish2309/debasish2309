package com.deb.geideahomework.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.deb.geideahomework.db.UserDAO
import com.deb.geideahomework.model.Data
import com.deb.geideahomework.model.Data1
import com.deb.geideahomework.model.UserDetailResponse
import com.deb.geideahomework.model.UserResponse
import com.deb.geideahomework.network.ServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val serviceInterface: ServiceInterface,
private val userDAO: UserDAO) {


    fun getAllRecords(): LiveData<List<Data>> {
        return userDAO.getAllRecords()
    }

    fun insertRecord(repositoryData: Data) {
        userDAO.insertRecords(repositoryData)
    }

    fun makeApiCall() {
        val call: Call<UserResponse> = serviceInterface.getDataFromAPI()
        call?.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                if(response.isSuccessful) {
                    userDAO.deleteAllRecords()

                    response.body()?.data!!.forEach {
                        insertRecord(it)
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                //
            }
        })
    }

    var userServerResponse : MutableLiveData<Data1> = MutableLiveData<Data1>()

    fun makeDetailApiCall(id: String){
        val call: Call<UserDetailResponse> = serviceInterface.getDetailFromAPI(id)
        call?.enqueue(object : Callback<UserDetailResponse>{
            override fun onResponse(
                call: Call<UserDetailResponse>,
                response: Response<UserDetailResponse>
            ) {
               userServerResponse.postValue(response.body()?.data)
            }

            override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}