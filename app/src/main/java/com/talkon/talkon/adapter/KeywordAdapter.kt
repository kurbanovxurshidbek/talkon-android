package com.talkon.talkon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.talkon.talkon.R
import com.talkon.talkon.activity.KeywordActivity
import com.talkon.talkon.model.Keyword

class KeywordAdapter(var activity: KeywordActivity, var items: List<Keyword>) : BaseAdapter() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_keyword, parent, false)
        return KeywordViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val keyword: Keyword = items[position]
        if (holder is KeywordViewHolder) {
            holder.tv_keyword.text = keyword.keyword
            holder.iv_remove.setOnClickListener {
                activity.deleteKeyword(keyword)
            }
        }
    }

    inner class KeywordViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var tv_keyword: TextView
        var iv_remove: ImageView

        init {
            tv_keyword = view.findViewById(R.id.tv_keyword)
            iv_remove = view.findViewById(R.id.iv_remove)
        }
    }

}