package com.talkon.talkon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.talkon.talkon.R
import com.talkon.talkon.utils.CountryBottomSheetDialog
import com.talkon.talkon.model.Country
import com.talkon.talkon.utils.LevelDialog
import com.talkon.talkon.utils.NationalityDialog

class NationalityDialogAdapter(var fragment: NationalityDialog, var items: ArrayList<Country>) : BaseAdapter(){
    var onClick : ((Country) ->Unit)? = null
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_nationality_dialog_layout, parent, false)
        return NationalityViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val country: Country = items[position]
        if (holder is NationalityViewHolder) {
            var iv_country_flag = holder.iv_country_flag
            var tv_country_name = holder.tv_country_name
            var ll_nationality = holder.ll_nationality
            tv_country_name.text = country.name

            Glide.with(fragment).load(country.flag).error(R.color.light_grey).into(iv_country_flag)

            ll_nationality.setOnClickListener {
                onClick!!.invoke(country)
            }

        }
    }

    class NationalityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_country_flag: ShapeableImageView = view.findViewById(R.id.iv_country_flag)
        var tv_country_name: TextView = view.findViewById(R.id.tv_country_name)
        var ll_nationality: LinearLayout = view.findViewById(R.id.ll_nationality)

    }
}