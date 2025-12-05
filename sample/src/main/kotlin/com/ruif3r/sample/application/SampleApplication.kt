package com.ruif3r.sample.application

import android.app.Application
import com.ruif3r.sample.di.sampleModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class SampleApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SampleApplication)
            modules(sampleModule)
        }
    }
}