package com.talkon.talkon.utils

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.talkon.talkon.R
import com.talkon.talkon.activity.entryActivity.LogInActivity
import com.talkon.talkon.adapter.CountryDialogAdapter
import com.talkon.talkon.model.Country
import kotlinx.android.synthetic.main.country_bottom_sheet_layout.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

/**
 * CountryBottomSheetDialog is used for choosing a country code when entering a phone number in SignUpActivity
 */

class CountryBottomSheetDialog(var activity: LogInActivity) : BottomSheetDialogFragment() {
    lateinit var recyclerView: RecyclerView
    private var countries: ArrayList<Country> = ArrayList()
    private var items: ArrayList<Country> = ArrayList<Country>()


    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.country_bottom_sheet_layout, container, false)
        readJsonFile()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    private fun initViews(view: View) {

        recyclerView = view.findViewById(R.id.recyclerView)
        val layoutManger = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = layoutManger

        refreshAdapter(countries)

        et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val keyword = s.toString().trim()
                usersByKeyword(keyword)
            }
        })
    }

    fun usersByKeyword(keyword: String) {
        if (keyword.isEmpty())
            refreshAdapter(items)

        items.clear()
        for (country in countries){
            if (country.name!!.toLowerCase().contains(keyword.toLowerCase())) {
                items.add(country)
            }
        }
        refreshAdapter(items)
    }

    private fun refreshAdapter(items: ArrayList<Country>) {
        var adapter = CountryDialogAdapter(activity, this, items)
        recyclerView.adapter = adapter
    }

    fun readJsonFile() {
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

                val userDetails =
                    Country(name, countryIso, currencyIso, currencySymbol, flag, prefix)

                // add the details in the list
                countries.add(userDetails)
            }

        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }

    }

    private fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myUsersJSONFile = activity?.assets!!.open("CountryCodes.json")
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

}