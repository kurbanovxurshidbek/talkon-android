package com.talkon.talkon.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.talkon.talkon.R
import kotlinx.android.synthetic.main.activity_status_choose.*

class StatusChooseActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_choose)

        initViews()
    }

    private fun initViews() {
        ll_student.setOnClickListener {
            ll_student_et.visibility = View.VISIBLE
            ll_teacher_et.visibility = View.INVISIBLE
        }
        ll_teacher.setOnClickListener {
            ll_teacher_et.visibility = View.VISIBLE
            ll_student_et.visibility = View.INVISIBLE
        }

        bt_next_light.setOnClickListener {
            callMainActivity(this)
        }
    }
}