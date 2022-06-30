package com.talkon.uz.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.talkon.uz.R
import com.talkon.uz.adapter.LessonHistoryAdapter
import com.talkon.uz.model.LessonHistory
import com.talkon.uz.model.Teacher

/**
 * CoursesFragment
 */

class LessonHistoryFragment : BaseFragment() {
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_courses, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.rv_lessonhistory)
        recyclerView.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))

        refreshAdapter(historyItems())
    }

    private fun refreshAdapter(items: ArrayList<LessonHistory>) {
        var adapter = LessonHistoryAdapter(this,items)
        recyclerView.adapter  =  adapter
    }


    private fun historyItems(): ArrayList<LessonHistory> {
        var teacher = Teacher(
            "https://images.unsplash.com/photo-1529626455594-4ff0802cfb7e?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1374",
            "Jeniffer Aniston",
            "",
            "",
            "",
            true,
            true
        )
        var items = ArrayList<LessonHistory>()

        items.add(LessonHistory(teacher, "12:30", "12:50", "12/05/22", ""))
        items.add(LessonHistory(teacher, "14:30", "14:50", "02/02/22", ""))
        items.add(LessonHistory(teacher, "14:30", "14:50", "02/02/22", ""))
        items.add(LessonHistory(teacher, "14:30", "14:50", "02/02/22", ""))
        items.add(LessonHistory(teacher, "14:30", "14:50", "02/02/22", ""))
        items.add(LessonHistory(teacher, "14:30", "14:50", "02/02/22", ""))
        return items
    }

}