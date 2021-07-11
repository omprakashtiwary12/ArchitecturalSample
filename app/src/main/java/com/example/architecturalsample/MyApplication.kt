package com.example.architecturalsample

import android.app.Application
import com.example.architecturalsample.common.composition.AppCompositionRoot
import com.example.architecturalsample.networking.StackoverflowApi
import com.example.architecturalsample.questions.FetchQuestionDetailsUseCase
import com.example.architecturalsample.questions.FetchQuestionsUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {
    lateinit var appCompositionRoot: AppCompositionRoot
    override fun onCreate() {
        appCompositionRoot  = AppCompositionRoot()
        super.onCreate()
    }
}