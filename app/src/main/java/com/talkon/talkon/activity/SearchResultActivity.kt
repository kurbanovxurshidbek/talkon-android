package com.talkon.talkon.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.talkon.talkon.R
import com.talkon.talkon.adapter.WordListAdapter
import com.talkon.talkon.model.Word
import com.talkon.talkon.viewModel.SearchResultActivityViewModel
import kotlinx.android.synthetic.main.activity_search_result.*


/**
 * In SearchResultActivity, user can search for tutors
 */

class SearchResultActivity : BaseActivity() {
    var mWordViewModel: SearchResultActivityViewModel? = null
    private lateinit var adapter: WordListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
//        overridePendingTransition( 0,R.anim.search_fade_out )
        initViews()
    }

    private fun initViews() {
        buildRecyclerView()
        searchAction()
        iv_cancel.setOnClickListener {
            finish()
        }

        // Get a new or existing ViewModel from the ViewModelProvider.
        mWordViewModel = ViewModelProvider(this)[SearchResultActivityViewModel::class.java]

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mWordViewModel!!.delete(1)
        mWordViewModel!!.allWords.observe(this) { words ->
            // Update the cached copy of the words in the adapter.
            adapter.submitList(words)

        }

    }

    private fun searchAction() {
        et_search.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!!.length >=1){
                    recyclerView.visibility = View.GONE
                    var keyword = et_search.text.toString().trim()
                    mWordViewModel?.performSearch(keyword)
                } else{
                    recyclerView.visibility = View.VISIBLE
                }
            }
        })

        et_search.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                var name = et_search.text.toString().trim()
                var word = Word(name)
                mWordViewModel?.insert(word)
                return@OnEditorActionListener true
            }
            false
        })
    }

//    fun performSearch(keyword: String) {
//        if (keyword.isEmpty())
//            adapter.items
//        items.clear()
//        for (name in names){
//            if (name!!.toLowerCase().contains(keyword.toLowerCase())) {
//                items.add(name)
//            }
//        }
//        adapter.items
//    }

    private fun buildRecyclerView() {
        adapter = WordListAdapter(this, WordListAdapter.WordDiff())
        val manager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

}