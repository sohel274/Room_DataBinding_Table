package com.example.root.roomdatabinding.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.root.roomdatabinding.R
import com.example.root.roomdatabinding.adapters.BillAdapter
import com.example.root.roomdatabinding.database.AppDatabase
import com.example.root.roomdatabinding.model.Bill
import com.example.root.roomdatabinding.model.Product
import com.example.root.roomdatabinding.utility.RandomNumberGenerator
import com.example.root.roomdatabinding.utility.Utility
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    lateinit var db: AppDatabase
    var TAG = MainActivity::class.java.simpleName
    lateinit var context: Context
    lateinit var tvAdd: TextView
    lateinit var rvBill: RecyclerView
    lateinit var billAdapter: BillAdapter
    lateinit var billList: ArrayList<Bill>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        RandomNumberGenerator.makeArrayList()
        db = AppDatabase.getAppDataBase(this)!!

        init()

    }


    fun init() {
        billList = arrayListOf()
        billAdapter = BillAdapter(context, billList)
        rvBill = findViewById(R.id.rvBill)
        rvBill.layoutManager = LinearLayoutManager(context)
        rvBill.adapter = billAdapter

        tvAdd = findViewById(R.id.tvAdd)
        tvAdd.setOnClickListener(View.OnClickListener {

            var i = Intent(this, AddBillActivity::class.java)
            startActivity(i)

        })


        Observable.fromCallable({

            insertProduct()


        }).doOnNext({ list ->

        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()


        db.billDao().getBillItem().observe(this, android.arch.lifecycle.Observer {

            billList.clear()
            billList.addAll(it!!)
            billAdapter.notifyDataSetChanged()


        })


    }

    fun insertProduct() {

        db.productDao().insertProduct(Product(1, "Dio 1", 20))
        db.productDao().insertProduct(Product(2, "Dio 2", 30))
        db.productDao().insertProduct(Product(3, "Dio 3", 60))


        //   Log.e(TAG, db.productDao().getProducts().size.toString())

    }

}
