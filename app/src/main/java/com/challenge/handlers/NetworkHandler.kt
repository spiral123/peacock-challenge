package com.challenge.handlers

import com.challenge.ioc.INetwork

class NetworkHandler : INetwork {

    override fun getAnimalFromNetwork(): String {
        return "Dog"
    }

    override fun sendAnalyticsInfo(info: String) {
        // send analytics info over network
    }
}