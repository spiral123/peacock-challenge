package com.challenge.ioc

import com.challenge.handlers.AnalyticsHandler
import com.challenge.handlers.NetworkHandler
import com.challenge.viewmodels.MainViewModel

fun Container.build(): Container {
    this.register<INetwork, NetworkHandler>()
    this.register<IAnalytics, AnalyticsHandler>()
    this.register<IMainViewModel, MainViewModel>()

    return this
}