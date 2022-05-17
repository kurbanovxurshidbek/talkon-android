package com.talkon.talkon.activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.talkon.talkon.R
import com.talkon.talkon.fragment.CountryBottomSheetDialog
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.country_bottom_sheet_layout.*


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

    private fun showBottomSheetDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.country_bottom_sheet_layout)

        dialog.show()
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
        dialog.window!!.setGravity(Gravity.BOTTOM)
    }

}