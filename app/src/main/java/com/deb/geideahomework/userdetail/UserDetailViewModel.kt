package com.deb.geideahomework.userdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.deb.geideahomework.model.Data1
import com.deb.geideahomework.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    fun makeDetailApiCall(id: String){
        userRepository.makeDetailApiCall(id)
    }

    fun getDetailsFromApi() : LiveData<Data1>{
        return userRepository.userServerResponse
    }
}