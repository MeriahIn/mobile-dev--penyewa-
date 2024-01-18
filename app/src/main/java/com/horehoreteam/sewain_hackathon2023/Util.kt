package com.horehoreteam.sewain_hackathon2023

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import android.widget.Toast


const val JAKARTA_LAT = -6.2293796
const val JAKARTA_LON = 106.6647046
fun Int.toDp(displayMetrics: DisplayMetrics):Int{
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), displayMetrics).toInt()
}

fun showToast(context: Context, message:String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

