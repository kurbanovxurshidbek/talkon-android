package com.talkon.uz.activity.entryActivity

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.fraggjkee.smsconfirmationview.SmsConfirmationView
import com.talkon.uz.R
import com.talkon.uz.activity.BaseActivity
import com.talkon.uz.model.Response
import com.talkon.uz.network.RetrofitHttp
import com.talkon.uz.utils.Extensions.vibrate
import com.talkon.uz.utils.Logger
import com.talkon.uz.utils.Utils
import kotlinx.android.synthetic.main.activity_number_verification.*
import retrofit2.Call
import retrofit2.Callback


/**
 * In NumberVerificationActivity, user gets verification code if signed up/in with phone number
 */

class NumberVerificationActivity : BaseActivity() {
    private var inputCode: String? = null
    private var defaultCode: String? = "6666"
    var phoneNum: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_verification)
        setTransparentStatusBar()

        initViews()
    }

    private fun initViews() {

        var textWatcher = object : TextWatcher, View.OnLayoutChangeListener,
            SmsConfirmationView.OnChangeListener {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                bn_verify.isEnabled = p0!!.length == 4
            }

            override fun afterTextChanged(p0: Editable?) {
            }

            override fun onLayoutChange(
                p0: View?,
                p1: Int,
                p2: Int,
                p3: Int,
                p4: Int,
                p5: Int,
                p6: Int,
                p7: Int,
                p8: Int,
            ) {
            }

            override fun onCodeChange(code: String, isComplete: Boolean) {
                bn_verify.isEnabled = code!!.length == 4
                inputCode = code
            }

        }

        sms_code_view.onChangeListener = (textWatcher)

        phoneNum = intent.getStringExtra("phoneNumber").toString()
        tv_number.text = phoneNum


        countTimer()

        bn_verify.setOnClickListener {
            codeCheck()
        }

    }

    private fun countTimer() {
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
    }

    private fun codeCheck() {
        if (inputCode!!.equals(defaultCode!!)) {
            apiSendCode(inputCode, phoneNum)
            callStatusChooseActivity(this)
            Utils.toast(this, "Success", true)
        } else {
            vibrate()
            Utils.toast(this, "Failed", false)
        }
    }


    private fun apiSendCode(phoneNum: String?, code: String?) {

        val map = HashMap<String, Any>()
        map["phoneNumber"] = phoneNum!!
        map["code"] = code!!


        RetrofitHttp.talkOnService.sendForToken(map).enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                Logger.d("@@@", response.body().toString())
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Logger.d("@@@", t.localizedMessage)
            }

        })

    }
}
