package com.example.root.roomdatabinding.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.example.root.roomdatabinding.dao.BillDao
import com.example.root.roomdatabinding.dao.ItemDao
import com.example.root.roomdatabinding.dao.ProductDao
import com.example.root.roomdatabinding.model.Bill
import com.example.root.roomdatabinding.model.Item
import com.example.root.roomdatabinding.model.ItemListConverter
import com.example.root.roomdatabinding.model.Product

@Database(entities = [Product::class, Item::class, Bill::class], version = 1, exportSchema=false)
@TypeConverters(ItemListConverter::class)
abstract class AppDatabase :RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun itemDao(): ItemDao
    abstract fun billDao(): BillDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "myDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }



}