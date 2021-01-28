package com.challenge.ioc

interface INetwork {
    fun getAnimalFromNetwork(): String

    fun sendAnalyticsInfo(info: String)
}

interface IAnalytics {
    fun recordScreen(screenName: String)

    fun recordDataRequest(request: String)
}

interface IMainViewModel {
    fun getAnimal(): String
}