package com.example.architecturalsample.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.architecturalsample.MyApplication
import com.example.architecturalsample.common.composition.ActivityCompositionRoot
import com.example.architecturalsample.common.composition.PresentationCompositionRoot

open class BaseActivity: AppCompatActivity() {
    private val appCompositionRoot get() = (application as MyApplication).appCompositionRoot
     val activityCompositionRoot by lazy {
        ActivityCompositionRoot(this,appCompositionRoot)
    }
   protected val compositionRoot by lazy { PresentationCompositionRoot(activityCompositionRoot)}
}