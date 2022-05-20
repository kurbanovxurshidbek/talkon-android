package com.talkon.talkon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.talkon.talkon.R
import com.talkon.talkon.fragment.minorFragment.CountryBottomSheetDialog
import com.talkon.talkon.model.Country

class CountryAdapter(var fragment: CountryBottomSheetDialog, var items: ArrayList<Country>) : BaseAdapter(){
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_country_layout, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val country: Country = items[position]
        if (holder is CountryViewHolder) {
            var tv_country_code = holder.tv_country_code
            var tv_country_name = holder.tv_country_name
            var iv_country_flag = holder.iv_country_flag

            tv_country_code.text = country.countryCode
            tv_country_name.text = country.countryName
            Glide.with(fragment).load(country.countryFlag).into(iv_country_flag)
        }
    }

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_country_flag: ShapeableImageView = view.findViewById(R.id.iv_country_flag)
        var tv_country_code: TextView = view.findViewById(R.id.tv_country_code)
        var tv_country_name: TextView = view.findViewById(R.id.tv_country_name)

    }
}