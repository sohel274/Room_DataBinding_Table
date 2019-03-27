package com.example.root.roomdatabinding.model

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ItemListConverter {

    var gson = Gson()

    @TypeConverter
    fun fromTimestamp(data: String?): ArrayList<Item>? {

        if (data == null){
            return ArrayList()
        }
        val listType = object : TypeToken<ArrayList<Item>>() {}.type
        return gson.fromJson(data, listType)


    }

    @TypeConverter
    fun someObjectListToString(someObjects: ArrayList<Item>?): String? {
        return gson.toJson(someObjects)
    }



}