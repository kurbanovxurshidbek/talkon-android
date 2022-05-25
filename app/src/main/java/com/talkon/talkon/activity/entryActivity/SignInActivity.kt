package com.talkon.talkon.activity.entryActivity

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.talkon.talkon.R
import com.talkon.talkon.activity.BaseActivity
import com.talkon.talkon.utils.CountryBottomSheetDialog
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_in.ll_country_code
import kotlinx.android.synthetic.main.activity_sign_up.*

/**
 * In SignInActivity, user can sign in using phone number or Google
 */

class SignInActivity : BaseActivity() {
    private val bottomSheet = CountryBottomSheetDialog()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        setTransparentStatusBar()

        initViews()
    }

    private fun initViews() {


        tv_sign_up.setOnClickListener {
            callSignUpActivity(this)
        }

        bt_sign_in_light.setOnClickListener {
            callNumberVerificationActivity(this)
        }


        bottomSheet.apiCountryList()

        ll_country_code.setOnClickListener {

            if (!bottomSheet.isVisible){
                bottomSheet.show(supportFragmentManager,
                    "ModalBottomSheet",)
            }


        }
    }
}