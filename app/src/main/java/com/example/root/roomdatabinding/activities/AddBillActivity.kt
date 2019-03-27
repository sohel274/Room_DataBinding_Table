package com.example.root.roomdatabinding.activities

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import com.example.root.roomdatabinding.R
import com.example.root.roomdatabinding.adapters.ItemAdapter
import com.example.root.roomdatabinding.adapters.StateSpinnerAdapter
import com.example.root.roomdatabinding.database.AppDatabase
import com.example.root.roomdatabinding.model.Bill
import com.example.root.roomdatabinding.model.Item
import com.example.root.roomdatabinding.model.Product
import com.example.root.roomdatabinding.utility.RandomNumberGenerator
import com.example.root.roomdatabinding.utility.Utility
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AddBillActivity : Activity() {

    lateinit var context: Context
    lateinit var spProduct: Spinner
    lateinit var db: AppDatabase
    lateinit var etBillNumber: EditText
    lateinit var etCustomerName: EditText
    lateinit var etMobile: EditText
    lateinit var productList: ArrayList<Product>
    lateinit var tvProductPrice: TextView
    var totalPrice = 0
    var pricePerItem = 0
    lateinit var etQuantity: EditText
    var currentPrice = 0
    lateinit var tvTotalPrice: TextView
    var productId = 0
    lateinit var tvAddProduct: TextView
    lateinit var itemList: ArrayList<Item>
    lateinit var itemAdapter: ItemAdapter
    lateinit var rv: RecyclerView
    lateinit var tvAddBill: TextView
    var TAG = AddBillActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_bill)
        context = this@AddBillActivity

        init()
        onItemSelectedListener()
        onTextChangeListener()


        tvAddProduct.setOnClickListener(View.OnClickListener {


            if (currentPrice == 0) {

                Utility.makeToast(context, "Please fill Quantity")

            } else {

                var item = Item(productId, totalPrice, etQuantity.text.toString().trim().toInt())
                itemList.add(item)
                itemAdapter.notifyDataSetChanged()

            }


        })


        tvAddBill.setOnClickListener(View.OnClickListener {


            var billNo = etBillNumber.text.toString().trim()
            var customerName = etCustomerName.text.trim()
            var customerNumber = etMobile.text.toString().trim()


            if (isValid()) {


                Observable.fromCallable({

                    db = AppDatabase.getAppDataBase(this)!!
                    var bill = Bill(billNo.toInt(), customerName.toString(), customerNumber.toString(), itemList)
                    db.billDao().insertItem(bill)
                    finish()


                }).doOnNext({ list ->

                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()


            }

        })


    }


    fun init() {
        //Classes
        productList = arrayListOf()
        itemList = arrayListOf()
        itemAdapter = ItemAdapter(context, itemList)


        //Editext
        etBillNumber = findViewById(R.id.etBillNumber)
        etBillNumber.setText(RandomNumberGenerator.getRandomNumberWithoutRepeat().toString())
        etCustomerName = findViewById(R.id.etCustomerName)
        etMobile = findViewById(R.id.etMobile)
        etQuantity = findViewById(R.id.etQuantity)


        //TextView
        tvProductPrice = findViewById(R.id.tvProductPrice)
        tvTotalPrice = findViewById(R.id.tvTotalPrice)
        tvAddProduct = findViewById(R.id.tvAddProduct)
        tvAddBill = findViewById(R.id.tvAddBill)


        Observable.fromCallable({
            db = AppDatabase.getAppDataBase(this)!!
            productList.addAll(db.productDao().getProducts())


        }).doOnNext({ list ->

        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()



        spProduct = findViewById(R.id.spProduct)
        var adapter: SpinnerAdapter = StateSpinnerAdapter(context, productList)
        spProduct.adapter = adapter


        rv = findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = itemAdapter


    }

    fun isValid(): Boolean {

        if (etBillNumber.text.toString().isEmpty()) {


            Utility.makeToast(context, "please enter bill number ")
            return false
        } else if (etCustomerName.text.toString().isEmpty()) {

            Utility.makeToast(context, "please enter customer name")
            return false
        } else if (etMobile.text.toString().isEmpty()) {

            Utility.makeToast(context, "please enter customer mobile")
            return false
        } else if (itemList.size == 0) {

            Utility.makeToast(context, "please enter item")
            return false

        } else {

            return true

        }

    }


    fun onItemSelectedListener() {
        spProduct.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {


                pricePerItem = productList[position].productPrice
                tvProductPrice.text = pricePerItem.toString()
                productId = productList[position].productId


                var s = etQuantity.text.toString()
                if (s.isEmpty()) {
                    currentPrice = 0
                } else {
                    currentPrice = s.toString().toInt()
                }
                totalPrice = currentPrice * pricePerItem
                tvTotalPrice.text = totalPrice.toString()


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }


    fun onTextChangeListener() {

        etQuantity.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

                if (s.isEmpty()) {
                    currentPrice = 0
                } else {
                    currentPrice = s.toString().toInt()
                }

                totalPrice = currentPrice * pricePerItem
                tvTotalPrice.text = totalPrice.toString()


            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {

            }
        })

    }
}