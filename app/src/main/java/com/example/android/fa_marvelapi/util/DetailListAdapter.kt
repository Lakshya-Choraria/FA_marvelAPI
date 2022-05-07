package com.example.android.fa_marvelapi.util

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.fa_marvelapi.R
import com.example.android.fa_marvelapi.domain.model.Entity

class DetailListAdapter() : RecyclerView.Adapter<DetailListAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val dID : TextView = view.findViewById(R.id.detailID)
        val dCharName : TextView = view.findViewById(R.id.detailCharName)
        val dCounter : TextView = view.findViewById(R.id.detailCounter)
    }
    //class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){}
    private var userList = emptyList<Entity>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate((R.layout.counter_row),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.dID.text = currentItem.id.toString()
        holder.dCharName.text = currentItem.charactername.toString()
        holder.dCounter.text = currentItem.counter.toString()
    }

    override fun getItemCount(): Int {
        return userList.size
    }
    fun setData(entity: List<Entity>){
        this.userList = entity
        notifyDataSetChanged()
    }


}