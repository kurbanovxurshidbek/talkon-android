package com.talkon.talkon.activity.entryActivity

import android.os.Bundle
import com.talkon.talkon.R
import com.talkon.talkon.activity.BaseActivity

/**
* In SignInActivity, user can sign in using phone number or Google
*/

class SignInActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }
}