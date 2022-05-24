package com.talkon.talkon.activity.entryActivity

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.talkon.talkon.R
import com.talkon.talkon.activity.BaseActivity
import com.talkon.talkon.utils.CountryBottomSheetDialog
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.tv_sign_in

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


        var sheetBehavior = BottomSheetBehavior.from(bottom_sheet)


        ll_country_code.setOnClickListener {
            if (sheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else {
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        }
    }
}