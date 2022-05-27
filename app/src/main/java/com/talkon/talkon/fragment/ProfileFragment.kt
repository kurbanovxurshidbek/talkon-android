package com.talkon.talkon.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.talkon.talkon.R
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * ProfileFragment is used for managing users profile: setting, balance, notifications...
 */

class ProfileFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        initViews(view)
        return view
    }


    private fun initViews(view: View?) {
    }

}

