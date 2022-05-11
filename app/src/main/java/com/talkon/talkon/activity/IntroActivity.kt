package com.talkon.talkon.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.sharedpreference.manager.SharedPref
import com.talkon.talkon.R
import com.talkon.talkon.adapter.IntroFragmentAdapter
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class IntroActivity : BaseActivity() {
    private lateinit var viewPager: ViewPager
    private var context: Context = this
    private var adapter: IntroFragmentAdapter? = null
    var dotsIndicator: WormDotsIndicator? = null
    lateinit var tv_skip: TextView
    lateinit var bt_next: Button
    lateinit var iv_img: ImageView
    lateinit var iv_img_1: ImageView
    lateinit var iv_img_2: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        initViews()
    }

    private fun initViews() {
        dotsIndicator = findViewById<View>(R.id.dots_indicator) as WormDotsIndicator
        viewPager = findViewById(R.id.viewpager)
        tv_skip = findViewById(R.id.tv_skip)
        bt_next = findViewById(R.id.bt_next)
        iv_img = findViewById(R.id.iv_img)
        iv_img_1 = findViewById(R.id.iv_img_1)
        iv_img_2 = findViewById(R.id.iv_img_2)

        tv_skip.setOnClickListener {
            SharedPref(this).isSaved = true
            callMainActivity()
        }

        // init slider pager adapter
        adapter = IntroFragmentAdapter(supportFragmentManager,
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
//                if (position == adapter!!.count - 2) {
//                    iv_img.setVisibility(View.INVISIBLE)
//                    iv_img_1.setVisibility(View.VISIBLE)
//                }
                if (position == adapter!!.count - 1) {
//                    iv_img_1.setVisibility(View.INVISIBLE)
//                    iv_img_2.setVisibility(View.VISIBLE)
                    bt_next.setOnClickListener {
                        SharedPref(context).isSaved = true
                        callMainActivity()
                    }
                } else {
                    bt_next.setOnClickListener {
                        viewPager.setCurrentItem(getItem(+1), true)
                    }
                }
            }
        })
    }

    private fun getItem(i: Int): Int {
        return viewPager.getCurrentItem() + i
    }

    private fun callMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}