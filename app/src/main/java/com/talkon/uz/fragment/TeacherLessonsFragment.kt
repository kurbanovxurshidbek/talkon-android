package com.talkon.uz.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.talkon.uz.R
import com.talkon.uz.adapter.TeacherLessonsViewPagerAdapter


class TeacherLessonsFragment : Fragment() {

    private var viewPagerAdapter: TeacherLessonsViewPagerAdapter? = null
    private var viewPager2: ViewPager2? = null
    private var tabLayout: TabLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_teacher_lessons, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        viewPager2 = view.findViewById(R.id.viewpager2_teacher_lessons)
        tabLayout = view.findViewById(R.id.tabLayout_teacher_lessons)

        viewPagerSetUp()
    }

    private fun viewPagerSetUp() {

        tabLayout!!.addTab(tabLayout!!.newTab().setText("My Lessons"));
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Cash"));

        // Set the adapter
        val fragmentManager: androidx.fragment.app.FragmentManager = childFragmentManager
        viewPagerAdapter = TeacherLessonsViewPagerAdapter(fragmentManager, lifecycle = viewLifecycleOwner.lifecycle)
        viewPager2!!.adapter = viewPagerAdapter
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager2!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        viewPager2!!.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout!!.selectTab(tabLayout!!.getTabAt(position))
            }
        })
    }

}