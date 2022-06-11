package com.talkon.talkon.network

import com.talkon.talkon.network.service.CountryService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHttp {
    private var IS_TESTER = true
    private val SERVER_DEVELOPMENT = "https://tolkon-2022.herokuapp.com/api/"
    private val SERVER_PRODUCTION = "https://www.universal-tutorial.com/api/"

    val retrofit =
        Retrofit.Builder().baseUrl(server()).addConverterFactory(GsonConverterFactory.create())
            .build()

    fun server(): String {
        if (IS_TESTER) return SERVER_DEVELOPMENT
        return SERVER_PRODUCTION
    }

    val countryService: CountryService = retrofit.create(CountryService::class.java)
}