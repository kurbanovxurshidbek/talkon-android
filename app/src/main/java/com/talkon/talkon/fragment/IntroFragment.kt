package com.talkon.talkon.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import com.airbnb.lottie.LottieAnimationView
import com.talkon.talkon.R
import kotlinx.android.synthetic.main.activity_intro.*
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.fragment_intro.*

/**
 * IntroFragment is used to switch the viewPager fragment info in IntroActivity,
 * like introduction title, text and animation
 */

class IntroFragment : BaseFragment() {
    private var position = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            position = requireArguments().getInt(ARG_POSITION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_intro, container, false)

        return view
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = view.findViewById<TextView>(R.id.tv_title)
        val text = view.findViewById<TextView>(R.id.tv_text)
        val anim = view.findViewById<LottieAnimationView>(R.id.lotti_teacher)
        // set page title
        title.setText(PAGE_TITLES[position])
        // set page sub title text
        text.setText(PAGE_TEXT[position])
        anim.setAnimation(PAGE_ANIMS[position])
    }

    companion object {
        private const val ARG_POSITION = "slider-position"

        // prepare all title ids arrays
        @StringRes
        private val PAGE_TITLES = intArrayOf(R.string.str_thousands_of_teachers, R.string.str_chat_with_teachers, R.string.str_chat_with_teachers, R.string.str_live_talk_with_teachers )

        @StringRes
        private val PAGE_ANIMS = intArrayOf(R.raw.thousands_of_teachers, R.raw.lottie_students, R.raw.lottie_students, R.raw.lottie_students )

        // prepare all subtitle ids arrays
        @StringRes
        private val PAGE_TEXT = intArrayOf(
            R.string.str_you_can_contact_teachers, R.string.str_you_can_contact_teachers, R.string.str_you_can_contact_teachers,R.string.str_you_can_contact_teachers
        )

        fun newInstance(position: Int): IntroFragment {
            val fragment = IntroFragment()
            val args = Bundle()
            args.putInt(ARG_POSITION, position)
            fragment.arguments = args
            return fragment
        }
    }
}
