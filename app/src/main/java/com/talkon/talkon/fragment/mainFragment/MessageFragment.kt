package com.talkon.talkon.fragment.mainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.talkon.talkon.R
import com.talkon.talkon.fragment.BaseFragment

/**
 * MessageFragment is used for chatting between users, like, student to teacher, or student to student
 */

class MessageFragment : BaseFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_message, container, false)
        initViews(view)
        return view
    }


    private fun initViews(view: View?) {

    }
}