package com.talkon.talkon.adapter

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
import com.talkon.talkon.fragment.mainFragment.SearchFragment
import com.talkon.talkon.fragment.mainFragment.SearchTeacherFragment
import com.talkon.talkon.model.Teacher

class TeacherFragmentAdapter (var fragment: SearchTeacherFragment, var items: ArrayList<Teacher>) : BaseAdapter(){
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_search_teacher_layout, parent, false)
        return TeacherViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val country: Teacher = items[position]
        if (holder is TeacherViewHolder) {
            var tv_fullname = holder.tv_fullname
            var tv_about_teacher = holder.tv_about_teacher
            var tv_lessons = holder.tv_lessons
            var tv_rating = holder.tv_rating
            var tv_busy_free = holder.tv_busy_free
            var iv_online = holder.iv_online
            var iv_profile_picture = holder.iv_profile_picture
            var iv_star = holder.iv_star
            var tv_new = holder.tv_new
            var ll_rated = holder.ll_rated

            tv_fullname.text = country.fullName
            tv_about_teacher.text = country.aboutInfo
            tv_lessons.text = country.lessons
            tv_rating.text = country.rating
            tv_new.text = country.new


            iv_online.setImageResource(R.drawable.online_circle_green)
            iv_star.setImageResource(R.drawable.ic_gold_star_empty)
            Glide.with(fragment).load(country.profile)
                .placeholder(R.color.light_grey)
                .into(iv_profile_picture)

            if (country.isBusy){
                tv_busy_free.text = "Busy in 30 minutes"
            } else if(!country.isOnline){
                tv_busy_free.text = ""
            } else {
                tv_busy_free.text = "Free! Talk now!"
            }

            if (!country.isRated){
                tv_new.visibility = View.VISIBLE
                ll_rated.visibility = View.GONE
            }

            if (country.isOnline){
                iv_online.visibility = View.VISIBLE

            } else{
                iv_online.visibility = View.GONE
            }

        }
    }

    class TeacherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_profile_picture: ShapeableImageView = view.findViewById(R.id.iv_profile_picture)
        var iv_online: ImageView = view.findViewById(R.id.iv_online)
        var iv_star: ImageView = view.findViewById(R.id.iv_star)
        var tv_fullname: TextView = view.findViewById(R.id.tv_fullname)
        var tv_lessons: TextView = view.findViewById(R.id.tv_lessons)
        var tv_new: TextView = view.findViewById(R.id.tv_new)
        var tv_rating: TextView = view.findViewById(R.id.tv_rating)
        var tv_about_teacher: TextView = view.findViewById(R.id.tv_about_teacher)
        var tv_busy_free: TextView = view.findViewById(R.id.tv_busy_free)
        var ll_rated: LinearLayout = view.findViewById(R.id.ll_rated)

    }
}