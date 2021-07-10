package com.example.architecturalsample.networking

import com.example.architecturalsample.questions.QuestionWithBody
import com.google.gson.annotations.SerializedName

data class SingleQuestionResponseSchema(@SerializedName("items")val questions:List<QuestionWithBody>) {
    val question: QuestionWithBody get() = questions[0]
}


