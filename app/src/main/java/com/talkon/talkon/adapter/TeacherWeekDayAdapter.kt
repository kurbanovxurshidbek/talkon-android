package com.talkon.talkon.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.talkon.talkon.R
import com.talkon.talkon.activity.AboutTeacherActivity
import com.talkon.talkon.fragment.mainFragment.SearchFragment
import com.talkon.talkon.fragment.mainFragment.SearchTeacherFragment
import com.talkon.talkon.model.Teacher
import com.talkon.talkon.model.WeekDay

class TeacherWeekDayAdapter (var items: ArrayList<WeekDay>) : BaseAdapter(){
    var onClick: ((WeekDay) -> Unit)? = null
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_week_day, parent, false)
        return AboutTeacherViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val weekDay: WeekDay = items[position]
        if (holder is AboutTeacherViewHolder) {
            var tv_week_light = holder.tv_week_light
            var tv_day_light = holder.tv_day_light

            tv_week_light.text = weekDay.tv_week_light
            tv_day_light.text = weekDay.tv_day_light

            holder.rootView.setOnClickListener {
                onClick?.invoke(weekDay)
            }

        }
    }

    class AboutTeacherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_week_light: TextView = view.findViewById(R.id.tv_week_light)
        var tv_day_light: TextView = view.findViewById(R.id.tv_day_light)
        val rootView = view.findViewById<LinearLayout>(R.id.ll_week)
    }
}