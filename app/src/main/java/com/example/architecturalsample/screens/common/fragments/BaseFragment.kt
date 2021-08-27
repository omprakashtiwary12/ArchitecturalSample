package com.example.architecturalsample.screens.common.fragments

import androidx.fragment.app.Fragment
import com.example.architecturalsample.common.composition.PresentationCompositionRoot
import com.example.architecturalsample.screens.common.activities.BaseActivity

open class BaseFragment: Fragment() {
    protected val compositionRoot by lazy {
        PresentationCompositionRoot((requireActivity() as BaseActivity).activityCompositionRoot)
    }
}