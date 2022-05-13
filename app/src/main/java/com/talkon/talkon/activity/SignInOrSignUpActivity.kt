package com.talkon.talkon.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.talkon.talkon.R
import kotlinx.android.synthetic.main.activity_sign_in_or_sign_up.*

class SignInOrSignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_or_sign_up)

        initViews()
    }

    private fun initViews() {
        bt_sign_up.setOnClickListener {
            callSignUpActivity(this)
        }
        bt_sign_in.setOnClickListener {
            callSignInActivity(this)
        }
    }
}