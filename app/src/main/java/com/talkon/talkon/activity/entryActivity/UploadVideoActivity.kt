package com.talkon.talkon.activity.entryActivity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.sangcomz.fishbun.BaseActivity
import com.sangcomz.fishbun.FishBun
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter
import com.talkon.talkon.R
import com.talkon.talkon.activity.TeacherActivity
import kotlinx.android.synthetic.main.activity_upload_video.*
import kotlinx.android.synthetic.main.fragment_edit_profile.*

/**
 * In UploadVideoActivity, teacher can upload his/her video, photo, about teacher information and certificates
 */
class UploadVideoActivity : BaseActivity() {
    var pickedPhoto: Uri? = null
    var allPhotos = ArrayList<Uri>()

    val REQUEST_VIDEO_CODE = 100
    lateinit var videoPath: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_video)

        initViews()
    }

    private fun initViews() {
        ll_upload_video.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
            intent.type = "video/*"
            startActivityForResult(intent, REQUEST_VIDEO_CODE)
        }

        ll_pick_photo.setOnClickListener {
            pickFishBunPhoto()
            uploadUserPhoto()
        }

        iv_back.setOnClickListener{
            finish()
        }

        bt_finish.setOnClickListener {
            callTeacherActivity()
        }
    }

    private fun callTeacherActivity() {
        val intent = Intent(this, TeacherActivity::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                val selectedVideoUri = data?.data

                // OI FILE Manager
                val filemanagerstring = selectedVideoUri.toString()
                videoPath = filemanagerstring

                // MEDIA GALLERY
                val selectedVideoPath = getPath(selectedVideoUri)
                if (selectedVideoPath != null) {
                    videoPath = selectedVideoPath
                    ll_upload_video.visibility = View.GONE
                    rl_about_video.visibility = View.VISIBLE
                    playVideoInDevicePlayer(videoPath)
                } else{
                    ll_upload_video.visibility = View.VISIBLE
                    rl_about_video.visibility = View.GONE
                }
            }
        }
    }

    //this function returns null when using IO file manager
    fun getPath(uri: Uri?): String? {
        val projection = arrayOf(MediaStore.Video.Media.DATA)
        contentResolver
        val cursor = applicationContext.contentResolver.query(uri!!, projection, null, null, null)
        return if (cursor != null) {
            val column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            cursor.moveToFirst()
            cursor.getString(column_index)
        } else null
    }

    fun playVideoInDevicePlayer(videoPath: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoPath))
        intent.setDataAndType(Uri.parse(videoPath), "video/mp4")
        startActivity(intent)
    }

    private fun uploadUserPhoto() {
//        if (pickedPhoto == null) return
//        StorageManager.uploadUserPhoto(pickedPhoto!!, object : StorageHandler {
//            override fun onSuccess(imgUrl: String) {
//                DatabaseManager.updateUserImage(imgUrl)
        iv_profile_picture.setImageURI(pickedPhoto)
        iv_profile_picture.visibility = View.VISIBLE
        tv_tap_to_upload.text = "Change"
    }

    private val photoLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                allPhotos =
                    it.data?.getParcelableArrayListExtra(FishBun.INTENT_PATH) ?: arrayListOf()
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
}