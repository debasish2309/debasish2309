package com.deb.geideahomework.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.deb.geideahomework.R
import com.deb.geideahomework.model.Data
import com.deb.geideahomework.userdetail.UserDetailActivity
import kotlinx.android.synthetic.main.layout_single_item.view.*

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var listData: List<Data>? = null
    fun setListData(listData: List<Data>?) {
        this.listData = listData
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.layout_single_item, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)
        holder.llView.setOnClickListener {
            val intent = Intent(holder.llView.context,UserDetailActivity::class.java)
            intent.putExtra("id", listData?.get(position)!!.id.toString())
            holder.llView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        if(listData == null )return 0
        return listData?.size!!
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        val tvid = view.user_id
        val tvname = view.user_name
        val llView = view.ll_item


        fun bind(data: Data) {
            tvid.text = data.id.toString()
            tvname.text = data.first_name + data.last_name
        }

    }

}