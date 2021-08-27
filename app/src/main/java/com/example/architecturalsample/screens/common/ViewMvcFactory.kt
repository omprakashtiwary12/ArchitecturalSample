package com.example.architecturalsample.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.architecturalsample.screens.questiondetails.QuestionDetailViewMvc
import com.example.architecturalsample.screens.questionslist.QuestionsListViewMvc

class ViewMvcFactory(private val layoutInflater: LayoutInflater) {
    fun newQuestionsListViewMvc(parent: ViewGroup?): QuestionsListViewMvc{
        return QuestionsListViewMvc(layoutInflater, parent)
    }
    fun newQuestionsDetailViewMvc(parent: ViewGroup?): QuestionDetailViewMvc {
        return QuestionDetailViewMvc(layoutInflater,parent)
    }
}