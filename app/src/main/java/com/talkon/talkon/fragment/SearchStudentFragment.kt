package com.talkon.talkon.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.talkon.talkon.R
import com.talkon.talkon.fragment.BaseFragment

/**
 * In StudentFragment, student can talk with student, by random match
 */
class SearchStudentFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_student, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {


    }
}
