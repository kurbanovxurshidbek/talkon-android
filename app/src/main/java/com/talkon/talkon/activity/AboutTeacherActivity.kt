package com.talkon.talkon.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.talkon.talkon.R
import com.talkon.talkon.adapter.*
import com.talkon.talkon.model.Course
import com.talkon.talkon.model.Reviews
import com.talkon.talkon.model.WeekDay
import kotlinx.android.synthetic.main.activity_about_teacher.*

/**
 * In AboutTeacherActivity, user can read about teacher and book a trial
 */

class AboutTeacherActivity : BaseActivity(), Player.Listener{

//    lateinit var playerView: PlayerView
    lateinit var player: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_teacher)
        initViews()

        videoView()
        addMp4Files()
        if (savedInstanceState != null) {
            savedInstanceState.getInt("mediaItem").let { restoredMedia ->
                val seekTime = savedInstanceState.getLong("SeekTime")
                player.seekTo(restoredMedia, seekTime)
                player.play()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("SeekTime", player.currentPosition)
        outState.putInt("mediaItem", player.currentMediaItemIndex)
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        rv_about_teacher_week_day.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        weekDayAdapter(getWeekDay())

        rv_about_teacher_course.layoutManager = GridLayoutManager(this, 3)
        courseAdapter(getCourse())

        if (getCourse().count() > 6) {
            tv_more_btn.visibility = View.VISIBLE
        }

        tv_more_btn.setOnClickListener {
            (rv_about_teacher_course.adapter as TeacherCourseAdapter).updateList(getCourseAll())
            Log.d("@@@", "nimadur")
            tv_collapse.visibility = View.VISIBLE
            tv_more_btn.visibility = View.GONE
        }
        tv_collapse.setOnClickListener {
            (rv_about_teacher_course.adapter as TeacherCourseAdapter).updateList(getCourse())
            tv_collapse.visibility = View.GONE
            tv_more_btn.visibility = View.VISIBLE
        }

        rv_about_teacher_reviews.layoutManager = LinearLayoutManager(this)
        reviewsAdapter(getReviews())

//        videoView()

        iv_back.setOnClickListener {
            callSearchTeacherFragment()
        }

    }

    private fun callSearchTeacherFragment() {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)

    }

    private fun getCourseAll(): java.util.ArrayList<Course> {

        var course = ArrayList<Course>()

        course.add(Course("01 : 00 pm"))
        course.add(Course("02 : 00 pm"))
        course.add(Course("03 : 00 am"))
        course.add(Course("04 : 00 pm"))
        course.add(Course("05 : 00 pm"))
        course.add(Course("06 : 00 am"))
        course.add(Course("07 : 00 am"))
        course.add(Course("08 : 00 am"))
        course.add(Course("09 : 00 am"))
        course.add(Course("10 : 00 am"))
        course.add(Course("11 : 00 am"))
        course.add(Course("12 : 00 am"))
        course.add(Course("13 : 00 am"))
        course.add(Course("14 : 00 am"))
        course.add(Course("15 : 00 am"))
        course.add(Course("16 : 00 am"))
        course.add(Course("17 : 00 am"))
        course.add(Course("18 : 00 am"))

        val list = (rv_about_teacher_course.adapter as TeacherCourseAdapter).items
        for (position in 0 until list.size) {
            course[position].isChecked = list[position].isChecked
        }

        return course
    }

    private fun videoView() {

        player = ExoPlayer.Builder(this).build()
//        playerView = exoPlayer
        exoPlayer.player = player
        player.addListener(this)



//
//        val mediaController = MediaController( this)
//        mediaController.setAnchorView(vv_ab_teacher)
//        val onlineUri =
//            Uri.parse("https://assets.mixkit.co/videos/preview/mixkit-people-pouring-a-warm-drink-around-a-campfire-513-large.mp4")
//
//        play_button.setOnClickListener {
//            if (vv_ab_teacher.isPlaying) {
//                vv_ab_teacher.pause()
//                play_button.visibility = View.VISIBLE
//
//            } else {
//                vv_ab_teacher.setMediaController(mediaController)
//                vv_ab_teacher.setVideoURI(onlineUri)
//                vv_ab_teacher.requestFocus()
//                vv_ab_teacher.start()
//
//                play_button.visibility = View.GONE
//            }
//        }
    }

    private fun addMp4Files() {
//        val mediaItem = MediaItem.fromUri("https://assets.mixkit.co/videos/preview/mixkit-people-pouring-a-warm-drink-around-a-campfire-513-large.mp4")
        val mediaItem = MediaItem.fromUri("https://cdn.videvo.net/videvo_files/video/free/2018-05/large_watermarked/180419_Boxing_01_03_preview.mp4")
        player.addMediaItem(mediaItem)
        player.prepare()
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        super.onPlaybackStateChanged(playbackState)

        when (playbackState) {
            Player.STATE_BUFFERING -> {
                progressBar.visibility = View.VISIBLE
            }
            Player.STATE_READY -> {
                progressBar.visibility = View.INVISIBLE
            }
        }
    }

    override fun onStop() {
        super.onStop()
        player.release()
    }

    @SuppressLint("ResourceAsColor")
    private fun weekDayAdapter(items: ArrayList<WeekDay>) {
        var adapter = TeacherWeekDayAdapter(items)
        rv_about_teacher_week_day.adapter = adapter

        adapter.onClick = {

        }

    }

    private fun courseAdapter(items: ArrayList<Course>) {
        var adapter = TeacherCourseAdapter(items)
        rv_about_teacher_course.adapter = adapter
    }

    private fun reviewsAdapter(items: ArrayList<Reviews>) {
        var adapter = TeacherReviewsAdapter(items)
        rv_about_teacher_reviews.adapter = adapter
    }

    private fun getWeekDay(): ArrayList<WeekDay> {
        var weekDay = ArrayList<WeekDay>()

        weekDay.add(WeekDay("Mon", "12"))
        weekDay.add(WeekDay("Fri", "8"))
        weekDay.add(WeekDay("Mon", "12"))
        weekDay.add(WeekDay("Mon", "12"))
        weekDay.add(WeekDay("Mon", "12"))
        weekDay.add(WeekDay("Mon", "12"))
        weekDay.add(WeekDay("Mon", "12"))
        weekDay.add(WeekDay("Mon", "12"))
        weekDay.add(WeekDay("Mon", "12"))
        weekDay.add(WeekDay("Mon", "12"))


        return weekDay
    }


    private fun getCourse(): ArrayList<Course> {
        var course = ArrayList<Course>()

        course.add(Course("01 : 00 pm"))
        course.add(Course("02 : 00 pm"))
        course.add(Course("03 : 00 am"))
        course.add(Course("04 : 00 pm"))
        course.add(Course("05 : 00 pm"))
        course.add(Course("06 : 00 am"))
        course.add(Course("06 : 00 am"))

        return course
    }

    private fun getReviews(): ArrayList<Reviews> {
        var reviews = ArrayList<Reviews>()
        val description = "For the most part, teachers are undervalued and\n" +
                "underappreciated. This is especially sad considering the tremendous impact that teachers have on a daily basis."


        reviews.add(Reviews(R.drawable.img, "Lisa Kudrov", description, "May 18, 2022"))
        reviews.add(Reviews(R.drawable.img_1, "Ross Geller", description, "May 11, 2022"))
        reviews.add(Reviews(R.drawable.img_2, "Lisa Kudrov", description, "May 19, 2022"))
        reviews.add(Reviews(R.drawable.img, "Richard F", description, "May 18, 2022"))
        reviews.add(Reviews(R.drawable.img_2, "Jeniffer Aniston", description, "May 2, 2022"))
        reviews.add(Reviews(R.drawable.img_2, "Richard F", description, "May 17, 2022"))
        reviews.add(Reviews(R.drawable.img_1, "Ross Geller", description, "May 15, 2022"))


        return reviews
    }

}