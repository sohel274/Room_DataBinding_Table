package com.example.root.roomdatabinding.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity
data class Item(@ColumnInfo(name =  "productId")
        var productId: Int,


        @ColumnInfo(name =  "totalPrice")
        var totalPrice: Int,



        @ColumnInfo(name = "quantity")
        var quantity: Int)

{
        @PrimaryKey(autoGenerate = true)
         var itemId: Int = 0
}




