package com.talkon.talkon.model

import android.os.Parcelable

data class Country(
    val name: String? = null,
    val countryIso: String? = null,
    val currencyIso: String? = null,
    val currencySymbol: String? = null,
    val flag: String? = null,
    val prefix: String? = null
)  {
}