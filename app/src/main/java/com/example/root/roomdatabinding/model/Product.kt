package com.example.root.roomdatabinding.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity
data class Product(@PrimaryKey(autoGenerate = true)
        val productId: Int,

        @ColumnInfo(name = "productName")
        var productName: String,


        @ColumnInfo(name = "productPrice")
        val productPrice: Int)


