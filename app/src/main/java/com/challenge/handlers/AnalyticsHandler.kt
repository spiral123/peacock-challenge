package com.challenge.handlers

import com.challenge.ioc.IAnalytics
import com.challenge.ioc.INetwork

class AnalyticsHandler(val networkHandler: INetwork) : IAnalytics {

    override fun recordScreen(screenName: String) {
        networkHandler.sendAnalyticsInfo("Screen: $screenName")
    }

    override fun recordDataRequest(request: String) {
        networkHandler.sendAnalyticsInfo("GetNetworkData: $request")
    }
}