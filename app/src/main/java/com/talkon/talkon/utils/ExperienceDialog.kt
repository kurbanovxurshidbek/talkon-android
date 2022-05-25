package com.talkon.talkon.utils

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.talkon.talkon.R
import com.talkon.talkon.activity.entryActivity.StatusChooseActivity
import com.talkon.talkon.adapter.ExperienceDialogAdapter
import com.talkon.talkon.adapter.LevelDialogAdapter

class ExperienceDialog(var listener: ExperienceListener): DialogFragment() {
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.white_border_rounded);
        var view = inflater.inflate(R.layout.experience_dialog_fragment, container, false)

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
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setLayoutManager(GridLayoutManager(context, 1))
        refreshAdapter(getLevel())
    }

    private fun refreshAdapter(items: ArrayList<String>) {
        var adapter = ExperienceDialogAdapter( items)
        recyclerView.adapter = adapter
        adapter.onClick = {
            this.dismiss()
            listener.onSelected(it)
        }
    }


    private fun getLevel(): ArrayList<String> {
        var experience = ArrayList<String>()

        for (i in 2..30){
            experience.add("$i years")
        }

        return experience
    }

    interface ExperienceListener{
        fun onSelected(experience: String)
    }

}