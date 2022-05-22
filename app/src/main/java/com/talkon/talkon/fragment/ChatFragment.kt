package com.talkon.talkon.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.talkon.talkon.R
import com.talkon.talkon.activity.ChatActivity
import com.talkon.talkon.adapter.ChatFragmentAdapter
import com.talkon.talkon.fragment.BaseFragment
import com.talkon.talkon.model.Chat

/**
 * ChatFragment is used for chatting between users, like, student to teacher, or student to student
 */

class ChatFragment : BaseFragment(){
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chat, container, false)
        initViews(view)
        return view
    }


    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setLayoutManager(GridLayoutManager(context, 1))
        refreshAdapter(getChat())
    }

    private fun refreshAdapter(items: ArrayList<Chat>) {
        var adapter = ChatFragmentAdapter(this, items)
        recyclerView.adapter = adapter
    }

    fun openItemDetails(chat: Chat) {
        callChatActivity()
    }

    private fun callChatActivity() {
        val intent = Intent(context, ChatActivity::class.java)
        startActivity(intent)
    }

    private fun getChat(): ArrayList<Chat> {
        var chat = ArrayList<Chat>()
        val user = "https://images.unsplash.com/photo-1529626455594-4ff0802cfb7e?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1374"
        val user2 = "https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387"
        var user3 = "https://images.unsplash.com/photo-1542596768-5d1d21f1cf98?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387"
        val user4 = "https://images.unsplash.com/photo-1528892952291-009c663ce843?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=344"
        val text = "Let`s speak natural British Englis, TESL, IELTS TOEFL English together , Let`s speak natural British English together"

        chat.add(Chat(user,"Lisa Kudrov",text, 1,  "22:09", true))
        chat.add(Chat(user2,"Richard F.",text, 2,  "Tue", false))
        chat.add(Chat(user3,"Rachel Green",text, null,  "Sun", true))
        chat.add(Chat(user4,"Saad Hakim",text, null,  "Apr 1", false))
        return chat
    }
}