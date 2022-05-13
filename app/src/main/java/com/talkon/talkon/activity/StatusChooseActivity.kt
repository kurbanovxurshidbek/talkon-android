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
            ll_teacher_et.visibility = View.VISIBLE
        }
        ll_teacher_et.setOnClickListener {
            ll_teacher_et.visibility = View.VISIBLE
        }
    }
}