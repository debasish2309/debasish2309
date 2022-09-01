package com.deb.geideahomework.userlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.deb.geideahomework.R
import com.deb.geideahomework.adapter.RecyclerViewAdapter
import com.deb.geideahomework.model.Data
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class UserListActivity : AppCompatActivity() {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        initMainViewModel()
    }

    private fun initViewModel() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@UserListActivity)

            val decoration  =  DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter =recyclerViewAdapter
        }
    }

    private fun initMainViewModel() {
        val viewModel  = ViewModelProvider(this).get(UserListViewModel::class.java)
        viewModel.getAllRepositoryList().observe(this, Observer<List<Data>>{
            recyclerViewAdapter.setListData(it)
            recyclerViewAdapter.notifyDataSetChanged()
        })
        viewModel.makeApiCall()
    }
}