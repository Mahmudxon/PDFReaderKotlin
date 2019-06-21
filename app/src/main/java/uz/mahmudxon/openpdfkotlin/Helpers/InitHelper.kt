package uz.mahmudxon.openpdfkotlin.Helpers

import android.app.Application
import uz.mahmudxon.openpdfkotlin.Cache.MyCache

class InitHelper : Application() {
    override fun onCreate() {
        super.onCreate()
        MyCache.init(applicationContext)
    }
}