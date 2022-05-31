package com.talkon.talkon.fragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
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

        log_out.setOnClickListener {
            longOut()
        }

        iv_plus.setOnClickListener {
            getFragmentEditProfile()
        }

        rate_app.setOnClickListener {
            rateApp()
        }

        profile_support_center.setOnClickListener {
            supportCenter()
        }
    }

    private fun getFragmentEditProfile() {
        val fragmentEditProfile = EditProfileFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fl_Fragment, fragmentEditProfile, "fragmnetId")
            .commit()
    }

    private fun longOut() {


    }

    private fun rateApp() {
            val uri=Uri.parse("market://details?id=com.mobile.paybek")
        val goToMarket=Intent(Intent.ACTION_VIEW,uri)
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NEW_DOCUMENT
                or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)

        try {
            startActivity(goToMarket)
        } catch (e:ActivityNotFoundException){
            startActivity(Intent(Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=com.mobile.paybek")))
        }

    }

    private fun supportCenter() {
        val uri=Uri.parse("https://www.instagram.com/mirzayev_sh_sh")
        val goToMarket=Intent(Intent.ACTION_VIEW,uri)
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NEW_DOCUMENT
                or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)

        try {
            startActivity(goToMarket)
        } catch (e:ActivityNotFoundException){
            startActivity(Intent(Intent.ACTION_VIEW,
                Uri.parse("t.me/mirzayev_sh_sh")))
        }

    }
}

