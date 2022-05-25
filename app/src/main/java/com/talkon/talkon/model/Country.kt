package com.talkon.talkon.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("country_name")
    var country_name: String? = null,
    @SerializedName("country_short_name")
    var country_short_name: String?=null,
    @SerializedName("country_phone_code")
    var country_phone_code: Long?=null,
) {
}