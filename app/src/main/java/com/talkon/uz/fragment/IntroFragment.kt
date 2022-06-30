package com.talkon.uz.fragment

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import com.airbnb.lottie.LottieAnimationView
import com.talkon.uz.R

/**
 * IntroFragment is used to switch the viewPager fragment info in IntroActivity,
 * like introduction title, text and animation
 */

class IntroFragment : BaseFragment() {
    private var position = 0
    public lateinit var title: TextView
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
        title = view.findViewById<TextView>(R.id.tv_title)
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
        val PAGE_TITLES = intArrayOf(R.string.str_talk_online, R.string.str_professional_tutors, R.string.str_flexible_lessons, R.string.str_practice_with_student, R.string.str_record_your_lessons)

        // prepare all subtitle ids arrays
        @StringRes
        private val PAGE_TEXT = intArrayOf(R.string.str_talk_online_text, R.string.str_professional_tutors_text, R.string.str_flexible_lessons_text, R.string.str_practice_with_student_text, R.string.str_record_your_lessons_text)

        @StringRes
        private val PAGE_ANIMS = intArrayOf( R.raw.lottie_recorder, R.raw.lottire_professional_tutors , R.raw.lottie_flexible_lessons, R.raw.lottie_practice_student,R.raw.online_learning)

        fun newInstance(position: Int): IntroFragment {
            val fragment = IntroFragment()
            val args = Bundle()
            args.putInt(ARG_POSITION, position)
            fragment.arguments = args
            return fragment
        }
    }

}
