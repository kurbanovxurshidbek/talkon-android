package com.talkon.uz.activity.entryActivity

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.MediaController
import androidx.activity.result.contract.ActivityResultContracts
import com.sangcomz.fishbun.BaseActivity
import com.sangcomz.fishbun.FishBun
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter
import com.talkon.uz.R
import com.talkon.uz.activity.TeacherActivity
import com.talkon.uz.utils.ExperienceDialog
import kotlinx.android.synthetic.main.activity_about_teacher.*
import kotlinx.android.synthetic.main.activity_upload_video.*
import kotlinx.android.synthetic.main.activity_upload_video.iv_back
import kotlinx.android.synthetic.main.activity_upload_video.iv_profile_picture
import kotlinx.android.synthetic.main.activity_upload_video.tv_experience
import java.io.*
import java.util.*


/**
 * In UploadVideoActivity, teacher can upload his/her video, photo, about teacher information and certificates
 */
class UploadVideoActivity : BaseActivity() {
    var pickedPhoto: Uri? = null
    var allPhotos = ArrayList<Uri>()
    lateinit var selectedVideoPath:String
    private val VIDEO_DIRECTORY = "/demonuts"
    private val GALLERY = 1
    private var CAMERA = 2
    private lateinit var newfile: File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_video)

        initViews()
    }

    private fun initViews() {
        ll_upload_video.setOnClickListener{
           chooseVideoFromGallery()
        }

        ll_pick_photo.setOnClickListener {
            uploadUserPhoto()
            pickFishBunPhoto()
        }

        iv_back.setOnClickListener{
            finish()
        }

        tv_experience.text = ""

        ll_experience.setOnClickListener {
            ExperienceDialog(object : ExperienceDialog.ExperienceListener{
                override fun onSelected(experience: String) {
                    tv_experience.visibility = View.VISIBLE
                    tv_experience.text = experience
                }
            }).show(supportFragmentManager, "MyCustomFragment")
        }

        bt_finish.setOnClickListener {
//            SharedPref(this).isSaved = true
            callTeacherActivity()
        }
    }

    private fun chooseVideoFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("result", "" + resultCode)

        val mediaController = MediaController( this)
        mediaController.setAnchorView(videoView)
        if (resultCode == RESULT_CANCELED) {
            Log.d("what", "cancel")
            return
        }

        if (requestCode == GALLERY) {
            Log.d("what", "gale")
            if (data != null) {
                val contentURI = data.data
                selectedVideoPath = getPath(contentURI)!!
                Log.d("path", selectedVideoPath!!)
                saveVideoToInternalStorage(selectedVideoPath)
                videoView.requestFocus()
                ll_upload_video.visibility = View.GONE
                ll_video.visibility= View.VISIBLE
                iv_play_button.setOnClickListener {
                    if (videoView.isPlaying) {
                        videoView.pause()
                        iv_play_button.visibility = View.VISIBLE

                    } else {
                        videoView.setMediaController(mediaController)
                        videoView.setVideoURI(contentURI)
                        videoView.requestFocus()
                        videoView.start()
                        iv_play_button.visibility = View.GONE
                    }
                }
            }

            iv_delete.setOnClickListener {
                newfile = File("")
                ll_upload_video.visibility = View.VISIBLE
                ll_video.visibility= View.GONE

            }
        }

    }

    private fun saveVideoToInternalStorage(filePath: String) {
        try {
            val currentFile = File(filePath)
            val wallpaperDirectory =
                File(Environment.getExternalStorageDirectory().toString() + VIDEO_DIRECTORY)
            newfile = File(wallpaperDirectory, Calendar.getInstance().getTimeInMillis().toString() + ".mp4")
            if (!wallpaperDirectory.exists()) {
                wallpaperDirectory.mkdirs()
            }
            if (currentFile.exists()) {
                val `in`: InputStream = FileInputStream(currentFile)
                val out: OutputStream = FileOutputStream(newfile)

                // Copy the bits from instream to outstream
                val buf = ByteArray(1024)
                var len: Int
                while (`in`.read(buf).also { len = it } > 0) {
                    out.write(buf, 0, len)
                }
                `in`.close()
                out.close()
                Log.v("vii", "Video file saved successfully.")
            } else {
                Log.v("vii", "Video saving failed. Source file missing.")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("Recycle")
    private fun getPath(uri: Uri?): String? {
        val projection = arrayOf(MediaStore.Video.Media.DATA)
        contentResolver
        val cursor = applicationContext.contentResolver.query(uri!!, projection, null, null, null)
        return if (cursor != null) {
            val column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            cursor.moveToFirst()
            cursor.getString(column_index)
        } else null
    }
    private fun uploadUserPhoto() {
        iv_profile_picture.setImageURI(pickedPhoto)
        iv_profile_picture.visibility = View.VISIBLE
        tv_tap_to_upload.text = "Change"
    }

    private val photoLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                allPhotos = it.data?.getParcelableArrayListExtra(FishBun.INTENT_PATH) ?: arrayListOf()
                pickedPhoto = allPhotos.get(0)
            }
        }

    private fun pickFishBunPhoto() {
        FishBun.with(this)
            .setImageAdapter(GlideAdapter())
            .setMaxCount(1)
            .setMinCount(1)
            .setSelectedImages(allPhotos)
            .startAlbumWithActivityResultCallback(photoLauncher)
    }

    private fun callTeacherActivity() {
        val intent = Intent(this, TeacherActivity::class.java)
        startActivity(intent)
        finish()
    }
}
