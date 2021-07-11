package com.example.architecturalsample.screens.questiondetails

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.toSpanned
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.architecturalsample.R
import com.example.architecturalsample.screens.common.toolbar.MyToolbar
import com.example.architecturalsample.screens.common.viewsmvc.BaseViewMvc

class QuestionDetailViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
): BaseViewMvc<QuestionDetailViewMvc.Listener>(
    layoutInflater,parent,R.layout.activity_question_details
) {
    interface Listener {
       fun onBackClicked()
    }
    private val toolbar: MyToolbar = findViewById(R.id.toolbar)
    private val  swipeRefresh: SwipeRefreshLayout
    private val txtQuestionBody: TextView = findViewById(R.id.txt_question_body)

    init {
        // init toolbar
        toolbar.setNavigateUpListener {
            for (listener in listeners){
                listener.onBackClicked()
            }
        }

        // init pull-down-to-refresh (used as a progress indicator)
        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.isEnabled = false
    }
    fun bindQuestionBody(questionBody: String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val question = Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY)
            txtQuestionBody.text = question.toSpanned()
        } else {
            @Suppress("DEPRECATION")
            txtQuestionBody.text = Html.fromHtml(questionBody)
        }
    }

    fun showProgressIndication() {
        swipeRefresh.isRefreshing = true
    }

    fun hideProgressIndication() {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
    }


}