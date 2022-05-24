package com.talkon.talkon.activity.entryActivity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.DatePicker
import com.talkon.talkon.R
import com.talkon.talkon.activity.BaseActivity
import com.talkon.talkon.utils.ExperienceDialog
import com.talkon.talkon.utils.LevelDialog
import kotlinx.android.synthetic.main.activity_status_choose.*
import java.util.*


/**
 * StatusChooseActivity is used for users to choose if they enter as Teacher or Student
 */
class StatusChooseActivity : BaseActivity(), DatePickerDialog.OnDateSetListener {
    var day : Int = 0
    var month : Int = 0
    var year : Int = 0
    var savedDay : Int = 0
    var savedMonth : Int = 0
    var savedYear : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_choose)
        setTransparentStatusBar()

        initViews()
    }

    private fun initViews() {
        ll_student.setOnClickListener {
            ll_info.visibility = View.VISIBLE
            ll_level.visibility = View.VISIBLE
            ll_experience.visibility = View.INVISIBLE
            ll_student.setBackgroundResource(R.drawable.background_onpressed_green)
            ll_teacher.setBackgroundResource(R.drawable.border_rounded_grey_green)
        }
        ll_teacher.setOnClickListener {
            ll_info.visibility = View.VISIBLE
            ll_level.visibility = View.INVISIBLE
            ll_experience.visibility = View.VISIBLE
            ll_teacher.setBackgroundResource(R.drawable.background_onpressed_green)
            ll_student.setBackgroundResource(R.drawable.border_rounded_grey_green)
        }

        ll_level.setOnClickListener {
            LevelDialog().show(supportFragmentManager, "MyCustomFragment")
        }
        ll_experience.setOnClickListener {
            ExperienceDialog().show(supportFragmentManager, "MyCustomFragment")
        }
        bt_next_light.setOnClickListener {
            callMainActivity(this)
        }
        pickDate()

    }

    fun getLevel(level: String){
        tv_experience.text = level
    }

    private fun pickDate() {
        ll_age_date.setOnClickListener {
            getDateTimeCalendar()
            DatePickerDialog(this, this, year, month, day).show()
        }
    }

    private fun getDateTimeCalendar() {
        val cal: Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        savedDay = day
        savedMonth = month
        savedYear = year

        getDateTimeCalendar()
        tv_age_hint.visibility = View.GONE
        tv_age_format.visibility = View.VISIBLE
        tv_age_format.text = "$savedDay/$savedMonth/$savedYear"
    }


}