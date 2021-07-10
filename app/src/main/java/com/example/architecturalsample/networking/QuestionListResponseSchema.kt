package com.example.architecturalsample.networking

import com.example.architecturalsample.questions.Question
import com.google.gson.annotations.SerializedName

class QuestionListResponseSchema(@SerializedName("items") val questions:List<Question>)
