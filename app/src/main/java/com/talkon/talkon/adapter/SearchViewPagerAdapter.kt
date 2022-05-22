package com.talkon.talkon.adapter

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.talkon.talkon.fragment.SearchStudentFragment
import com.talkon.talkon.fragment.SearchTeacherFragment

class SearchViewPagerAdapter(
    @NonNull fragmentManager: FragmentManager?,
    @NonNull lifecycle: Lifecycle?
) :
    FragmentStateAdapter(fragmentManager!!, lifecycle!!) {
    @NonNull
    override fun createFragment(position: Int): Fragment {
        return if (position == 1) {
            SearchStudentFragment()
        } else SearchTeacherFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }
}