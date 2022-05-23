package com.talkon.talkon.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.skydoves.powerspinner.PowerSpinnerView
import com.talkon.talkon.R
import com.talkon.talkon.adapter.SearchViewPagerAdapter
import com.talkon.talkon.fragment.BaseFragment
import com.talkon.talkon.utils.Extensions.toast
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * In SearchFragment, student talks to Tutors or Students, search for tutors
 */
class SearchFragment : BaseFragment() {
    private var viewPagerAdapter: SearchViewPagerAdapter? = null
    private var viewPager2: ViewPager2? = null
    private var tabLayout: TabLayout? = null
    private lateinit var iv_search: ImageView
    private lateinit var iv_filter: ImageView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_search, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        viewPager2 = view.findViewById(R.id.viewpager2)
        tabLayout = view.findViewById(R.id.tabLayout)
        iv_filter = view.findViewById(R.id.iv_filter)
        iv_search = view.findViewById(R.id.iv_search)

        tabLayout!!.addTab(tabLayout!!.newTab().setText("Teachers"));
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Students"));
        // Set the adapter
        val fragmentManager: androidx.fragment.app.FragmentManager = childFragmentManager
        viewPagerAdapter = SearchViewPagerAdapter(fragmentManager, lifecycle = viewLifecycleOwner.lifecycle)
        viewPager2!!.setAdapter(viewPagerAdapter)

        // The Page (fragment) titles will be displayed in the
        // tabLayout hence we need to  set the page viewer
        // we use the setupWithViewPager().
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewpager2!!.setCurrentItem(tab.position)
                if(tab.position == 0){
                    iv_filter.visibility = View.VISIBLE
                    iv_search.visibility = View.VISIBLE
                }
                if(tab.position == 1){
                    iv_filter.visibility = View.INVISIBLE
                    iv_search.visibility = View.INVISIBLE
                }
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