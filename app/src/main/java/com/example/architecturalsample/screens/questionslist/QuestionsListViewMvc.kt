package com.example.architecturalsample.screens.questionslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.architecturalsample.R
import com.example.architecturalsample.adapters.QuestionsAdapter
import com.example.architecturalsample.questions.Question
import com.example.architecturalsample.screens.common.viewsmvc.BaseViewMvc

class QuestionsListViewMvc(layoutInflater: LayoutInflater, parent: ViewGroup?): BaseViewMvc<QuestionsListViewMvc.Listener>(layoutInflater,parent,R.layout.layout_questions_list) {

    private val swipeRefresh: SwipeRefreshLayout = findViewById(R.id.swipeRefresh)
    private val recyclerView: RecyclerView
    private val questionsAdapter: QuestionsAdapter


    init {
        // init pull-down-to-refresh
        swipeRefresh.setOnRefreshListener {
            for (listener in listeners){
                listener.onRefreshClicked()
            }
        }
        // init recycler view
        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
        questionsAdapter = QuestionsAdapter{ clickedQuestion ->
            for (listener in listeners){
                listener.onQuestionClicked(clickedQuestion)
            }
        }
        recyclerView.adapter = questionsAdapter
    }
     fun showProgressIndication() {
        swipeRefresh.isRefreshing = true
    }

    fun hideProgressIndication() {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
    }

    fun bindQuestions(questions: List<Question>) {
        questionsAdapter.bindData(questions)
    }

    interface Listener {
        fun onRefreshClicked()
        fun onQuestionClicked(clickedQuestion: Question)
    }
}
