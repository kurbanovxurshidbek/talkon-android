package com.talkon.uz.network.service

import com.talkon.uz.model.Response
import retrofit2.Call
import retrofit2.http.*

interface TalkOnService {

    @POST("/api/v1/auth/register/{phoneNumber}")
    fun sendPhoneNumber(@Path("phoneNumber") phoneNumber: String): Call<Boolean>

    @POST("/api/v1/auth/access/token")
    fun sendForToken(@Body map: HashMap<String, Any>): Call<Response>
}