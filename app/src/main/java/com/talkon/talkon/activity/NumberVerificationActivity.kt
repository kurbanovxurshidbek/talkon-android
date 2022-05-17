package com.talkon.talkon.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.talkon.talkon.R
import kotlinx.android.synthetic.main.activity_number_verification.*

class NumberVerificationActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_verification)

        initViews()
    }

    private fun initViews() {
        var timer = object: CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tv_seconds.setText(""+millisUntilFinished / 1000)
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
}