package com.challenge.app

import android.app.Application
import com.challenge.ioc.Container

class AnimalOnDemand : Application() {

    lateinit var container: com.challenge.ioc.Container

    override fun onCreate() {
        super.onCreate()

        this.container = com.challenge.ioc.Container()


    }
}