package com.talkon.talkon.fragment

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.sangcomz.fishbun.FishBun
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter
import com.talkon.talkon.R
import kotlinx.android.synthetic.main.fragment_edit_profile.*

class EditProfileFragment : BaseFragment() {

    var pickedPhoto: Uri? = null
    var allPhotos = ArrayList<Uri>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View?) {

        iv_edit_profile_plus.setOnClickListener {
            pickFishBunPhoto()
            uploadUserPhoto()
        }


        save_button.setOnClickListener {
            changeUserName()
        }
    }

    private val photoLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                allPhotos =
                    it.data?.getParcelableArrayListExtra(FishBun.INTENT_PATH) ?: arrayListOf()
                pickedPhoto = allPhotos.get(0)
            }
        }

    private fun uploadUserPhoto() {
//        if (pickedPhoto == null) return
//        StorageManager.uploadUserPhoto(pickedPhoto!!, object : StorageHandler {
//            override fun onSuccess(imgUrl: String) {
//                DatabaseManager.updateUserImage(imgUrl)
        iv_edit_profile.setImageURI(pickedPhoto)
    }

    private fun callProfileFragment() {
        val fragmentProfile = ProfileFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fl_Fragment, fragmentProfile, "fragmnetId")
            .commit()
    }


    private fun pickFishBunPhoto() {
        FishBun.with(this)
            .setImageAdapter(GlideAdapter())
            .setMaxCount(1)
            .setMinCount(1)
            .setSelectedImages(allPhotos)
            .startAlbumWithActivityResultCallback(photoLauncher)
    }

    private fun changeUserName(){
        tv_fullname_edit_profile.text = editext_edit_profile.text
    }

    private fun updatePhoneNumber(){
        tv_number_edit_profile.text = et_phone_number_edit_profile.text
    }

}