package com.talkon.talkon.activity.entryActivity

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.talkon.talkon.R
import com.talkon.talkon.activity.BaseActivity
import com.talkon.talkon.adapter.IntroFragmentAdapter
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.activity_intro.*


/**
 * IntroActivity is used to introduce the application for users when they first installed the app
 */
class IntroActivity : BaseActivity() {
    private lateinit var viewPager: ViewPager
    override var context: Context = this
    private var adapter: IntroFragmentAdapter? = null
    var dotsIndicator: WormDotsIndicator? = null
    lateinit var mColorAnimation: ValueAnimator
    lateinit var ll_viewPager_background: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        setTransparentStatusBar()
        initViews()
    }

    @SuppressLint("ResourceAsColor")
    private fun initViews() {
        dotsIndicator = findViewById<View>(R.id.dots_indicator) as WormDotsIndicator
        viewPager = findViewById(R.id.viewPager)
        ll_viewPager_background = findViewById(R.id.ll_viewPager_background)

        var slideAnimation = AnimationUtils.loadAnimation(this, R.anim.slide);
        ll_white_corner_rounded.animation = slideAnimation
        var fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_faster);
        viewPager.animation = fadeAnimation

        bt_get_started.setOnClickListener {
            callSignUpActivity(this)
        }

        tv_sign_in.setOnClickListener {
            callSignInActivity(this)
        }
        // init slider pager adapter
        adapter = IntroFragmentAdapter(supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        // set adapter
        viewPager.setAdapter(adapter)
        // set dot indicators
        dotsIndicator!!.setViewPager(viewPager)
        // make status bar transparent
//        backgroundTransition()
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                animateToColor(pageColors[position])

            }
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (position == adapter!!.count - 1) {
                    bt_get_started.setOnClickListener {
//                        SharedPref(context).isSaved = true
                        callSignUpActivity(this@IntroActivity)
                    }
                }
            }
        })
    }

    var pageColors = intArrayOf(Color.rgb(131,190,60), Color.YELLOW, Color.LTGRAY, Color.MAGENTA, Color.GREEN)
    var currentColor = pageColors[4]

    fun animateToColor(colorTo: Int) {
        mColorAnimation = ValueAnimator()
        if (mColorAnimation != null) {
            mColorAnimation.cancel()
        }
        mColorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), currentColor, colorTo)
        mColorAnimation.setDuration(600) // milliseconds
        mColorAnimation.addUpdateListener(AnimatorUpdateListener { animator ->
            currentColor = animator.animatedValue as Int
            ll_viewPager_background.setBackgroundColor(currentColor)
        })
        mColorAnimation.start()
    }
}