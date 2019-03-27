package com.example.root.roomdatabinding.utility

object  RandomNumberGenerator {


    lateinit var indices: ArrayList<Int>

    public fun makeArrayList() {
        indices = ArrayList<Int>(1000)
        for (c in 0 until 1000) {
            indices.add(c)
        }

    }


    public  fun getRandomNumberWithoutRepeat(): Int {

        val arrIndex = (indices.size * Math.random()).toInt()
        val randomIndex = indices[arrIndex]
        indices.remove(arrIndex)

        return randomIndex

    }
}