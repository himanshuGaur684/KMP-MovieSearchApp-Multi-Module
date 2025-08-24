package dev.himanshu.moviesearchapp

import android.app.Application
import dev.himanshu.moviesearchapp.di.initKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}