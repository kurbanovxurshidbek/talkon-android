package com.talkon.talkon.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iv_plus.setOnClickListener {
            callEditProfileFragment()
        }
    }

    private fun callEditProfileFragment() {
        val fragmentEditProfile = EditProfileFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fl_Fragment, fragmentEditProfile, "fragmnetId")
            .commit()
    }




}

