package com.talkon.talkon.activity.entryActivity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import com.talkon.talkon.R
import com.talkon.talkon.activity.BaseActivity
import com.talkon.talkon.fragment.CountryBottomSheetDialog
import kotlinx.android.synthetic.main.activity_sign_up.*

/**
* In SignUpActivity, user can sign up using phone number or Google
*/
class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        initViews()
    }

    private fun initViews() {
        tv_sign_in.setOnClickListener {
            callSignInActivity(this)
        }
        bt_sign_up_light.setOnClickListener {
            callNumberVerificationActivity(this)
        }
        ll_country_code.setOnClickListener {
            val bottomSheet = CountryBottomSheetDialog()
            bottomSheet.show(supportFragmentManager,
                "ModalBottomSheet")
        }
    }

}