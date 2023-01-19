package com.example.imageloading

import android.app.Application
import com.example.imageloading.domain.di.module
import org.koin.core.context.startKoin

class App :Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(module)
        }
    }
}