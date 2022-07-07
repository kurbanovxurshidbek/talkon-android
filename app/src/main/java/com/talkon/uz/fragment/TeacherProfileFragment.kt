package com.talkon.uz.fragment

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.talkon.uz.R
import com.talkon.uz.activity.BalanceActivity
import com.talkon.uz.activity.entryActivity.LogInActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.iv_plus
import kotlinx.android.synthetic.main.fragment_profile.log_out
import kotlinx.android.synthetic.main.fragment_profile.profile_support_center
import kotlinx.android.synthetic.main.fragment_profile.rate_app
import kotlinx.android.synthetic.main.fragment_teacher_profile.*


class TeacherProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teacher_profile, container, false)
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

       teacher_money.setOnClickListener {
           goCashFragment()
       }



    }

    private fun getFragmentEditProfile() {
        val fragmentEditProfile = EditProfileFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fl_Fragment, fragmentEditProfile, "fragmnetId")
            .commit()
    }

    private fun longOut() {

        val dialogClickListener =
            DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        val intent= Intent(requireContext(), LogInActivity::class.java)
                        startActivity(intent)
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {

                    }
                }
            }

        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
            .setNegativeButton("No", dialogClickListener).show()


    }

    private fun goCashFragment() {
      val intent=Intent(requireContext(),CashFragment::class.java)
        startActivity(intent)
    }



    private fun rateApp() {
        val uri = Uri.parse("market://details?id=com.mobile.paybek")
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        goToMarket.addFlags(
            Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NEW_DOCUMENT
                    or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        )

        try {
            startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=com.mobile.paybek")
                )
            )
        }

    }

    private fun supportCenter() {
        val uri = Uri.parse("https://www.instagram.com/mirzayev_sh_sh")
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        goToMarket.addFlags(
            Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NEW_DOCUMENT
                    or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        )

        try {
            startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("t.me/mirzayev_sh_sh")
                )
            )
        }

    }
}