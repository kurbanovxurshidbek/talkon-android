package com.talkon.talkon.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.talkon.talkon.R
import com.talkon.talkon.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_teacher.*

class TeacherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher)

        initViews()
    }

    private fun initViews() {
        val teacherLessonsFragment = TeacherLessonsFragment()
        val teacherProfileFragment = TeacherProfileFragment()

        replaceFragment(teacherLessonsFragment)

        bottomTeacherNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_teacher_lessons -> replaceFragment(teacherLessonsFragment)
                R.id.menu_teacher_profile -> replaceFragment(teacherProfileFragment)
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
            ft.replace(R.id.fl_TeacherFragment, fragment)
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