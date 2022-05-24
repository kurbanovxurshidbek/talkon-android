package com.talkon.talkon.activity.entryActivity

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color.BLACK
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.type.Color
import com.talkon.talkon.R
import com.talkon.talkon.activity.BaseActivity
import com.talkon.talkon.adapter.IntroFragmentAdapter
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.activity_intro.*


/**
 * IntroActivity is used to introduce the application for users when they first installed the app
 */
class IntroActivity : BaseActivity(), ViewPager.OnPageChangeListener {
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
//        ll_viewPager_background.setBackgroundColor(R.color.black);

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
        adapter = IntroFragmentAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        // set adapter
        viewPager.setAdapter(adapter)
        // set dot indicators
        dotsIndicator!!.setViewPager(viewPager)
        // make status bar transparent

        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
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


    fun backgroundTransition() {
        mColorAnimation = ValueAnimator.ofObject(ArgbEvaluator(),R.color.background_green, R.color.light_gold, R.color.light_purple,R.color.light_blue, R.color.light_grey )
        mColorAnimation.addUpdateListener { animator -> ll_viewPager_background.setBackgroundColor(animator.animatedValue as Int) }
        // (3 - 1) = number of pages minus 1
        mColorAnimation.setDuration((5 - 1) * 10000000000L);
    }

    var colors = arrayOf(R.color.background_green, R.color.light_gold, R.color.light_purple,R.color.light_blue, R.color.light_grey)

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//        if (position < adapter!!.getCount() - 1 && position < colors.size - 1) {
//            val argbEvaluator = ArgbEvaluator()
//            ll_viewPager_background.setBackgroundColor(
//                argbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1]) as Int)
//        } else {
//            ll_viewPager_background.setBackgroundColor(colors[colors.size - 1])
//        }
    }

    override fun onPageSelected(position: Int) {}

    override fun onPageScrollStateChanged(state: Int) {}

}