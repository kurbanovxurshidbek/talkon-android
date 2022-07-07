package com.talkon.uz.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.talkon.uz.R
import com.talkon.uz.adapter.ReservationAdapter
import com.talkon.uz.model.LessonHistory
import com.talkon.uz.model.Reservation
import com.talkon.uz.model.Teacher
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_reservation.*


class ReservationFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reservation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    fun initViews(view: View?){
        recyclerView_reservation.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        refreshAdapter(reservationItems())
    }

    private fun refreshAdapter(items: ArrayList<Reservation>) {
        var adapter = ReservationAdapter(this,items)
        recyclerView_reservation.adapter  =  adapter
    }

    private fun reservationItems(): ArrayList<Reservation> {
        var teacher = Teacher(
            "https://images.unsplash.com/photo-1529626455594-4ff0802cfb7e?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1374",
            "Botir Sadullayev",
            "",
            "",
            "",
            true,
            true
        )
        var items = ArrayList<Reservation>()

        items.add(Reservation(teacher, "12:30", "12:50", "10/05/22"))
        items.add(Reservation(teacher, "15:00", "12:50", "11/05/22"))
        items.add(Reservation(teacher, "1:30", "12:50", "17/05/22"))
        items.add(Reservation(teacher, "12:30", "12:50", "14/05/22"))
        items.add(Reservation(teacher, "12:30", "12:50", "16/05/22"))

        return items
    }



}
