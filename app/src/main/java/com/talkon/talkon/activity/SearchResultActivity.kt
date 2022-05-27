package com.talkon.talkon.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.talkon.talkon.R
import com.talkon.talkon.adapter.SearchHistoryAdapter
import kotlinx.android.synthetic.main.activity_search_result.*

/**
 * In SearchResultActivity, user can search for tutors
 */

class SearchResultActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
//        overridePendingTransition( 0,R.anim.search_fade_out )
        initViews()
    }

    private fun initViews() {
        recyclerView.setLayoutManager(GridLayoutManager(this, 1))
        refreshAdapter(getHistory())

        iv_cancel.setOnClickListener {
            finish()
        }
        
        et_search.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!!.length >=1){
                    recyclerView.visibility = View.GONE
                }
                else
                    recyclerView.visibility = View.VISIBLE
            }
        })
    }

    private fun refreshAdapter(history: ArrayList<String>) {
        var adapter = SearchHistoryAdapter(history)
        recyclerView.adapter = adapter
    }

    private fun getHistory(): ArrayList<String> {
        var history = ArrayList<String>()

        history.add("Cute")
        history.add("Kurbanov Khurshidbek ")
        history.add("Khurshidbek Kurbanov")

        return history
    }
}