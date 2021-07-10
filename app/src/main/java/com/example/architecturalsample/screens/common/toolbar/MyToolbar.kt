package com.example.architecturalsample.screens.common.toolbar

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import com.example.architecturalsample.R


class MyToolbar : Toolbar {
    private var centeredTitleTextView: TextView? = null
    interface NavigateUpListener {
        fun onNavigationUpClicked()
    }
    private var navigateUpListener: () -> Unit = {}

    private lateinit var navigateUp: FrameLayout

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    override fun setTitle(@StringRes resId: Int) {
        val s = resources.getString(resId)
        title = s
    }
    override fun setTitle(title: CharSequence?) {
        getCenteredTitleTextView().setText(title)
    }

    private fun getCenteredTitleTextView(): TextView {
        if (centeredTitleTextView == null){
            centeredTitleTextView = TextView(context)
            centeredTitleTextView!!.gravity = Gravity.CENTER
            val layoutParams = Toolbar.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
            layoutParams.gravity = Gravity.CENTER
            centeredTitleTextView!!.layoutParams = layoutParams
            addView(centeredTitleTextView)
        }
        return centeredTitleTextView as TextView
    }


    override fun getTitle(): CharSequence? {
        return getCenteredTitleTextView().getText().toString()
    }
    fun setTypeface(font: Typeface?) {
        getCenteredTitleTextView().setTypeface(font)
    }

    private fun init(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_my_toolbar, this, true)
        setContentInsetsRelative(0, 0)
        navigateUp = view.findViewById(R.id.navigate_up)
        navigateUp.setOnClickListener { navigateUpListener.invoke() }
    }

    fun setNavigateUpListener(navigateUpListener: () -> Unit) {
        this.navigateUpListener = navigateUpListener
        navigateUp.visibility = View.VISIBLE
    }
}