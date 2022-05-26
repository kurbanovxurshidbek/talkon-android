package com.talkon.talkon.activity.entryActivity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.santalu.maskara.Mask
import com.santalu.maskara.MaskChangedListener
import com.santalu.maskara.MaskStyle
import com.talkon.talkon.R
import com.talkon.talkon.activity.BaseActivity
import com.talkon.talkon.model.Country
import com.talkon.talkon.utils.CountryBottomSheetDialog
import com.talkon.talkon.utils.Extensions.toast
import com.talkon.talkon.utils.Logger
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset


/**
 * In SignInActivity, user can sign in using phone number or Google
 */

class SignInActivity : BaseActivity() {
    private val bottomSheet = CountryBottomSheetDialog(this)
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        initViews()
        simCountryIso()
    }

    private fun initViews() {

        bt_sign_in_light.setOnClickListener {
            callNumberVerificationActivity(this)
        }


        ll_country_code.setOnClickListener {

            if (!bottomSheet.isVisible){
                bottomSheet.show(
                    supportFragmentManager,
                    "ModalBottomSheet",
                )
            }
        }
    }

    open fun itemCountry(country: Country?,mask:String){
        tv_country_code!!.text = country!!.prefix
        Glide.with(this).load(country!!.flag).into(iv_country_flag)


        var mask = Mask(
            value = mask,
            character = '#',
            style = MaskStyle.NORMAL
        )

        val listener = MaskChangedListener(mask)
        et_phone_number.addTextChangedListener(listener)


        bottomSheet.dismiss()
    }

    private fun simCountryIso() {
        val tm = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val simCountryIso =tm.simCountryIso

    }
}