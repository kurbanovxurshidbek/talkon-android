package com.talkon.uz.utils

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.talkon.uz.R
import com.talkon.uz.adapter.NationalityDialogAdapter
import com.talkon.uz.model.Country
import kotlinx.android.synthetic.main.nationality_dialog_fragment.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class NationalityDialog (val listener: NationalityListener): DialogFragment() {
    lateinit var recyclerView: RecyclerView
    private var countries: ArrayList<Country> = ArrayList()
    private var items: ArrayList<Country> = ArrayList<Country>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.white_border_rounded);
        var view = inflater.inflate(R.layout.nationality_dialog_fragment, container, false)
        readJsonFile()
        initViews(view)
        return view
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        var et_search = view.findViewById<EditText>(R.id.et_search)
        recyclerView.setLayoutManager(GridLayoutManager(context, 1))
        refreshAdapter(countries)

        et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

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
        var adapter = NationalityDialogAdapter(this, items)
        recyclerView.adapter = adapter
        adapter.onClick = {
            this.dismiss()
            listener.onSelected(it)
        }
    }

    fun readJsonFile() {
        try {
            val obj = JSONObject(getJSONFromAssets()!!)
            val countryArray = obj.getJSONArray("countries")

            for (i in 0 until countryArray.length()) {
                val country = countryArray.getJSONObject(i)
                val name = country.getString("name")
                val flag = country.getString("flag")

                val userDetails =
                    Country(name = name, flag = flag)
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

    interface NationalityListener{
        fun onSelected(country: Country)
    }
}