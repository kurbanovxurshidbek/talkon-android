package com.talkon.talkon.utils

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.provider.Settings
import android.util.DisplayMetrics
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.talkon.talkon.R

object Utils{
    fun getDeviceID(context: Context):String{
        val device_id: String = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        return device_id
    }

    fun datePickerDialog(){

    }

}

interface DialogListener {
    fun onCallback(isChosen: Boolean)
}