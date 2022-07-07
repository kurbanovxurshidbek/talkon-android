package com.talkon.uz.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.talkon.uz.R
import com.talkon.uz.adapter.CashAdapter
import com.talkon.uz.model.Cash
import com.talkon.uz.model.Teacher
import kotlinx.android.synthetic.main.fragment_cash.*
import kotlinx.android.synthetic.main.fragment_my_lessons.*


class CashFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_cash, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    private fun initViews() {
        rv_cash_fragment.layoutManager = GridLayoutManager(requireContext(), 1)
        refreshAdapter(getCash())
    }


    private fun refreshAdapter(items: ArrayList<Cash>) {
        var adapter = CashAdapter(items)
        rv_cash_fragment.adapter = adapter
    }

    private fun getCash(): ArrayList<Cash> {

        var teacher = Teacher(
            "https://images.unsplash.com/photo-1529626455594-4ff0802cfb7e?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1374",
            "Botir Sadullayev",
            "",
            "",
            "",
            true,
            true
        )
        var items = ArrayList<Cash>()

        items.add(Cash(teacher, "12:30", "12:50", "10/05/22", "23"))
        items.add(Cash(teacher, "15:00", "12:50", "11/05/22", "30"))
        items.add(Cash(teacher, "1:30", "12:50", "17/05/22", "90"))
        items.add(Cash(teacher, "12:30", "12:50", "14/05/22", "14"))
        items.add(Cash(teacher, "12:30", "12:50", "16/05/22", "10"))

        items.add(Cash(teacher, "12:30", "12:50", "10/05/22", "23"))
        items.add(Cash(teacher, "15:00", "12:50", "11/05/22", "30"))
        items.add(Cash(teacher, "1:30", "12:50", "17/05/22", "90"))
        items.add(Cash(teacher, "12:30", "12:50", "14/05/22", "14"))
        items.add(Cash(teacher, "12:30", "12:50", "16/05/22", "10"))

        items.add(Cash(teacher, "12:30", "12:50", "10/05/22", "23"))
        items.add(Cash(teacher, "15:00", "12:50", "11/05/22", "30"))
        items.add(Cash(teacher, "1:30", "12:50", "17/05/22", "90"))
        items.add(Cash(teacher, "12:30", "12:50", "14/05/22", "14"))
        items.add(Cash(teacher, "12:30", "12:50", "16/05/22", "10"))

        return items

    }
}