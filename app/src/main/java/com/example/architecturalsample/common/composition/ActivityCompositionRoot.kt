package com.example.architecturalsample.common.composition

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturalsample.screens.common.ScreensNavigator

class ActivityCompositionRoot(
    private val activity: AppCompatActivity,
    private val appCompositionRoot: AppCompositionRoot
) {

    /*val screensNavigator by lazy {
        ScreensNavigator(activity)
    }*/
    val screensNavigator get() = ScreensNavigator(activity)

     val layoutInflater: LayoutInflater get() = LayoutInflater.from(activity)
     val fragmentManager get() = activity.supportFragmentManager
     val stackoverflowApi get() = appCompositionRoot.stackoverflowApi



}