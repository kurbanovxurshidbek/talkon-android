package com.talkon.talkon.network.service

import com.talkon.talkon.model.Country
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface CountryService {
    companion object {
        private const val header_str = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7InVzZXJfZW1haWwiOiJzaGFtc3VuLmNvbUBnbWFpbC5jb20iLCJhcGlfdG9rZW4iOiJPZ19fUERXQVlnSWN1RXRzOHJndDFQTDROLWktd0U2cVltb1BkZGlnUUVEX0pBOVcycFZmTmNCNWhacU5sME4yRjlvIn0sImV4cCI6MTY1MzUwMjA3OH0.rbwf1J0NHS8drJV-UAI9QK16qtsqcjdDmp1eIxz34m0"
    }


    @Headers("Authorization:$header_str")

    @GET("countries")
    fun getCountryList():Call<ArrayList<Country>>

}