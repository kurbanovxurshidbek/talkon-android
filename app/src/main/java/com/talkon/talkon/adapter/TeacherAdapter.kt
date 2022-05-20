package com.talkon.talkon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.talkon.talkon.R
import com.talkon.talkon.fragment.mainFragment.SearchFragment
import com.talkon.talkon.model.Country

class TeacherAdapter (var fragment: SearchFragment, var items: ArrayList<Country>) : BaseAdapter(){
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_search_teacher_layout, parent, false)
        return TeacherViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val country: Country = items[position]
        if (holder is TeacherViewHolder) {
//            var tv_country_code = holder.tv_country_code
//            var tv_country_name = holder.tv_country_name
//            var iv_country_flag = holder.iv_country_flag
//
//            tv_country_code.text = country.countryCode
//            tv_country_name.text = country.countryName
//            Glide.with(fragment).load(country.countryFlag).into(iv_country_flag)
        }
    }

    class TeacherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_profile_picture: ShapeableImageView = view.findViewById(R.id.iv_profile_picture)
        var iv_talk_on_certified: ImageView = view.findViewById(R.id.iv_talk_on_certified)
        var iv_online: ImageView = view.findViewById(R.id.iv_online)
        var iv_star: TextView = view.findViewById(R.id.iv_star)
        var tv_fullname: TextView = view.findViewById(R.id.tv_fullname)
        var tv_lessons: TextView = view.findViewById(R.id.tv_lessons)
        var tv_new: TextView = view.findViewById(R.id.tv_new)
        var tv_rating: TextView = view.findViewById(R.id.tv_rating)
        var tv_about_teacher: TextView = view.findViewById(R.id.tv_about_teacher)
        var tv_busy_free: TextView = view.findViewById(R.id.tv_busy_free)
        var ll_rated: LinearLayout = view.findViewById(R.id.ll_rated)

    }
}