package com.example.architecturalsample.common.composition

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturalsample.questions.FetchQuestionDetailsUseCase
import com.example.architecturalsample.questions.FetchQuestionsUseCase
import com.example.architecturalsample.screens.common.ScreensNavigator
import com.example.architecturalsample.screens.common.dialogs.DialogsNavigator

class ActivityCompositionRoot(
    private val activity: AppCompatActivity,
    private val appCompositionRoot: AppCompositionRoot
) {
    val screensNavigator by lazy {
        ScreensNavigator(activity)
    }
    private val layoutInflater get() = LayoutInflater.from(activity)
    private val fragmentManager get() = activity.supportFragmentManager
    val dialogsNavigator get() = DialogsNavigator(fragmentManager)
    private val stackoverflowApi get() = appCompositionRoot.stackoverflowApi
    val fetchQuestionsUseCase get() = FetchQuestionsUseCase(stackoverflowApi)
    val fetchQuestionDetailsUseCase get() = FetchQuestionDetailsUseCase(stackoverflowApi)
}