package com.example.kcttwarehousemobilesystem

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

/*
fun View.snackbar(message: String){
    Snackbar.make(
        this,message,Snackbar.LENGTH_LONG
    ).also{ snackbar ->
        snackbar.setAction("Ok"){
            snackbar.dismiss()
        }
    }.show()
}
*/


object Utils {
    fun getBytes(bitmap: Bitmap) :ByteArray{
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream)
        return stream.toByteArray()
    }

    fun getImage(image:ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(image, 0, image.size)
    }
}
