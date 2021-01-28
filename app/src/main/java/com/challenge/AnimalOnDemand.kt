package com.challenge

import android.app.Application
import com.challenge.ioc.Container
import com.challenge.ioc.build

class AnimalOnDemand : Application() {

    val container = Container()

    override fun onCreate() {
        super.onCreate()

        container.build()
    }
}