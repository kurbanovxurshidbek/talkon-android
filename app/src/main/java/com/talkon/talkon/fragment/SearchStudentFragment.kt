package com.talkon.talkon.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.airbnb.lottie.LottieAnimationView
import com.skyfishjy.library.RippleBackground
import com.talkon.talkon.R
import com.talkon.talkon.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_search_student.*
import kotlinx.android.synthetic.main.partner_found_dialog.*

/**
 * In StudentFragment, student can talk with student, by random match
 */
class SearchStudentFragment : BaseFragment() {

    private var isClicked : Boolean = false
    lateinit var lottie_students: LottieAnimationView
    lateinit var dialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_search_student, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        bt_search_partner.setOnClickListener {
            ll_call.visibility = View.VISIBLE
            ll_search_partner.visibility = View.GONE
        }

        tv_exit_search_student.setOnClickListener {
            ll_call.visibility = View.GONE
            ll_search_partner.visibility = View.VISIBLE
        }

        bt_skip.setOnClickListener {
//            openPartnerDialog()

            val view = View.inflate(requireContext(), R.layout.partner_found_dialog, null)

            val builder = AlertDialog.Builder(requireContext())
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        }

    }

    private fun openPartnerDialog() {
        dialog.setContentView(R.layout.partner_found_dialog)

        btn_yes.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}
