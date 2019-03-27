package com.example.root.roomdatabinding.utility

import android.content.Context
import android.widget.Toast
import java.nio.file.Files.size


object Utility {




    fun makeToast(context: Context, message:String){

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}