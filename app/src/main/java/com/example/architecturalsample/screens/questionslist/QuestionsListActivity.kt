package com.example.architecturalsample.screens.questionslist

import android.os.Bundle
import com.example.architecturalsample.R
import com.example.architecturalsample.screens.common.activities.BaseActivity


class QuestionsListActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_frame)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_content,QuestionsListFragment())
                .commit()
        }

    }
}