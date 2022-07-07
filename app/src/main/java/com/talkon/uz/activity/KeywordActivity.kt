package com.talkon.uz.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.talkon.uz.R
import com.talkon.uz.adapter.KeywordAdapter
import com.talkon.uz.database.KeywordRepository
import com.talkon.uz.model.Keyword
import com.talkon.uz.viewmodel.KeywordViewModel
import kotlinx.android.synthetic.main.activity_keyword.*


/**
 * In SearchResultActivity, user can search for tutors
 */

class KeywordActivity : BaseActivity() {

    lateinit var viewModel: KeywordViewModel
    lateinit var repository: KeywordRepository
    var items: List<Keyword> = ArrayList<Keyword>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keyword)
        initViews()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this).get(KeywordViewModel::class.java)
        repository = KeywordRepository(application)
        recyclerView.setLayoutManager(GridLayoutManager(this, 1))

        iv_cancel.setOnClickListener {
            finish()
        }

        et_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val keyword = et_search.text.toString().trim()
                if (keyword.isEmpty()) {
                    viewModel.getKeywords(repository)
                } else {
                    viewModel.searchKeywords(repository,keyword)
                }
            }
        })

        et_search.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val name = et_search.text.toString().trim()
                val keyword = Keyword(name)

                viewModel.saveKeyword(repository, keyword)
                viewModel.getKeywords(repository)

                return@OnEditorActionListener true
            }
            false
        })

        viewModel.getKeywords(repository)
        viewModel.allKeywords.observe(this, Observer {
            items = it
            refreshAdapter(items)
        })
    }

    private fun refreshAdapter(items: List<Keyword>) {
        val adapter = KeywordAdapter(this, items)
        recyclerView.setAdapter(adapter)
    }

    fun deleteKeyword(keyword: Keyword) {
        viewModel.deleteKeyword(repository, keyword.id)
        viewModel.getKeywords(repository)
    }
}