package com.talkon.talkon.adapter

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
import com.talkon.talkon.model.LessonHistory

class LessonHistoryAdapter(
    var fragment: LessonHistoryFragment,
    var items: ArrayList<LessonHistory>
) : BaseAdapter() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_lesson_history, parent, false)
        return LessonHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is LessonHistoryViewHolder) {
            var iv_profile_picture = holder.iv_profile_picture
            var tv_name = holder.tv_name
            var tv_started_hour = holder.tv_started_hour
            var tv_finished_hour = holder.tv_finished_hour
            var tv_date = holder.tv_date
            var ic_arrow = holder.ic_arrow
            var video_view = holder.video_view
            var liner = holder.liner

            var teacher = item.teacher

            Glide.with(fragment).load(teacher.profile).error(R.drawable.ic_warning)
                .into(iv_profile_picture)
            tv_name.setText(teacher.fullName)
            tv_started_hour.setText(item.startedHour)
            tv_finished_hour.setText(item.finishedHour)
            tv_date.setText(item.date)


            var isExpandable = item.isExpandable
            video_view.visibility = if (isExpandable) View.VISIBLE else View.GONE

            if (isExpandable) {
                ic_arrow.setImageResource(R.drawable.ic_arrow_up)
                slideDowAnimation(video_view)
            } else {
                ic_arrow.setImageResource(R.drawable.ic_arrow_down)
                slideUpAnimation(video_view)
            }


            ic_arrow.setOnClickListener {
                item.isExpandable = !item.isExpandable
                notifyItemChanged(position)
            }
        }
    }

    class LessonHistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_profile_picture: ShapeableImageView = view.findViewById(R.id.iv_profile_picture)
        var tv_name: TextView = view.findViewById(R.id.tv_name)
        var tv_started_hour: TextView = view.findViewById(R.id.tv_started_hour)
        var tv_finished_hour: TextView = view.findViewById(R.id.tv_finished_hour)
        var tv_date: TextView = view.findViewById(R.id.tv_date)
        var ic_arrow: ImageView = view.findViewById(R.id.ic_arrow)
        var video_view: ShapeableImageView = view.findViewById(R.id.video_view)
        var liner: LinearLayout = view.findViewById(R.id.liner)

    }

    private fun slideUpAnimation(view: View) {
        var anim = AnimationUtils.loadAnimation(fragment.context, R.anim.slide_up_animation)
        view.startAnimation(anim)
    }

    private fun slideDowAnimation(view: View) {
        var anim = AnimationUtils.loadAnimation(fragment.context, R.anim.slide_down_animation)
        view.startAnimation(anim)
    }
}