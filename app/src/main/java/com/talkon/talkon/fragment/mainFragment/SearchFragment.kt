package com.talkon.talkon.fragment.mainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skydoves.powerspinner.PowerSpinnerView
import com.talkon.talkon.R
import com.talkon.talkon.fragment.BaseFragment
import com.talkon.talkon.utils.Extensions.toast

/**
 * SearchFragment is used for searching Tutors or Students
 */
class SearchFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {

        val powerSpinnerView = view.findViewById<PowerSpinnerView>(R.id.powerSpinnerView)
        powerSpinnerView.setIsFocusable(true)
        powerSpinnerView.setOnSpinnerItemSelectedListener<String> { oldIndex, oldItem, newIndex, newItem ->
            activity?.toast("$newItem is selected! (previous: $oldItem)")
        }
//        powerSpinnerView.show() // show the spinner popup
//        powerSpinnerView.dismiss() // dismiss the spinner popup
//
//        // If the popup is not showing, shows the spinner popup menu.
//// If the popup is already showing, dismiss the spinner popup menu.
//        powerSpinnerView.showOrDismiss()
    }
}