package com.challenge.app

import android.app.Application
import com.challenge.ioc.Container

class AnimalOnDemand : Application() {

    lateinit var container: Container

    override fun onCreate() {
        super.onCreate()

        this.container = Container()

        val fred = container.resolve<IWot>()
        val john = container.resolve<IWot>()
        val dfred = container.resolve<IWot>()


    }
}

interface IWot