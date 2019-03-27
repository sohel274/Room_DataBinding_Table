package com.example.root.roomdatabinding.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.root.roomdatabinding.model.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(product:Product)


    @Query("SELECT * FROM Product")
    fun getProducts(): List<Product>





}