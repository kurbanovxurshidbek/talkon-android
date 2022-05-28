package com.talkon.talkon.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.talkon.talkon.R
import com.talkon.talkon.activity.entryActivity.StatusChooseActivity
import com.talkon.talkon.activity.entryActivity.StatusChooseActivity.Companion.sometext
import com.talkon.talkon.adapter.LevelDialogAdapter
import com.talkon.talkon.viewModel.StatusSharedViewModel

class LevelDialog(val listener: LevelListener): DialogFragment() {
    lateinit var recyclerView: RecyclerView
    private lateinit var statusSharedViewModel:StatusSharedViewModel

    public lateinit var selectedLevel: String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.white_border_rounded);
        var view = inflater.inflate(R.layout.level_dialog_fragment, container, false)

        initViews(view)
        return view
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun initViews(view: View) {
        statusSharedViewModel = ViewModelProvider(this).get(StatusSharedViewModel::class.java)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setLayoutManager(GridLayoutManager(context, 1))
        refreshAdapter(getLevel())
    }



    private fun refreshAdapter(items: ArrayList<String>) {
        var adapter = LevelDialogAdapter( items)
        recyclerView.adapter = adapter
        adapter.onClick = {
            this.dismiss()
            listener.onSelected(it)
        }
    }

    private fun getLevel(): ArrayList<String> {
        var level = ArrayList<String>()

        level.add("Beginner")
        level.add("Elementary")
        level.add("PreIntermediate")
        level.add("Intermediate")
        level.add("UpperIntermediate")
        level.add("Advanced")
        return level
    }

    interface LevelListener{
        fun onSelected(level: String)
    }
}

