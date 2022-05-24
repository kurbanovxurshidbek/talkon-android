package com.talkon.talkon.activity.entryActivity

import android.os.Bundle
import android.view.View
import com.talkon.talkon.R
import com.talkon.talkon.activity.BaseActivity
import com.talkon.talkon.utils.CountryBottomSheetDialog
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.ll_country_code
import kotlinx.android.synthetic.main.activity_sign_up.tv_sign_in

/**
 * In SignInActivity, user can sign in using phone number or Google
 */

class SignInActivity : BaseActivity() {
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


        ll_country_code.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                        val bottomSheet = CountryBottomSheetDialog()
                        bottomSheet.show(
                            supportFragmentManager,
                            "ModalBottomSheet"
                        )
                    }
        })
    }
}