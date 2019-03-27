package com.example.root.roomdatabinding.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.root.roomdatabinding.R
import com.example.root.roomdatabinding.model.Product
import java.util.*
import kotlin.collections.ArrayList

class StateSpinnerAdapter(val ctx: Context, private val list: ArrayList<Product>) : ArrayAdapter<Product>(ctx, R.layout.spinner_list_item, list) {
    private val lInflater: LayoutInflater

    init {
        lInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {


        var view = convertView

        if (view == null) {
            view = lInflater.inflate(R.layout.spinner_list_item, parent, false)
        }


        val txt = view!!.findViewById(R.id.tvSpinnerItem) as TextView
        txt.setText(list[position].productName)

               return view
    }

    override fun getView(position: Int, v: View?, viewgroup: ViewGroup): View {

        var view = v
        if (view == null) {
            view = lInflater.inflate(R.layout.spinner_list_item, viewgroup, false)
        }



        val txt = view!!.findViewById(R.id.tvSpinnerItem) as TextView
        //txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.dropdown, 0);
        txt.setText(list[position].productName)



        return view
    }

}
