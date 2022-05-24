package com.talkon.talkon.activity.entryActivity

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.fraggjkee.smsconfirmationview.SmsConfirmationView
import com.talkon.talkon.R
import com.talkon.talkon.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_number_verification.*

/**
 * In NumberVerificationActivity, user gets verification code if signed up/in with phone number
 */

class NumberVerificationActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_verification)
        setTransparentStatusBar()

        initViews()
    }

    private fun initViews() {


        var timer = object : CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tv_seconds.setText("" + millisUntilFinished / 1000)
            }

            override fun onFinish() {
                ll_counting.visibility = View.GONE
                ll_resend.visibility = View.VISIBLE

            }

        }
        timer.start()

        bt_verify_light.setOnClickListener {
            callStatusChooseActivity(this)
        }
    }

    private fun checkSmsCode(){
        sms_code_view.onChangeListener = SmsConfirmationView.OnChangeListener { code, isComplete ->
            if (code.equals("1111")) {
                isComplete.not()
            }
        }
    }
}