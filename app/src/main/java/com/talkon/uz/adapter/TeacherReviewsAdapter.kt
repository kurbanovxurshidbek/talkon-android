package com.talkon.uz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.talkon.uz.R
import com.talkon.uz.activity.AboutTeacherActivity
import com.talkon.uz.model.Reviews
import com.talkon.uz.model.Teacher

class TeacherReviewsAdapter (var items: ArrayList<Reviews>) : BaseAdapter(){
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_teacher_reviews, parent, false)
        return TeacherViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val reviews: Reviews = items[position]
        if (holder is TeacherViewHolder) {
            var tv_fullname = holder.tv_fullname
            var tv_item_reviews = holder.tv_item_reviews
            var iv_profile_picture = holder.iv_profile_picture
            var tv_date = holder.tv_date

            tv_fullname.text = reviews.fullName
            tv_item_reviews.text = reviews.tv_item_reviews
            tv_date.text = reviews.tv_date
            iv_profile_picture.setImageResource(reviews.profile)
        }
    }

    class TeacherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_profile_picture: ShapeableImageView = view.findViewById(R.id.iv_profile_picture)
        var tv_item_reviews: TextView = view.findViewById(R.id.tv_item_reviews)
        var tv_fullname: TextView = view.findViewById(R.id.tv_fullname)
        var tv_date: TextView = view.findViewById(R.id.tv_date)


    }
}