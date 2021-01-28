package com.challenge.viewmodels

import com.challenge.ioc.IMainViewModel
import com.challenge.ioc.INetwork

class MainViewModel(val networkHandler: INetwork) : IMainViewModel {

    override fun getAnimal(): String {
        return networkHandler.getAnimalFromNetwork()
    }
}