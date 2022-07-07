package com.talkon.uz.activity.entryActivity

import android.content.Context
import android.os.Bundle
import android.telephony.TelephonyManager
import android.text.Editable
import android.text.TextWatcher
import com.bumptech.glide.Glide
import com.santalu.maskara.Mask
import com.santalu.maskara.MaskChangedListener
import com.santalu.maskara.MaskStyle
import com.talkon.uz.R
import com.talkon.uz.activity.BaseActivity
import com.talkon.uz.model.Country
import com.talkon.uz.network.RetrofitHttp
import com.talkon.uz.utils.CountryBottomSheetDialog
import com.talkon.uz.utils.Logger
import kotlinx.android.synthetic.main.activity_log_in.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * In SignInActivity, user can sign in using phone number or Google
 */

class LogInActivity : BaseActivity() {
    private val bottomSheet = CountryBottomSheetDialog(this)
    private var countryObj: Country? = null
    private var maskLength: Int? = null
    private var phoneNumber: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        initViews()
    }

    private fun initViews() {
        setDefaultCountry()

        var textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                bt_sign_in_light.isEnabled = if (p0!!.length == maskLength) true else false
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        }

        et_phone_number.addTextChangedListener(textWatcher)

        bt_sign_in_light.setOnClickListener {
            phoneNumber = countryObj!!.prefix + et_phone_number.text
            phoneNumber = phoneNumber!!.replace("-", "")
            phoneNumber = phoneNumber!!.replace("(", "")
            phoneNumber = phoneNumber!!.replace(")", "")
            phoneNumber = phoneNumber!!.replace(" ", "").trim()

            apiSendNumber(phoneNumber!!)
            callNumberVerificationActivity(this, phoneNumber!!)
        }

        ll_country_code.setOnClickListener {

            if (!bottomSheet.isVisible) {
                bottomSheet.show(
                    supportFragmentManager,
                    "ModalBottomSheet",
                )
            }
        }
    }

    private fun setDefaultCountry() {
        var defaultCountry = readDefaultCountry()

        Glide.with(this).load(defaultCountry.flag).error(R.drawable.ic_warning)
            .into(iv_country_flag)
        tv_country_code.text = defaultCountry.prefix

        var mask = phoneNumberMask(defaultCountry)
        var maskObj = Mask(value = mask, character = '#', style = MaskStyle.NORMAL)
        val listener = MaskChangedListener(maskObj)
        et_phone_number.addTextChangedListener(listener)

        countryObj = defaultCountry
        maskLength = mask.length
    }

    open fun itemCountry(country: Country?, maskStr: String) {
        bottomSheet.dismiss()
        et_phone_number.text!!.clear()

        tv_country_code!!.text = country!!.prefix
        Glide.with(this).load(country!!.flag).into(iv_country_flag)
        var mask = Mask(value = maskStr, character = '#', style = MaskStyle.NORMAL)

        val listener = MaskChangedListener(mask)
        et_phone_number.addTextChangedListener(listener)

        countryObj = country
        maskLength = maskStr.length
    }

    fun readDefaultCountry(): Country {
        var default = Country("", "", "", "", "", "+998")
        try {
            val obj = JSONObject(getJSONFromAssets()!!)
            val countryArray = obj.getJSONArray("countries")



            for (i in 0 until countryArray.length()) {
                val country = countryArray.getJSONObject(i)
                val name = country.getString("name")
                val countryIso = country.getString("countryIso")
                val currencyIso = country.getString("currencyIso")
                val currencySymbol = country.getString("currencySymbol")
                val flag = country.getString("flag")
                val prefix = country.getString("prefix")

                val countryDetails =
                    Country(name, countryIso, currencyIso, currencySymbol, flag, prefix)


                if (countryDetails.countryIso!!.toLowerCase().equals(simCountryIso())) {
                    return countryDetails
                }
            }

        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }
        return default
    }

    private fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myUsersJSONFile = this?.assets!!.open("CountryCodes.json")
            val size = myUsersJSONFile.available()
            val buffer = ByteArray(size)
            myUsersJSONFile.read(buffer)
            myUsersJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    private fun simCountryIso(): String {
        val tm = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return tm.simCountryIso
    }


    private fun apiSendNumber(phoneNumber: String?) {
        RetrofitHttp.talkOnService.sendPhoneNumber(phoneNumber!!)
            .enqueue(object : Callback<Boolean> {
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    Logger.d("@@@", response.body().toString())
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Logger.d("@@@", t.toString())
                }
            })
    }
}