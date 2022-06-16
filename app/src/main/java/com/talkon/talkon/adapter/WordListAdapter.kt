package com.talkon.talkon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.talkon.talkon.R
import com.talkon.talkon.activity.SearchResultActivity
import com.talkon.talkon.model.Word

class WordListAdapter(var activity: SearchResultActivity, diffCallback: WordDiff) : ListAdapter<Word, WordListAdapter.WordViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_search_history, parent, false)
        return WordViewHolder(view)
    }
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current: Word? = getItem(position)
        holder.bind(current?.word)
        var iv_cancel = holder.iv_cancel
        var ll_item = holder.ll_item
        iv_cancel.setImageResource(R.drawable.ic_close)

        ll_item.setOnClickListener {

        }
        iv_cancel.setOnClickListener {
            deleteItem(position, holder)
        }
    }

    private fun deleteItem(position: Int, holder: WordViewHolder) {
        activity.mWordViewModel?.delete(position)
        //activity.mWordViewModel?.getAll()
        notifyDataSetChanged()
    }

    class WordDiff : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.word.equals(newItem.word)
        }
    }

    class WordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(text: String?) {
            tv_searched_name.setText(text)
        }

        var tv_searched_name: TextView = view.findViewById(R.id.tv_searched_name)
        var iv_cancel: ImageView = view.findViewById(R.id.iv_cancel)
        var ll_item: LinearLayout = view.findViewById(R.id.ll_item)

    }

}
