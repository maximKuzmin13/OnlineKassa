package ru.kassi.onlinekassa

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        val builder = VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
    }

}