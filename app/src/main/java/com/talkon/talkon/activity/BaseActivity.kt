package com.talkon.talkon.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.talkon.talkon.activity.entryActivity.NumberVerificationActivity
import com.talkon.talkon.activity.entryActivity.SignInActivity
import com.talkon.talkon.activity.entryActivity.SignUpActivity
import com.talkon.talkon.activity.entryActivity.StatusChooseActivity

/**
 * BaseActivity is parent for all Activities
 */

open class BaseActivity : AppCompatActivity() {
    open lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this

    }

    fun callMainActivity(context: Context) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun callNumberVerificationActivity(context: Context) {
        val intent = Intent(this, NumberVerificationActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun callStatusChooseActivity(context: Context) {
        val intent = Intent(this, StatusChooseActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun callSignInActivity(context: Context) {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun callSignUpActivity(context: Context) {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }
}