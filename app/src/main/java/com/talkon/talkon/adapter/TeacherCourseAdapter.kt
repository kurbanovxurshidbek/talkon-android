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
import com.talkon.talkon.model.Course
import com.talkon.talkon.model.Teacher
import com.talkon.talkon.model.WeekDay

class TeacherCourseAdapter (var items: ArrayList<Course>) : BaseAdapter(){
    var onClick: ((Course) -> Unit)? = null

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList:ArrayList<Course>){
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_ab_tech_course, parent, false)
        return AboutTeacherViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val course: Course = items[position]
        if (holder is AboutTeacherViewHolder) {
            var tv_course = holder.tv_course

            tv_course.text = course.tv_course

            holder.rootView.setOnClickListener {
                onClick?.invoke(course)
                holder.rootView.setBackgroundResource(R.drawable.back_rounded_green)
                holder.tv_course.setTextColor(R.color.white)
            }

        }
    }

    class AboutTeacherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_course: TextView = view.findViewById(R.id.tv_course)
        val rootView = view.findViewById<LinearLayout>(R.id.ll_course)
    }
}