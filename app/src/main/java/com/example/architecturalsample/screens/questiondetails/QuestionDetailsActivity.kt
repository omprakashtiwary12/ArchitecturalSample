package com.example.architecturalsample.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturalsample.questions.FetchQuestionDetailsUseCase
import com.example.architecturalsample.questions.FetchQuestionDetailsUseCase.Result.Success
import com.example.architecturalsample.screens.common.ScreensNavigator
import com.example.architecturalsample.screens.common.dialogs.DialogsNavigator
import com.example.architecturalsample.screens.common.dialogs.ServerErrorDialogFragment
import kotlinx.coroutines.*

class QuestionDetailsActivity : AppCompatActivity(), QuestionDetailViewMvc.Listener {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase
    private lateinit var questionId: String
    private lateinit var viewMvc: QuestionDetailViewMvc
    private lateinit var dialogsNavigator: DialogsNavigator
    private lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewMvc = QuestionDetailViewMvc(LayoutInflater.from(this),null)
        setContentView(viewMvc.rootView)
        fetchQuestionDetailsUseCase = FetchQuestionDetailsUseCase()

        // retrieve question ID passed from outside
        questionId = intent.extras!!.getString(EXTRA_QUESTION_ID)!!

        dialogsNavigator = DialogsNavigator(supportFragmentManager)
        screensNavigator = ScreensNavigator(this)
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
        fetchQuestionDetails()
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(this)
        coroutineScope.coroutineContext.cancelChildren()
    }


    private fun fetchQuestionDetails() {
        coroutineScope.launch {
            viewMvc.showProgressIndication()
            try {
                when(val result = fetchQuestionDetailsUseCase.fetchLatestQuestionDetail(questionId)){
                    is Success -> {
                        viewMvc.bindQuestionBody(result.question)

                    }
                    is FetchQuestionDetailsUseCase.Result.Failure ->  onFetchFailed()
                }
            } finally {
                viewMvc.hideProgressIndication()
            }

        }
    }

    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
    }

    companion object {
        const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"
        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }

    override fun onBackClicked() {
        screensNavigator.navigateBack()
    }


}