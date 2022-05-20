package com.talkon.talkon.activity.entryActivity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.talkon.talkon.manager.SharedPref
import com.talkon.talkon.R
import com.talkon.talkon.activity.BaseActivity
import com.talkon.talkon.activity.MainActivity

/**
 * In SplashActivity, user can visit to IntroActivity or MainActivity
 */

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        overridePendingTransition(R.anim.fade, R.anim.fade)


        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
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
//                overridePendingTransition(R.anim.main_fade_in, R.anim.splash_fade_out);
                overridePendingTransition( 0, R.anim.splash_fade_out );
            }

        }, 2000)
    }
}