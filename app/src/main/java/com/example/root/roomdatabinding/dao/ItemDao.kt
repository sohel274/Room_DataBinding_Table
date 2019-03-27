package com.example.root.roomdatabinding.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.root.roomdatabinding.model.Item
import com.example.root.roomdatabinding.model.Product

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item:Item)


    @Query("SELECT * FROM Item")
    fun getItem(): List<Item>





}