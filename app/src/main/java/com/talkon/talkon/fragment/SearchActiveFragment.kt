package com.talkon.talkon.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.talkon.talkon.R

class SearchActiveFragment: BaseFragment() {
    companion object {
        private const val KEY_STRING = "string"
        fun newInstance(text: String?): SearchActiveFragment {
            val args = Bundle()
            args.putString(KEY_STRING, text)
            val newFragment = SearchActiveFragment()
            newFragment.arguments = args
            return newFragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_active, container, false)
    }
}