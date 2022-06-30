package com.talkon.uz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.talkon.uz.R
import com.talkon.uz.utils.CountryBottomSheetDialog
import com.talkon.uz.model.Country
import com.talkon.uz.utils.ExperienceDialog
import com.talkon.uz.utils.LevelDialog

class ExperienceDialogAdapter( var items: ArrayList<String>) : BaseAdapter(){
    var onClick : ((String) ->Unit)? = null
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_experience_dialog_layout, parent, false)
        return ExperienceViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val level: String = items[position]
        if (holder is ExperienceViewHolder) {
            var tv_experience = holder.tv_experience
            var ll_experience = holder.ll_experience

            tv_experience.text = level
            ll_experience.setOnClickListener {
                onClick?.invoke(level)
            }

        }
    }

    class ExperienceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_experience: TextView = view.findViewById(R.id.tv_experience)
        var ll_experience: LinearLayout = view.findViewById(R.id.ll_experience)

    }
}