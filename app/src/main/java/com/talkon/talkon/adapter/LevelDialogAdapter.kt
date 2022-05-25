package com.talkon.talkon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.talkon.talkon.R
import com.talkon.talkon.utils.CountryBottomSheetDialog
import com.talkon.talkon.model.Country
import com.talkon.talkon.utils.LevelDialog

class LevelDialogAdapter(var items: ArrayList<String>) : BaseAdapter(){
    var onClick : ((String) ->Unit)? = null
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_level_dialog_layout, parent, false)
        return LevelViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val level: String = items[position]
        if (holder is LevelViewHolder) {
            var tv_level = holder.tv_level
            var ll_level = holder.ll_level
            tv_level.text = level

            ll_level.setOnClickListener {
                onClick?.invoke(level)
            }

        }
    }

    class LevelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_level: TextView = view.findViewById(R.id.tv_level)
        var ll_level: LinearLayout = view.findViewById(R.id.ll_level)

    }
}