package com.example.root.roomdatabinding.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.root.roomdatabinding.model.Bill
import com.example.root.roomdatabinding.model.Item
import com.example.root.roomdatabinding.model.Product

@Dao
interface BillDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(bill:Bill)


    @Query("SELECT * FROM Bill")
    fun getBillItem(): LiveData<List<Bill>>





}