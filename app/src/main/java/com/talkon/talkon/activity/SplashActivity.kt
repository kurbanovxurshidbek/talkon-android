package com.talkon.talkon.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.talkon.talkon.manager.SharedPref
import com.talkon.talkon.R

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        val handler = Handler()
        handler.postDelayed({
            if (SharedPref(this).isSaved){
                Intent(this, MainActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }else Intent(this, IntroActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }, 2000)
    }
}