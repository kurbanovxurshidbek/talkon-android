package com.talkon.uz.adapter

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.talkon.uz.fragment.CashFragment
import com.talkon.uz.fragment.MyLessonsFragment
import com.talkon.uz.fragment.SearchStudentFragment
import com.talkon.uz.fragment.SearchTeacherFragment

class TeacherLessonsViewPagerAdapter(
    @NonNull fragmentManager: FragmentManager?,
    @NonNull lifecycle: Lifecycle?
) :
    FragmentStateAdapter(fragmentManager!!, lifecycle!!) {
    @NonNull
    override fun createFragment(position: Int): Fragment {
        return if (position == 1) {
            CashFragment()
        } else MyLessonsFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }
}