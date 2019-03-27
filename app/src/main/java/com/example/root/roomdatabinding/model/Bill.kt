package com.example.root.roomdatabinding.model

import android.arch.persistence.room.*


@Entity
data class Bill(

        @ColumnInfo(name = "billNo")
        var billNo: Int,


        @ColumnInfo(name = "customerName")
        var customerName: String,


        @ColumnInfo(name = "customerNo")
        var customerNo: String,

        @ColumnInfo(name = "itemList")
        @TypeConverters(ItemListConverter::class)
        var itemList: ArrayList<Item>


) {
    @PrimaryKey(autoGenerate = true)
    var billId: Int = 0


}




