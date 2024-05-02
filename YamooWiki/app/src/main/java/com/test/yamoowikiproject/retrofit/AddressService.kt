package com.test.yamoowikiproject.retrofit

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface AddressService {
    @GET("local/search/address")
    suspend fun searchAddress(
        @Header("Authorization") token: String,
        @Query("query") query: String
    ): SearchAddressResponse
}
// "KakaoAK 내 REST_API_KEY"