package com.talkon.talkon.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.talkon.talkon.R
import com.talkon.talkon.fragment.mainFragment.CoursesFragment
import com.talkon.talkon.fragment.mainFragment.MessageFragment
import com.talkon.talkon.fragment.mainFragment.ProfileFragment
import com.talkon.talkon.fragment.mainFragment.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
/**
 * It contains view pager with 4 fragments in MainActivity
 * and pages can be controlled by BottomNavigationView
 */
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        val homeFragment = MessageFragment()
        val searchFragment = SearchFragment()
        val messagesFragment = CoursesFragment()
        val profileFragment = ProfileFragment()

        replaceFragment(searchFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_message -> replaceFragment(homeFragment)
                R.id.menu_search -> replaceFragment(searchFragment)
                R.id.menu_courses -> replaceFragment(messagesFragment)
                R.id.menu_profile -> replaceFragment(profileFragment)

            }
            true
        }

    }

    fun replaceFragment(fragment: Fragment) {
        val backStateName = fragment.javaClass.name
        val manager = supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) {
            val ft = manager.beginTransaction()
            ft.replace(R.id.fl_Fragment, fragment)
            ft.addToBackStack(backStateName)
            ft.commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1)
            finish()
        else
            super.onBackPressed()
    }
}