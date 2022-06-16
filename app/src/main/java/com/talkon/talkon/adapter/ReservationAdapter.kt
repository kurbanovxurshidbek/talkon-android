package com.talkon.talkon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.skydoves.powerspinner.createPowerSpinnerView
import com.talkon.talkon.R
import com.talkon.talkon.fragment.LessonHistoryFragment
import com.talkon.talkon.fragment.ReservationFragment
import com.talkon.talkon.model.LessonHistory
import com.talkon.talkon.model.Reservation

class ReservationAdapter(var fragment: ReservationFragment, var items: ArrayList<Reservation>) :
    BaseAdapter() {


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_reservation, parent, false)
        return ReservationViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is ReservationViewHolder) {
            var iv_profile_picture = holder.iv_profile_picture
            var tv_name = holder.tv_name
            var tv_started_hour = holder.tv_started_hour
            var tv_finished_hour = holder.tv_finished_hour
            var tv_date = holder.tv_date
            var teacher = item.teacher

            Glide.with(fragment).load(teacher.profile).error(R.drawable.ic_warning)
                .into(iv_profile_picture)
            tv_name.setText(teacher.fullName)
            tv_started_hour.setText(item.startedHour)
            tv_finished_hour.setText(item.finishedHour)
            tv_date.setText(item.date)

        }
    }

    class ReservationViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var iv_profile_picture: ShapeableImageView = view.findViewById(R.id.iv_profile_picture)
        var tv_name: TextView = view.findViewById(R.id.tv_name)
        var tv_started_hour: TextView = view.findViewById(R.id.tv_started_hour)
        var tv_finished_hour: TextView = view.findViewById(R.id.tv_finished_hour)
        var tv_date: TextView = view.findViewById(R.id.tv_date)

    }

}