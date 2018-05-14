package com.aliumujib.githubtrending.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.aliumujib.githubtrending.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mainNavigator = MainNavigator(this)
        mainNavigator.goToList()
    }


}
