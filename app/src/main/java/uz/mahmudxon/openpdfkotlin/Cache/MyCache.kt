package uz.mahmudxon.openpdfkotlin.Cache

import android.content.Context
import android.content.SharedPreferences

class MyCache {

    var sharedPreferences: SharedPreferences

    companion object {
        lateinit var default : MyCache
        fun init(context: Context)
        {

                default = MyCache(context)

        }
    }

    var isSetup:Boolean
        get() = sharedPreferences.getBoolean("reg", false)
        set(value) {
            sharedPreferences.edit().putBoolean("reg", value).apply()
        }

    private constructor(context: Context)
    {
        sharedPreferences = context.getSharedPreferences("pdfviewer", Context.MODE_PRIVATE)
    }
}