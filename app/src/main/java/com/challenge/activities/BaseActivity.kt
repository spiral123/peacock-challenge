package com.challenge.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.challenge.AnimalOnDemand
import com.challenge.ioc.Container

open class BaseActivity : AppCompatActivity() {

    lateinit var container: Container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        container = (this.applicationContext as AnimalOnDemand).container
    }
}