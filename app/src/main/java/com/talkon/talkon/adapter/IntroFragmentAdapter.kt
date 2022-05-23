package com.talkon.talkon.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.talkon.talkon.fragment.IntroFragment

/**
 * IntroFragmentAdapter is used for viewPager in IntroActivity
 */

class IntroFragmentAdapter(fm: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        return IntroFragment.newInstance(position)
    }

    // size is hardcoded
    override fun getCount(): Int {
        return 4
    }
}