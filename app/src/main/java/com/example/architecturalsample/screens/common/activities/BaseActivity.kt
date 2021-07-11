package com.example.architecturalsample.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.architecturalsample.MyApplication
import com.example.architecturalsample.common.composition.ActivityCompositionRoot

open class BaseActivity: AppCompatActivity() {
    private val appCompositionRoot get() = (application as MyApplication).appCompositionRoot
    val compositionRoot by lazy {
        ActivityCompositionRoot(this,appCompositionRoot)
    }
}