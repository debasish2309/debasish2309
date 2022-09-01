package com.deb.geideahomework.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.deb.geideahomework.repository.UserRepository
import com.deb.geideahomework.model.Data
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val userRepository: UserRepository) :ViewModel() {

    fun getAllRepositoryList(): LiveData<List<Data>> {
        return userRepository.getAllRecords()
    }

    fun makeApiCall() {
        userRepository.makeApiCall()
    }
}