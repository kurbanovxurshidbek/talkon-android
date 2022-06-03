package com.talkon.talkon.activity.entryActivity

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.text.Editable
import android.text.TextWatcher
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.santalu.maskara.Mask
import com.santalu.maskara.MaskChangedListener
import com.santalu.maskara.MaskStyle
import com.talkon.talkon.R
import com.talkon.talkon.activity.BaseActivity
import com.talkon.talkon.model.Country
import com.talkon.talkon.utils.CountryBottomSheetDialog
import com.talkon.talkon.utils.Logger
import kotlinx.android.synthetic.main.activity_log_in.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset


/**
 * In SignInActivity, user can sign in using phone number or Google
 */

class LogInActivity : BaseActivity() {
    private val bottomSheet = CountryBottomSheetDialog(this)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        initViews()
    }

    private fun initViews() {
        setDefaultCountry()

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val str = et_phone_number.text.toString()
                bt_sign_in_light.isEnabled = str.isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        }

        et_phone_number.addTextChangedListener(textWatcher)

        bt_sign_in_light.setOnClickListener {
            callNumberVerificationActivity(this)
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
    }

    open fun itemCountry(country: Country?, mask: String) {
        bottomSheet.dismiss()
        et_phone_number.text!!.clear()

        tv_country_code!!.text = country!!.prefix
        Glide.with(this).load(country!!.flag).into(iv_country_flag)
        var mask = Mask(value = mask, character = '#', style = MaskStyle.NORMAL)

        val listener = MaskChangedListener(mask)
        et_phone_number.addTextChangedListener(listener)
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
                    Logger.d("@@@", countryDetails.toString())
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


    fun phoneNumberMask(country: Country?): String {

        var hashMap: HashMap<String, String> = HashMap<String, String>()
        hashMap.put("AC", "+247-####")
        hashMap.put("AD", "+376-###-###")
        hashMap.put("AE", "+971-5#-###-####")
        hashMap.put("AE", "+971-#-###-####")
        hashMap.put("AF", "+93-##-###-####")
        hashMap.put("AG", "+1(268)###-####")
        hashMap.put("AI", "+1(264)###-####")
        hashMap.put("AL", "+355(###)###-###")
        hashMap.put("AN", "+599-9###-####")
        hashMap.put("AN", "+599-9###-####")
        hashMap.put("AO", "+244 (###) ###-###")
        hashMap.put("AQ", "+672-1##-###")
        hashMap.put("AR", "+54 (###) ###-####")
        hashMap.put("AS", "+1 (684) ###-####")
        hashMap.put("AT", "+43 (###) ###-####")
        hashMap.put("AU", "+61-#-####-####")
        hashMap.put("AW", "+297-###-####")
        hashMap.put("AZ", "+994-##-###-##-##")
        hashMap.put("BA", "+387-##-#####")
        hashMap.put("BB", "+1 (246) ###-####")
        hashMap.put("BD", "+880-##-###-###")
        hashMap.put("BE", "+32 (###) ###-###")
        hashMap.put("BF", "+226-##-##-####")
        hashMap.put("BG", "+359 (###) ###-###")
        hashMap.put("BH", "+973-####-####")
        hashMap.put("BI", "+257-##-##-####")
        hashMap.put("BJ", "+229-##-##-####")
        hashMap.put("BM", "+1 (441) ###-####")
        hashMap.put("BN", "+673-###-####")
        hashMap.put("BO", "+591-#-###-####")
        hashMap.put("BR", "+55 (##) 9####-####")
        hashMap.put("BS", "+1 (242) ###-####")
        hashMap.put("BT", "+975-##-###-###")
        hashMap.put("BW", "+267-##-###-###")
        hashMap.put("BY", "+375 (##) ###-##-##")
        hashMap.put("BZ", "+501-###-####")
        hashMap.put("CA", "+1 (###) ###-####")
        hashMap.put("CD", "+243 (###) ###-###")
        hashMap.put("CF", "+236-##-##-####")
        hashMap.put("CG", "+242-##-###-####")
        hashMap.put("CH", "+41-##-###-####")
        hashMap.put("CI", "+225-##-###-###")
        hashMap.put("CK", "+682-##-###")
        hashMap.put("CL", "+56-#-####-####")
        hashMap.put("CM", "+237-####-####")
        hashMap.put("CN", "+86 (###) ####-####")
        hashMap.put("CO", "+57 (###) ###-####")
        hashMap.put("CU", "+53-#-###-####")
        hashMap.put("CV", "+238 (###) ##-##")
        hashMap.put("CW", "+599-###-####")
        hashMap.put("CY", "+357-##-###-###")
        hashMap.put("CZ", "+420 (###) ###-###")
        hashMap.put("DE", "+49 (####) ###-####")
        hashMap.put("DJ", "+253-##-##-##-##")
        hashMap.put("DK", "+45-##-##-##-##")
        hashMap.put("DM", "+1 (767) ###-####")
        hashMap.put("DO", "+1 (###) ###-####")
        hashMap.put("DZ", "+213-##-###-####")
        hashMap.put("EC", "+593-##-###-####")
        hashMap.put("EE", "+372-####-####")
        hashMap.put("EG", "+20 (###) ###-####")
        hashMap.put("ER", "+291-#-###-###")
        hashMap.put("ES", "+34 (###) ###-###")
        hashMap.put("ET", "+251-##-###-####")
        hashMap.put("FI", "+358 (###) ###-##-##")
        hashMap.put("FJ", "+679-##-#####")
        hashMap.put("FK", "+500-#####")
        hashMap.put("FM", "+691-###-####")
        hashMap.put("FO", "+298-###-###")
        hashMap.put("FR", "+33 (###) ###-###")
        hashMap.put("GA", "+241-#-##-##-##")
        hashMap.put("GB", "+44-##-####-####")
        hashMap.put("GD", "+1 (473) ###-####")
        hashMap.put("GE", "+995 (###) ###-###")
        hashMap.put("GF", "+594-#####-####")
        hashMap.put("GH", "+233 (###) ###-###")
        hashMap.put("GI", "+350-###-#####")
        hashMap.put("GL", "+299-##-##-##")
        hashMap.put("GM", "+220 (###) ##-##")
        hashMap.put("GN", "+224-##-###-###")
        hashMap.put("GQ", "+240-##-###-####")
        hashMap.put("GR", "+30 (###) ###-####")
        hashMap.put("GT", "+502-#-###-####")
        hashMap.put("GU", "+1 (671) ###-####")
        hashMap.put("GW", "+245-#-######")
        hashMap.put("GY", "+592-###-####")
        hashMap.put("HK", "+852-####-####")
        hashMap.put("HN", "+504-####-####")
        hashMap.put("HN", "+504-####-####")
        hashMap.put("HT", "+509-##-##-####")
        hashMap.put("HU", "+36 (###) ###-###")
        hashMap.put("ID", "+62 (###) ####-####")
        hashMap.put("IE", "+353 (###) ###-###")
        hashMap.put("IN", "+91 (####) ###-###")
        hashMap.put("IO", "+246-###-####")
        hashMap.put("IQ", "+964 (###) ###-####")
        hashMap.put("IR", "+98 (###) ###-####")
        hashMap.put("IS", "+354-###-####")
        hashMap.put("IT", "+39 (###) ####-###")
        hashMap.put("JM", "+1 (876) ###-####")
        hashMap.put("JO", "+962-#-####-####")
        hashMap.put("JP", "+81-##-####-####")
        hashMap.put("KE", "+254-###-######")
        hashMap.put("KG", "+996 (###) ###-###")
        hashMap.put("KH", "+855-##-###-###")
        hashMap.put("KI", "+686-##-###")
        hashMap.put("KM", "+269-##-#####")
        hashMap.put("KN", "+1 (869) ###-####")
        hashMap.put("KP", "+850-####-#############")
        hashMap.put("KR", "+82 ##-####-####")
        hashMap.put("KW", "+965-####-####")
        hashMap.put("KY", "+1 (345) ###-####")
        hashMap.put("KZ", "+7 (###) ###-##-##")
        hashMap.put("LA", "+856 (####) ###-###")
        hashMap.put("LB", "+961-##-###-###")
        hashMap.put("LC", "+1 (758) ###-####")
        hashMap.put("LI", "+423 (###) ###-####")
        hashMap.put("LK", "+94-##-###-####")
        hashMap.put("LR", "+231-##-###-###")
        hashMap.put("LS", "+266-#-###-####")
        hashMap.put("LT", "+370 (###) ##-###")
        hashMap.put("LU", "+352 (###) ###-###")
        hashMap.put("LV", "+371-##-###-###")
        hashMap.put("LY", "+218-##-###-####")
        hashMap.put("MA", "+212-##-####-###")
        hashMap.put("MC", "+377 (###) ###-###")
        hashMap.put("MD", "+373-####-####")
        hashMap.put("ME", "+382-##-###-###")
        hashMap.put("MG", "+261-##-##-#####")
        hashMap.put("MH", "+692-###-####")
        hashMap.put("MH", "+692-###-####")
        hashMap.put("MK", "+389-##-###-###")
        hashMap.put("ML", "+223-##-##-####")
        hashMap.put("MM", "+95-##-####-####")
        hashMap.put("MN", "+976-##-##-####")
        hashMap.put("MO", "+853-####-####")
        hashMap.put("MP", "+1 (670) ###-####")
        hashMap.put("MQ", "+596 (###) ##-##-##")
        hashMap.put("MR", "+222-##-##-####")
        hashMap.put("MS", "+1 (664) ###-####")
        hashMap.put("MT", "+356-####-####")
        hashMap.put("MU", "+230-###-####")
        hashMap.put("MV", "+960-###-####")
        hashMap.put("MW", "+265-##-###-####")
        hashMap.put("MX", "+52 (###) ###-####")
        hashMap.put("MY", "+60-##-###-####")
        hashMap.put("MZ", "+258-##-###-###")
        hashMap.put("NA", "+264-##-###-####")
        hashMap.put("NC", "+687-##-####")
        hashMap.put("NE", "+227-##-##-####")
        hashMap.put("NF", "+672-3##-###")
        hashMap.put("NG", "+234 (##) ####-####")
        hashMap.put("NI", "+505-####-####")
        hashMap.put("NL", "+31-##-###-####")
        hashMap.put("NO", "+47 (###) ##-###")
        hashMap.put("NP", "+977 ### ### ####")
        hashMap.put("NR", "+674-###-####")
        hashMap.put("NU", "+683-####")
        hashMap.put("NZ", "+64 (###) ###-####")
        hashMap.put("OM", "+968-##-###-###")
        hashMap.put("PA", "+507-###-####")
        hashMap.put("PE", "+51 (###) ###-###")
        hashMap.put("PF", "+689-##-##-##")
        hashMap.put("PG", "+675 (###) ##-###")
        hashMap.put("PH", "+63 (###) ###-####")
        hashMap.put("PK", "+92 (###) ###-####")
        hashMap.put("PL", "+48 (###) ###-###")
        hashMap.put("PS", "+970-##-###-####")
        hashMap.put("PT", "+351-##-###-####")
        hashMap.put("PW", "+680-###-####")
        hashMap.put("PY", "+595 (###) ###-###")
        hashMap.put("QA", "+974-####-####")
        hashMap.put("RE", "+262-#####-####")
        hashMap.put("RO", "+40-##-###-####")
        hashMap.put("RS", "+381-##-###-####")
        hashMap.put("RU", "+7 (###) ###-##-##")
        hashMap.put("RW", "+250 (###) ###-###")
        hashMap.put("SA", "+966-#-####-####")
        hashMap.put("SB", "+677-###-####")
        hashMap.put("SC", "+248-#-###-###")
        hashMap.put("SD", "+249-##-###-####")
        hashMap.put("SE", "+46-##-###-####")
        hashMap.put("SG", "+65-####-####")
        hashMap.put("SI", "+386-##-###-###")
        hashMap.put("SK", "+421 (###) ###-###")
        hashMap.put("SL", "+232-##-######")
        hashMap.put("SM", "+378-####-######")
        hashMap.put("SN", "+221-##-###-####")
        hashMap.put("SO", "+252-##-###-###")
        hashMap.put("SR", "+597-###-####")
        hashMap.put("SS", "+211-##-###-####")
        hashMap.put("ST", "+239-##-#####")
        hashMap.put("SV", "+503-##-##-####")
        hashMap.put("SX", "+1 (721) ###-####")
        hashMap.put("SY", "+963-##-####-###")
        hashMap.put("SZ", "+268-##-##-####")
        hashMap.put("TC", "+1 (649) ###-####")
        hashMap.put("TD", "+235-##-##-##-##")
        hashMap.put("TD", "+235-##-##-##-##")
        hashMap.put("TG", "+228-##-###-###")
        hashMap.put("TH", "+66-#-####-####")
        hashMap.put("TJ", "+992-##-###-####")
        hashMap.put("TK", "+690-####")
        hashMap.put("TL", "+670-###-#####")
        hashMap.put("TM", "+993-#-###-####")
        hashMap.put("TN", "+216-##-###-###")
        hashMap.put("TO", "+676-#####")
        hashMap.put("TR", "+90 (###) ###-####")
        hashMap.put("TT", "+1 (868) ###-####")
        hashMap.put("TV", "+688-#######")
        hashMap.put("TW", "+886-#-####-####")
        hashMap.put("TZ", "+255-##-###-####")
        hashMap.put("UA", "+380 (##) ###-##-##")
        hashMap.put("UG", "+256 (###) ###-###")
        hashMap.put("UK", "+44-##-####-####")
        hashMap.put("US", "+1 (###) ###-####")
        hashMap.put("UY", "+598-#-###-##-##")
        hashMap.put("UZ", "+998 (##) ###-####")
        hashMap.put("VA", "+39-6-698-#####")
        hashMap.put("VC", "+1 (784) ###-####")
        hashMap.put("VE", "+58 (###) ###-####")
        hashMap.put("VG", "+1 (284) ###-####")
        hashMap.put("VI", "+1 (340) ###-####")
        hashMap.put("VN", "+84-##-####-###")
        hashMap.put("VU", "+678-##-#####")
        hashMap.put("WF", "+681-##-####")
        hashMap.put("WS", "+685-##-####")
        hashMap.put("YE", "+967-###-###-###")
        hashMap.put("ZA", "+27-##-###-####")
        hashMap.put("ZW", "+263-#-######")

        var str = hashMap.get(country!!.countryIso).toString()
        var mask: String = str.removeRange(0, country.prefix!!.length)
        return mask
    }

}