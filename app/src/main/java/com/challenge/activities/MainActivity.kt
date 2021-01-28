package com.challenge.activities

import android.os.Bundle
import android.widget.TextView
import com.challenge.animalondemand.R
import com.challenge.ioc.IAnalytics
import com.challenge.ioc.IMainViewModel

open class MainActivity : BaseActivity() {

    lateinit var analytics: IAnalytics
    lateinit var viewModel: IMainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        analytics = container.resolve()
        viewModel = container.resolve()

        analytics.recordScreen("MainActivity")

        val animalName = findViewById<TextView>(R.id.animal_name)
        animalName.text = viewModel.getAnimal()
    }
}