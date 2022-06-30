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
import com.talkon.uz.fragment.ChatFragment
import com.talkon.uz.model.Chat

class ChatFragmentAdapter (var fragment: ChatFragment, var items: ArrayList<Chat>) : BaseAdapter() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_layout, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chat: Chat = items[position]
        if (holder is ChatViewHolder) {
            var tv_fullname = holder.tv_fullname
            var tv_time = holder.tv_time
            var tv_message = holder.tv_message
            var tv_message_count = holder.tv_message_count
            var iv_online = holder.iv_online
            var iv_profile_picture = holder.iv_profile_picture
            var ll_chat = holder.ll_chat

            tv_fullname.text = chat.fullName
            tv_time.text = chat.time
            tv_message.text = chat.message
            tv_message_count.text = chat.messageCount.toString()

            iv_online.setImageResource(R.drawable.online_circle_green)

            Glide.with(fragment).load(chat.profile)
                .placeholder(R.color.light_grey)
                .into(iv_profile_picture)

            if (chat.isOnline) {
                iv_online.visibility = View.VISIBLE

            } else {
                iv_online.visibility = View.GONE
            }
            if (chat.messageCount != null) {
                tv_message_count.visibility = View.VISIBLE
            }

            ll_chat.setOnClickListener {
                fragment.openItemDetails(chat)
            }

        }
    }

    class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_profile_picture: ShapeableImageView = view.findViewById(R.id.iv_profile_picture)
        var iv_online: ImageView = view.findViewById(R.id.iv_online)
        var tv_fullname: TextView = view.findViewById(R.id.tv_fullname)
        var tv_time: TextView = view.findViewById(R.id.tv_time)
        var tv_message: TextView = view.findViewById(R.id.tv_message)
        var tv_message_count: TextView = view.findViewById(R.id.tv_message_count)
        var ll_chat: LinearLayout = view.findViewById(R.id.ll_chat)

    }
}