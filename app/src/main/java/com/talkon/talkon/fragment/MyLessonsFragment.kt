package com.talkon.talkon.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.talkon.talkon.R
import com.talkon.talkon.adapter.SearchTeacherFragmentAdapter
import com.talkon.talkon.adapter.TeacherLessonsAdapter
import com.talkon.talkon.model.Reservation
import com.talkon.talkon.model.Teacher
import com.talkon.talkon.model.TeacherLessons
import kotlinx.android.synthetic.main.fragment_my_lessons.*


class MyLessonsFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_my_lessons, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    private fun initViews() {
        rv_MyLessons.layoutManager = GridLayoutManager(requireContext(), 1)
        refreshAdapter(getStudent())
    }


    private fun refreshAdapter(items: ArrayList<TeacherLessons>) {
        var adapter = TeacherLessonsAdapter(items)
        rv_MyLessons.adapter = adapter
    }

    private fun getStudent(): ArrayList<TeacherLessons> {

        var teacher = Teacher(
            "https://images.unsplash.com/photo-1529626455594-4ff0802cfb7e?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1374",
            "Botir Sadullayev",
            "",
            "",
            "",
            true,
            true
        )
        var items = ArrayList<TeacherLessons>()

        items.add(TeacherLessons(teacher, "12:30", "12:50", "10/05/22"))
        items.add(TeacherLessons(teacher, "15:00", "12:50", "11/05/22"))
        items.add(TeacherLessons(teacher, "1:30", "12:50", "17/05/22"))
        items.add(TeacherLessons(teacher, "12:30", "12:50", "14/05/22"))
        items.add(TeacherLessons(teacher, "12:30", "12:50", "16/05/22"))

        return items

    }


}