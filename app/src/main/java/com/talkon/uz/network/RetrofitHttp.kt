package com.talkon.uz.network

import com.talkon.uz.network.service.TalkOnService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHttp {
    private var IS_TESTER = true
    private val SERVER_DEVELOPMENT = "https://tolkon-2022.herokuapp.com"
    private val SERVER_PRODUCTION = "https://tolkon-2022.herokuapp.com"

    val retrofit =
        Retrofit.Builder().baseUrl(server()).addConverterFactory(GsonConverterFactory.create())
            .build()

    fun server(): String {
        if (IS_TESTER) return SERVER_DEVELOPMENT
        return SERVER_PRODUCTION
    }

    val talkOnService: TalkOnService = retrofit.create(TalkOnService::class.java)
}