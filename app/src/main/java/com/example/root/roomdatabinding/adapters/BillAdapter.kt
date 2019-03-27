package com.example.root.roomdatabinding.adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.root.roomdatabinding.R
import com.example.root.roomdatabinding.databinding.BillListItemBinding
import com.example.root.roomdatabinding.model.Bill


class BillAdapter(private val ctx: Context, private var payeeList: ArrayList<Bill>) : RecyclerView.Adapter<BillAdapter.ViewHolder>() {

    var listdata = payeeList
    var layoutInflater:LayoutInflater? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillAdapter.ViewHolder {

        if(layoutInflater==null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

    var binding:BillListItemBinding =  DataBindingUtil.inflate(this!!.layoutInflater!!, R.layout.bill_list_item, parent, false)
        return ViewHolder(binding)
    }

        //this method is binding the data on the list
       override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.binding.bill =  listdata[position]

        }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return listdata.size
    }

   inner class ViewHolder(var binding: BillListItemBinding) : RecyclerView.ViewHolder(binding.root) {}

}