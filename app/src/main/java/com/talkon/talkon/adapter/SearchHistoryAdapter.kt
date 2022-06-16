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
import com.talkon.talkon.activity.SearchResultActivity
import com.talkon.talkon.manager.SharedPref
import com.talkon.talkon.utils.CountryBottomSheetDialog
import com.talkon.talkon.model.Country
import com.talkon.talkon.model.Word
import com.talkon.talkon.utils.LevelDialog

class SearchHistoryAdapter(var activity : SearchResultActivity, var items: ArrayList<Word>) : BaseAdapter(){
    var onClick : ((Word) ->Unit)? = null
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_search_history, parent, false)
        return SearchHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val history: Word = items[position]
        if (holder is SearchHistoryViewHolder) {
            var tv_searched_name = holder.tv_searched_name
            tv_searched_name.text = history.toString()
            var iv_cancel = holder.iv_cancel
            var ll_item = holder.ll_item
            iv_cancel.setImageResource(R.drawable.ic_close)

            ll_item.setOnClickListener {

            }

            iv_cancel.setOnClickListener {
                deleteItem(position, holder)
            }
        }
    }

    fun deleteItem(position: Int,holder: SearchHistoryViewHolder) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, items.size)
        holder.itemView.visibility = View.GONE
    }

    class SearchHistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_searched_name: TextView = view.findViewById(R.id.tv_searched_name)
        var iv_cancel: ImageView = view.findViewById(R.id.iv_cancel)
        var ll_item: LinearLayout = view.findViewById(R.id.ll_item)

    }
}