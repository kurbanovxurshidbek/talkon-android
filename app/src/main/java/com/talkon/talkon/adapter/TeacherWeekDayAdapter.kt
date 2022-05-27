package com.talkon.talkon.adapter

import android.annotation.SuppressLint
import android.os.Build
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
import com.talkon.talkon.model.Teacher
import com.talkon.talkon.model.WeekDay
import kotlinx.android.synthetic.main.item_week_day.*

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
        var weekDay: WeekDay = items[position]
        if (holder is AboutTeacherViewHolder) {
            var tv_week_light = holder.tv_week_light
            var tv_day_light = holder.tv_day_light

            tv_week_light.text = weekDay.tv_week_light
            tv_day_light.text = weekDay.tv_day_light



            holder.rootView.setOnClickListener {
                onClick?.invoke(weekDay)
                holder.ll_week_day.setBackgroundResource(R.drawable.back_rounded_green)
                holder.tv_day_light.setTextColor(R.color.white)
                holder.tv_week_light.setTextColor(R.color.white)
                }

//                holder.ll_week_day.setBackgroundResource(R.drawable.back_rounded_green)
//                holder.tv_day_light.setTextColor(R.color.white)
//                holder.tv_week_light.setTextColor(R.color.white)
            }



        }
    }


    class AboutTeacherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_week_light: TextView = view.findViewById(R.id.tv_week_light)
        var tv_day_light: TextView = view.findViewById(R.id.tv_day_light)
        val rootView = view.findViewById<LinearLayout>(R.id.ll_week)
        val ll_week_day = view.findViewById<LinearLayout>(R.id.ll_item_week_day_light)
    }
