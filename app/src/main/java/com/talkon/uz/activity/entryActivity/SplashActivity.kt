package com.talkon.uz.activity.entryActivity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View.*
import android.view.Window
import android.view.animation.AnimationUtils
import com.talkon.uz.R
import com.talkon.uz.activity.BaseActivity
import com.talkon.uz.activity.MainActivity
import com.talkon.uz.manager.SharedPref
import kotlinx.android.synthetic.main.activity_splash.*


/**
 * In SplashActivity, user can visit to IntroActivity or MainActivity
 */

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setTransparentStatusBar()
        initViews()
    }

    private fun initViews() {

        val handler = Handler()
        handler.postDelayed({
            if (SharedPref(this).isSaved){
                Intent(this, MainActivity::class.java).also {
                    startActivity(it)
                    finish()
                    overridePendingTransition( 0, R.anim.splash_fade_out)
                }
            }else Intent(this, IntroActivity::class.java).also {
                startActivity(it)
                finish()
                overridePendingTransition( 0, R.anim.splash_fade_out)

            }

        },2000)

        var fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fade);
        ll_talk_on.animation = fadeAnimation
    }


}