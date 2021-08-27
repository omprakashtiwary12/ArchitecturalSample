package com.example.architecturalsample.common.composition

import com.example.architecturalsample.questions.FetchQuestionDetailsUseCase
import com.example.architecturalsample.questions.FetchQuestionsUseCase
import com.example.architecturalsample.screens.common.ViewMvcFactory
import com.example.architecturalsample.screens.common.dialogs.DialogsNavigator

class PresentationCompositionRoot(private val activityCompositionRoot: ActivityCompositionRoot) {
    private val layoutInflater get() = activityCompositionRoot.layoutInflater
    private val fragmentManager get() = activityCompositionRoot.fragmentManager
    private val stackoverflowApi get() = activityCompositionRoot.stackoverflowApi


    val screensNavigator get() = activityCompositionRoot.screensNavigator
    val viewMvcFactory get() = ViewMvcFactory(layoutInflater)
    val dialogsNavigator get() = DialogsNavigator(fragmentManager)
    val fetchQuestionsUseCase get() = FetchQuestionsUseCase(stackoverflowApi)
    val fetchQuestionDetailsUseCase get() = FetchQuestionDetailsUseCase(stackoverflowApi)

}