package com.talkon.talkon.model

data class Response(
    val data: Data? = null,
    val appErrorDto: AppErrorDto? = null,
    val success: Boolean? = null,
    val totalCount: Long? = null,
)

data class AppErrorDto(
    val timestamp: String? = null,
    val status: Long? = null,
    val code: String? = null,
    val message: String? = null,
    val developerMessage: String? = null,
    val path: String? = null,
)

data class Data(
    val expiryForAccessToken: Long? = null,
    val accessToken: String? = null,
    val expiryForRefreshToken: Long? = null,
    val refreshToken: String? = null,
    val issuedAd: Long? = null,
)

