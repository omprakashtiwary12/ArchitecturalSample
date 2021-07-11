package com.example.architecturalsample.screens.common.fragments

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.architecturalsample.MyApplication
import com.example.architecturalsample.common.composition.ActivityCompositionRoot
import com.example.architecturalsample.screens.common.activities.BaseActivity

open class BaseFragment: Fragment() {
    protected val compositionRoot get() = (requireActivity() as BaseActivity).compositionRoot
}