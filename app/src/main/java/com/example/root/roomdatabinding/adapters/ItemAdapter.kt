package com.example.root.roomdatabinding.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.root.roomdatabinding.R
import com.example.root.roomdatabinding.model.Item

class ItemAdapter(private val ctx: Context, private var payeeList: ArrayList<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    var listdata = payeeList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.spinner_list_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        val finalModel = listdata[position]


        holder.tvMix.text =  finalModel.productId.toString()+" - "+finalModel.quantity.toString()+" - "+finalModel.totalPrice.toString()


    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return listdata.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var tvMix: TextView = itemView.findViewById(R.id.tvSpinnerItem)

    }




}